(function () {
    'use strict';

    angular.module('BlurAdmin.pages.fastsearch', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('main.fastsearch', {
                url: '/fastsearch',
                templateUrl: 'app/pages/fastsearch/fastsearch.html',
                title: 'Fast search',
                controller: 'fastSearchCtrl',
                sidebarMeta: {
                    icon: 'ion-android-search',
                    order: 0
                }
            });
    }

})();
