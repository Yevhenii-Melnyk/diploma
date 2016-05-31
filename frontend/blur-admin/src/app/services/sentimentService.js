(function () {
    'use strict';

    angular.module('BlurAdmin.theme')
        .service('sentimentService', sentimentService);

    /** @ngInject */
    function sentimentService($http) {

        var searchUrl = 'http://localhost:8080/search';
        var geoUrl = 'http://localhost:8080/geo';
        var researchUrl = 'http://localhost:8080/research';
        var startResearchUrl = 'http://localhost:8080/startResearch';

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

        var researchData = function (id) {
            return $http.get(researchUrl, {
                params: {
                    researchId: id
                }
            });
        };
        var startResearch = function (searchTags, id) {
            return $http.get(startResearchUrl, {
                params: {
                    tags: searchTags,
                    researchId: id
                }
            });
        };

        return {
            searchSentiment: searchSentiment,
            geoSearchSentiment: geoSearchSentiment,
            researchData: researchData,
            startResearch: startResearch
        };
    }
})();