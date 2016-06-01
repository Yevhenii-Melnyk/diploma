(function () {
    'use strict';

    angular.module('BlurAdmin.pages.research')
        .controller('researchCtrl', researchCtrl);

    /** @ngInject */
    function researchCtrl($scope, sentimentService, $stateParams, $stomp, layoutColors) {
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

        $scope.tweets = [
            {
                text: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto, optio, dolorum provident rerum" +
                "aut hic quasi" +
                "placeat iure tempora laudantium ipsa ad debitis unde? Iste voluptatibus minus veritatis qui" +
                "ut.",
                name: "asdfasdfasdf",
                date: "Jan 14"
            }, {
                text: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto, optio, dolorum provident rerum" +
                "aut hic quasi" +
                "placeat iure tempora laudantium ipsa ad debitis unde? Iste voluptatibus minus veritatis qui" +
                "ut.",
                name: "asdfasdfasdf",
                date: "Jan 14"
            }
        ];
        $scope.colors = [layoutColors.primary, layoutColors.warning, layoutColors.danger, layoutColors.info, layoutColors.success, layoutColors.default, layoutColors.primaryDark];
        $scope.lineData = [
            {y: "2006", a: 100, b: 90},
            {y: "2007", a: 75, b: 65},
            {y: "2008", a: 50, b: 40},
            {y: "2009", a: 75, b: 65},
            {y: "2010", a: 50, b: 40},
            {y: "2011", a: 75, b: 65},
            {y: "2012", a: 100, b: 90}
        ];

        $scope.labels = ["May", "June", "Jule", "August", "September", "October", "November"];
        $scope.series = ['Product A', 'Product B'];
        $scope.data = [
            [65, 59, 90, 81, 56, 55, 40],
            [28, 48, 40, 19, 88, 27, 45]
        ];

    
    }
})();