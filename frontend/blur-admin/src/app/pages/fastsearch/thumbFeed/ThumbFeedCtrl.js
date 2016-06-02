(function () {
    'use strict';

    angular.module('BlurAdmin.pages.fastsearch')
        .controller('ThumbFeedCtrl', ThumbFeedCtrl);

    function ThumbFeedCtrl($scope) {
        $scope.feed = [
            {
                type: 'thumb-up',
                author: 'Kostya',
                surname: 'Danovsky',
                header: 'Posted new message',
                text: 'Guys, check this out: \nA police officer found a perfect hiding place for watching for speeding motorists. One day, the officer was amazed when everyone was under the speed limit, so he investigated and found the problem. A 10 years old boy was standing on the side of the road with a huge hand painted sign which said "Radar Trap Ahead." A little more investigative work led the officer to the boy\'s accomplice: another boy about 100 yards beyond the radar trap with a sign reading "TIPS" and a bucket at his feet full of change.',
                time: 'Today 11:55 pm',
                ago: '25 minutes ago',
                expanded: false,
            }, {
                type: 'thumb-up',
                author: 'Andrey',
                surname: 'Hrabouski',
                header: 'Added new video',
                text: '"Vader and Me"',
                preview: 'app/feed/vader-and-me-preview.png',
                link: 'https://www.youtube.com/watch?v=IfcpzBbbamk',
                time: 'Today 9:30 pm',
                ago: '3 hrs ago',
                expanded: false,
            }, {
                type: 'thumb-up',
                author: 'Vlad',
                surname: 'Lugovsky',
                header: 'Added new image',
                text: '"My little kitten"',
                preview: 'app/feed/my-little-kitten.png',
                link: 'http://api.ning.com/files/DtcI2O2Ry7A7VhVxeiWfGU9WkHcMy4WSTWZ79oxJq*h0iXvVGndfD7CIYy-Ax-UAFCBCdqXI4GCBw3FOLKTTjQc*2cmpdOXJ/1082127884.jpeg',
                time: 'Today 2:20 pm',
                ago: '10 hrs ago',
                expanded: false,
            }, {
                type: 'thumb-down',
                author: 'Nasta',
                surname: 'Linnie',
                header: 'Posted new message',
                text: 'Haha lol',
                time: '11.11.2015',
                ago: '2 days ago',
                expanded: false,
            }, {
                type: 'thumb-up',
                author: 'Nick',
                surname: 'Cat',
                header: 'Posted location',
                text: '"New York, USA"',
                preview: 'app/feed/new-york-location.png',
                link: 'https://www.google.by/maps/place/New+York,+NY,+USA/@40.7201111,-73.9893872,14z',
                time: '11.11.2015',
                ago: '2 days ago',
                expanded: false,
            }
        ];

        $scope.expandMessage = function (message) {
            message.expanded = !message.expanded;
        };

        function getType(sentiment) {
            if ('Positive' === sentiment)
                return 'thumb-up';
            else if ('Negative' === sentiment)
                return 'thumb-down';
            else return 'thumb-neutral';
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
                    img: tweet.mediaUrl,
                    link: tweet.link,
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
                    img: tweet.mediaUrl,
                    link: tweet.link,
                    expanded: true
                });
            });
            $scope.$apply();
        });
    }
})();