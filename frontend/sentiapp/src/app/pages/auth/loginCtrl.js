(function () {
    'use strict';

    angular.module('BlurAdmin.pages.auth')
        .controller('loginCtrl', loginCtrl);

    /** @ngInject */
    function loginCtrl($scope, $auth, $state, sentimentService) {

        $scope.authenticate = function (provider) {
            console.log($auth.isAuthenticated());
            $auth.authenticate(provider).then(function (response) {
                sentimentService.getMe(function (user) {
                    $state.go('main.dashboard');
                });
            });
        };

    }
})();