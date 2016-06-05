(function () {
    'use strict';

    angular.module('BlurAdmin.theme')
        .service('sentimentService', sentimentService);

    /** @ngInject */
    function sentimentService($http) {

        var user;
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
        var startResearch = function (searchTags, id, isPublic) {
            return $http.get(startResearchUrl, {
                params: {
                    tags: searchTags,
                    researchId: id,
                    userName: user.name,
                    isPublic: isPublic
                }
            });
        };

        var getMe = function (callback) {
            if (!user) {
                $http.get('http://localhost:8080/auth/me').then(function (response) {
                    user = response.data;
                    callback(user);
                });
            } else {
                callback(user);
            }
        };

        return {
            searchSentiment: searchSentiment,
            geoSearchSentiment: geoSearchSentiment,
            researchData: researchData,
            startResearch: startResearch,
            getMe: getMe
        };
    }
})();