(function () {
    'use strict';

    angular.module('BlurAdmin.pages.research')
        .controller('researchCtrl', researchCtrl);

    /** @ngInject */
    function researchCtrl($scope, sentimentService, $stateParams, $stomp, layoutColors, $timeout, $state) {
        var researchId = $stateParams.researchId;
        if (!researchId) {
            researchId = Math.floor(Math.random() * 1000000000) + "";
        }
        var subscription;

        $scope.research = {
            isPublic: true
        };

        $scope.researchData;

        sentimentService.researchData(researchId)
            .then(function (success) {
                searchBegan(success.data);
                $stomp.connect('http://localhost:8080/cool', {}).then(function (frame) {
                    subscription = $stomp.subscribe('/tweet/' + researchId, function (payload, headers, res) {
                        console.log(payload);
                        $scope.$broadcast('researchEventTweets', payload.tweets);
                        $scope.$broadcast('researchEventBySentiment', payload.tweetsBySentiment);
                        $scope.$broadcast('researchEventLineData', payload.lineData);
                    });
                });
            });

        $scope.search = function () {
            var tags = $("#search-tags-input").tagsinput("items");
            sentimentService.startResearch(tags, researchId, $scope.research.isPublic).then(function (success) {
                $state.go('main.research', {researchId: researchId});
            });
        };

        function searchBegan(data) {
            if (data && data != "") {
                $scope.researchData = data;
                $timeout(function () {
                    $scope.$broadcast('researchEventTweets', data.tweets);
                    $scope.$broadcast('researchEventLineData', data.lineDatas);
                });
            }
        }

        $scope.$on('$destroy', function () {
            if (subscription != null) {
                subscription.unsubscribe();
            }
        });

        $scope.colors = [layoutColors.primary, layoutColors.warning, layoutColors.danger, layoutColors.info, layoutColors.success, layoutColors.default, layoutColors.primaryDark];
        $scope.lineData = [];
        var i = 1;
        $scope.$on('researchEventLineData', function (event, lineData) {
            $scope.lineData = lineData;
            $scope.$apply();
        });

        $scope.radarLabels = ["Positive", "Negative", "Neutral"];
        $scope.radarData = [0, 0, 0];
        $scope.radarOptions = {
            segmentShowStroke: false
        };

        $scope.$on('researchEventBySentiment', function (event, bySentiment) {
            $scope.radarData = [0, 0, 0];
            $scope.radarData[0] = bySentiment.Positive;
            $scope.radarData[1] = bySentiment.Negative;
            $scope.radarData[2] = bySentiment.Neutral;
            $scope.$apply();
        });

    }
})();