(function () {
    'use strict';

    angular.module('BlurAdmin.pages.auth')
        .controller('authHeaderCtrl', authHeaderCtrl);

    /** @ngInject */
    function authHeaderCtrl($scope, $http, $auth, sentimentService) {

        sentimentService.getMe(function (user) {
            $scope.user = user;
        });

        $scope.isAuthenticated = function () {
            return $auth.isAuthenticated();
        };
    }
})();