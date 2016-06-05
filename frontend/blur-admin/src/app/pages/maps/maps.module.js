/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.maps', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('main.geosearch', {
                url: '/geosearch',
                templateUrl: 'app/pages/maps/map-bubbles/map-bubbles.html',
                controller: 'MapBubblePageCtrl',
                title: 'Geo Search',
                sidebarMeta: {
                    icon: 'ion-ios-location-outline',
                    order: 500
                }
            });

    }

})();
