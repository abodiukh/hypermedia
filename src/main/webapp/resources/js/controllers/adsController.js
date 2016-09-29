app.controller('adsController',
        function ($scope, $http, $location, apiService, paginationService) {
            $scope.ads = {};
            $scope.pagination = {};

            apiService.getData().then(response => {
                return response.data._links["ads"].href.split('{')[0];
            }).then(result => apiService.getResource(result)).then(response=> {
                $scope.ads = response.data;
                createPagination(response.data);
            });

            $scope.navigate = function (link) {
                apiService.getResource(link.value).then(result => {
                    $scope.ads = result.data;
                    createPagination(result.data);
                })
            };

            var createPagination = function (data) {
                $scope.pagination.links = paginationService.buildPagination(data.page, data._links);
            }

        }
);