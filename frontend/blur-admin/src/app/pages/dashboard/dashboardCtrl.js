(function () {
    'use strict';

    angular.module('BlurAdmin.pages.fastsearch')
        .controller('dashboardCtrl', dashboardCtrl);

    /** @ngInject */
    function dashboardCtrl($scope, sentimentService, $auth) {


        $scope.isAuthenticated = function () {
            return $auth.isAuthenticated();
        };

    }
})();