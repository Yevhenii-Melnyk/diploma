(function () {
    'use strict';

    angular.module('BlurAdmin.pages.research')
        .controller('researchCtrl', researchCtrl);

    /** @ngInject */
    function researchCtrl($scope, sentimentService, $stateParams, $stomp) {
        var researchId = $stateParams.researchId;
        var subscription;
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
            sentimentService.startResearch(tags, researchId).then(function (success) {
                console.log(success)
            });
        };

        $scope.$on('$destroy', function () {
            if (subscription != null) {
                subscription.unsubscribe();
            }
        });

        $scope.tweets = [{
            text: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto, optio, dolorum provident rerum" +
            "aut hic quasi" +
            "placeat iure tempora laudantium ipsa ad debitis unde? Iste voluptatibus minus veritatis qui" +
            "ut.",
            name: "asdfasdfasdf"

        }];
    }
})();