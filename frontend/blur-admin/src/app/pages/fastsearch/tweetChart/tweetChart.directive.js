(function () {
    'use strict';

    angular.module('BlurAdmin.pages.fastsearch')
        .directive('tweetChart', tweetChart);

    /** @ngInject */
    function tweetChart() {
        return {
            restrict: 'E',
            controller: 'TweetChartCtrl',
            templateUrl: 'app/pages/fastsearch/tweetChart/tweetChart.html'
        };
    }
})();