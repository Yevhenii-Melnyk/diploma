(function () {
    'use strict';

    angular.module('BlurAdmin.pages.fastsearch')
        .directive('radarChart', radarChart);

    /** @ngInject */
    function radarChart() {
        return {
            restrict: 'E',
            controller: 'RadarChartCtrl',
            templateUrl: 'app/pages/fastsearch/radarChart/radarChart.html'
        };
    }
})();