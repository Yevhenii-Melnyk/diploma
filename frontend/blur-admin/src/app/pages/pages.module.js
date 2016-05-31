(function () {
    'use strict';

    angular.module('BlurAdmin.pages', [
            'ui.router',

            'BlurAdmin.pages.dashboard',
            'BlurAdmin.pages.ui',
            'BlurAdmin.pages.components',
            'BlurAdmin.pages.form',
            'BlurAdmin.pages.tables',
            'BlurAdmin.pages.charts',
            'BlurAdmin.pages.maps',
            'BlurAdmin.pages.profile',
            'BlurAdmin.pages.fastsearch',
            'BlurAdmin.pages.research'
        ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($urlRouterProvider) {
        $urlRouterProvider.otherwise('/dashboard');
    }

})();
