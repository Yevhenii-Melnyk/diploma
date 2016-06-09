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
            var keys = [1, 2, 3, 4, 5, 6, 7];
            for (var key in keys) {
                var dayData = data.negativeTweetsByDay[key];
                if (!dayData)
                    dayData = 0;
                $scope.data[1].push(dayData);
            }
            for (var key in keys) {
                var dayData = data.positiveTweetsByDay[key];
                if (!dayData)
                    dayData = 0;
                $scope.data[0].push(dayData);
            }
        });
    }
})();