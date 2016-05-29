(function () {
    'use strict';

    angular.module('BlurAdmin.pages.fastsearch')
        .controller('RadarChartCtrl', RadarChartCtrl);

    /** @ngInject */
    function RadarChartCtrl($scope) {
        $scope.labels = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
        $scope.data = [
            [0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0]
        ];
        $scope.series = ['Positive', 'Negative'];

        $scope.$on('searchEvent', function (event, data) {
            $scope.data = [[], []];
            for (var key in data.negativeTweetsByDay) {
                $scope.data[0].push(data.negativeTweetsByDay[key]);
            }
            for (var key in data.positiveTweetsByDay) {
                $scope.data[1].push(data.positiveTweetsByDay[key]);
            }
        });
    }
})();