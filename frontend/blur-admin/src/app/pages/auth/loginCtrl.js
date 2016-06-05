(function () {
    'use strict';

    angular.module('BlurAdmin.pages.auth')
        .controller('loginCtrl', loginCtrl);

    /** @ngInject */
    function loginCtrl($scope, $auth, $state) {

        $scope.authenticate = function (provider) {
            console.log($auth.isAuthenticated());
            $auth.authenticate(provider).then(function (response) {
                $state.go('main.dashboard');
            });
        };

    }
})();