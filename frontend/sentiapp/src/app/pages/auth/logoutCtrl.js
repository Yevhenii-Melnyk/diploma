(function () {
    'use strict';

    angular.module('BlurAdmin.pages.auth')
        .controller('logoutCtrl', logoutCtrl);

    /** @ngInject */
    function logoutCtrl($scope, $auth, $state) {

        $auth.logout()
            .then(function () {
                $state.go('main.dashboard');
            });

    }
})();