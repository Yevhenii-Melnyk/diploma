/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.auth', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('login', {
                url: '/login',
                title: 'Login',
                templateUrl: 'app/pages/auth/login.html',
                controller: "loginCtrl"
            })
            .state('logout', {
                url: '/logout',
                title: 'logout',
                controller: "logoutCtrl"
            });
    }

})();
