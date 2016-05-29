(function () {
    'use strict';

    angular.module('BlurAdmin.theme')
        .service('sentimentService', sentimentService);

    /** @ngInject */
    function sentimentService($http) {

        var searchUrl = 'http://localhost:8080/search';
        var geoUrl = 'http://localhost:8080/geo';

        var searchSentiment = function (searchTags) {
            return $http.get(searchUrl, {
                params: {
                    tags: searchTags
                }
            });
        };

        var geoSearchSentiment = function (searchTags, countries) {
            return $http.post(geoUrl, {
                tags: searchTags,
                countries: countries
            });
        };

        return {
            searchSentiment: searchSentiment,
            geoSearchSentiment: geoSearchSentiment
        };
    }
})();