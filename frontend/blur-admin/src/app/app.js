'use strict';

var app = angular.module('BlurAdmin', [
        'ngAnimate',
        'ui.bootstrap',
        'ui.sortable',
        'ui.router',
        'ngTouch',
        'toastr',
        'smart-table',
        "xeditable",
        'ui.slimscroll',
        'ngJsTree',
        'angular-progress-button-styles',
        'bootstrap-tagsinput',
        'ngLoadingSpinner',
        'ngStomp',
        'satellizer',

        'BlurAdmin.theme',
        'BlurAdmin.pages',
        'BlurAdmin.tplSkin'
    ])
    .config(function ($authProvider) {
        $authProvider.twitter({
            url: 'http://localhost:8080/auth/twitter'
        });

    });