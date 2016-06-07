(function () {
    'use strict';

    angular.module('BlurAdmin.pages.dashboard')
        .controller('BlurFeedCtrl', BlurFeedCtrl);

    function BlurFeedCtrl($scope, sentimentService, $state) {
        $scope.feed = [];

        var setFeed = function (response) {
            var researches = response.data;
            researches.forEach(function (research) {
                $scope.feed.push({
                    type: 'text-message',
                    author: research.userName,
                    time: research.createdAt,
                    expanded: false,
                    tags: research.tags,
                    id: research.id
                });
            });
        };

        if ($scope.public == "true") {
            sentimentService.getPublicResearches().then(setFeed);
        } else {
            sentimentService.getUserResearches(setFeed);
        }

        $scope.expandMessage = function (research) {
            $state.go('main.research', {researchId: research.id})
        }
    }
})();