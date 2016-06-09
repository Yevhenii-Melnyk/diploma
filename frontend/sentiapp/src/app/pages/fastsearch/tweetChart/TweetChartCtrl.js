(function () {
    'use strict';

    angular.module('BlurAdmin.pages.fastsearch')
        .controller('TweetChartCtrl', TweetChartCtrl);

    /** @ngInject */
    function TweetChartCtrl(layoutColors, $scope) {
        var palette = layoutColors.bgColorPalette;

        var colours = {
            'Negative': {
                color: palette.silverTree,
                highlight: palette.silverTreeDark
            },
            'Positive': {
                color: palette.gossip,
                highlight: palette.gossipDark
            },
            'Neutral': {
                color: palette.white,
                highlight: palette.whiteDark
            }
        };

        $scope.totalCount = 0;
        $scope.tweets = [];
        //$scope.tweets = [
        //    {
        //        value: 10,
        //        color: palette.silverTree,
        //        highlight: palette.silverTreeDark,
        //        label: 'Negative',
        //        percentage: 87,
        //        order: 0
        //    }, {
        //        value: 30,
        //        color: palette.gossip,
        //        highlight: palette.gossipDark,
        //        label: 'Positive',
        //        percentage: 38,
        //        order: 2
        //    }
        //, {
        //    value: 20,
        //    color: palette.white,
        //    highlight: palette.whiteDark,
        //    label: 'Strong positive',
        //    percentage: 17,
        //    order: 1
        //}
        //{
        //    value: 20,
        //    color: palette.blueStone,
        //    highlight: palette.blueStoneDark,
        //    label: 'Negative',
        //    percentage: 22,
        //    order: 4
        //}, {
        //    value: 120,
        //    color: palette.surfieGreen,
        //    highlight: palette.surfieGreenDark,
        //    label: 'Neutral',
        //    percentage: 70,
        //    order: 3
        //},
        //];

        //$scope.tweets.forEach(function (item) {
        //    item.percentage = item.value / $scope.totalCount * 100;
        //});


        $scope.$on('searchEvent', function (event, data) {
            $scope.totalCount = data.total;
            $scope.tweets = [];
            angular.forEach(data.tweetsBySentiment, function (value, key) {
                $scope.tweets.push({
                    label: key,
                    value: value,
                    color: colours[key].color,
                    highlight: colours[key].highlight,
                    percentage: value / $scope.totalCount * 100
                });
            });
            drawChart($scope.tweets);
        });

        function drawChart(tweets) {
            var ctx = document.getElementById('chart-area').getContext('2d');
            window.myDoughnut = new Chart(ctx).Doughnut(tweets, {
                segmentShowStroke: false,
                percentageInnerCutout: 50

            });
        }

    }
})();