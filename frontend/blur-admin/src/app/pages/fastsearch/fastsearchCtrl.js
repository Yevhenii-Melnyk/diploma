(function () {
    'use strict';

    angular.module('BlurAdmin.pages.fastsearch')
        .controller('fastSearchCtrl', fastSearchCtrl);

    /** @ngInject */
    function fastSearchCtrl($scope, sentimentService, $timeout) {

        $scope.searchPerformed = false;

        $scope.search = function () {
            var tags = $("#search-tags-input").tagsinput("items");
            sentimentService.searchSentiment(tags)
                .then(function (success) {
                    $scope.searchPerformed = true;
                    $timeout(function () {
                        $scope.$broadcast('searchEvent', success.data);
                    });
                    console.log(success)
                });
        };

    }
})();