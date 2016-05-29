(function () {
    'use strict';

    angular.module('BlurAdmin.pages.fastsearch')
        .directive('thumbFeed', thumbFeed);

    /** @ngInject */
    function thumbFeed() {
        return {
            restrict: 'E',
            controller: 'ThumbFeedCtrl',
            templateUrl: 'app/pages/fastsearch/thumbFeed/thumbFeed.html',
            scope: {
                type: '@'
            }
        };
    }
})();