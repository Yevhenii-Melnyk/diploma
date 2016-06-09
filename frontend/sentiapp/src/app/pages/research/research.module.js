(function () {
    'use strict';

    angular.module('BlurAdmin.pages.research', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('main.research', {
                url: '/research/:researchId',

                templateUrl: 'app/pages/research/research.html',
                title: 'Research',
                controller: 'researchCtrl',
                sidebarMeta: {
                    icon: 'ion-clock',
                    order: 20,
                    secured: true
                }
            });
    }

})();
