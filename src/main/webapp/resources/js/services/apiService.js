app.factory('apiService', function ($http) {
    var apiUrl = 'http://localhost:8080/api';

    function getData(path = "") {
        return $http.get(apiUrl + path, {cache: true})
    }

    function getResource(link) {
        console.log(link);
        return $http.get(link);
    }

    return {
        getData,
        getResource
    }
});
