(function () {
    'use strict';

    angular.module('BlurAdmin.pages.research')
        .controller('researchCtrl', researchCtrl);

    /** @ngInject */
    function researchCtrl($scope, sentimentService, $stateParams, $stomp, layoutColors) {
        var researchId = $stateParams.researchId;
        var subscription;

        $scope.research = {
            isPublic: true
        };

        sentimentService.researchData(researchId)
            .then(function (success) {
                console.log(success);
                $stomp.connect('http://localhost:8080/cool', {}).then(function (frame) {
                    subscription = $stomp.subscribe('/tweet/' + researchId, function (payload, headers, res) {
                        console.log(payload);
                    });
                });
            });

        $scope.search = function () {
            var tags = $("#search-tags-input").tagsinput("items");
            sentimentService.startResearch(tags, researchId, $scope.research.isPublic).then(function (success) {
                console.log(success)
            });
        };

        $scope.$on('$destroy', function () {
            if (subscription != null) {
                subscription.unsubscribe();
            }
        });



        $scope.labels = ["May", "June", "Jule", "August", "September", "October", "November"];
        $scope.series = ['Positive', 'Negative', 'Neutral'];
        $scope.data = [
            [65, 59, 90, 81, 56, 55, 40],
            [28, 48, 40, 19, 88, 27, 45],
            [11, 42, 44, 31, 25, 43, 11]
        ];


        $scope.radarLabels = ["Positive", "Negative", "Neutral"];
        $scope.radarData = [134, 55, 80];
        $scope.radarOptions = {
            segmentShowStroke: false
        };


    }
})();