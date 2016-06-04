(function () {
    'use strict';

    angular.module('BlurAdmin.pages', [
            'ui.router',

            //'BlurAdmin.pages.ui',
            //'BlurAdmin.pages.components',
            'BlurAdmin.pages.form',
            //'BlurAdmin.pages.tables',
            'BlurAdmin.pages.charts',
            //'BlurAdmin.pages.profile',
            'BlurAdmin.pages.dashboard',
            'BlurAdmin.pages.maps',
            'BlurAdmin.pages.fastsearch',
            'BlurAdmin.pages.research'
        ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($urlRouterProvider) {
        $urlRouterProvider.otherwise('/dashboard');
    }

})();
