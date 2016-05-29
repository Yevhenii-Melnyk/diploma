(function () {
    'use strict';

    angular.module('BlurAdmin.pages.fastsearch')
        .controller('ThumbFeedCtrl', ThumbFeedCtrl);

    function ThumbFeedCtrl($scope) {
        console.log($scope.type);
        $scope.feed = [];

        $scope.expandMessage = function (message) {
            message.expanded = !message.expanded;
        };

        function getType(sentiment) {
            if ('Positive' === sentiment)
                return 'thumb-up';
            else if ('Negative' === sentiment)
                return 'thumb-down';
        }

        function getHeader(text) {
            if (text.length > 200)
                return text.substring(200) + '...';
            else return text;
        }

        $scope.$on('searchEvent', function (event, data) {
            $scope.feed = [];
            data.tweets.forEach(function (tweet) {
                $scope.feed.push({
                    type: getType(tweet.sentiment),
                    author: tweet.userName,
                    text: tweet.text,
                    header: getHeader(tweet.text),
                    time: tweet.createdAt,
                    expanded: true
                });
            });
        });

        $scope.$on('countryEvent', function (event, data) {
            var tweets = data.positive;
            if ($scope.type == 'negative') {
                tweets = data.negative;
            }
            $scope.feed = [];
            tweets.forEach(function (tweet) {
                $scope.feed.push({
                    type: getType(tweet.sentiment),
                    author: tweet.userName,
                    text: tweet.text,
                    header: getHeader(tweet.text),
                    time: tweet.createdAt,
                    expanded: true
                });
            });
            $scope.$apply();
        });
    }
})();