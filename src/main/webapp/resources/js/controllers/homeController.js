app.controller('homeController',
        function ($scope, $location, apiService) {
            $scope.response = {};
            $scope.menuItems = [];

            apiService
                    .getData()
                    .then(response => {
                        $scope.response = response;
                        $scope.menuItems = Object.keys(response.data._links);

                    })
                    .catch(error => console.log(error));

        }
);