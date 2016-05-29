(function () {
    'use strict';

    angular.module('BlurAdmin.pages.fastsearch', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('fastsearch', {
                url: '/fastsearch',
                templateUrl: 'app/pages/fastsearch/fastsearch.html',
                title: 'Fast search',
                controller: 'fastSearchCtrl',
                sidebarMeta: {
                    icon: 'ion-android-home',
                    order: 0
                }
            });
    }

})();
