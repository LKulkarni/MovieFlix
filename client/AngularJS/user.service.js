/**
 * Created by Loukik on 28-Sep-16.
 */

(function () {


    angular.module('movieflix')
        .service('userService', userService);


    userService.$inject = ['$http', '$q'];
    function userService($http, $q) {

        var self = this;

        self.findAll = getUsers;
        self.findOne = getUser;
        self.addNew = addUser;
        self.update = updateUser;
        self.deleteOne = deleteUser;
        self.authenticateUser = authenticate;

        function getUsers() {
            return $http
            ({
                method: 'GET',
                url: 'http://localhost:8080/RestAPI/app/users'
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }


        function authenticate(username, password) {
            return $http
            ({
                method: 'POST',
                url: 'http://localhost:8080/RestAPI/app/users/authenticate',
                params: {email: username, pass: password}
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

        function getUser(id) {
            return $http
            ({
                method: 'GET',
                url: 'http://localhost:8080/RestAPI/app/users/' + id
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

        function addUser(user) {
            return $http
            ({
                method: 'POST',
                url: 'http://localhost:8080/RestAPI/app/users',
                data: user
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

        function updateUser(id, user) {
            return $http
            ({
                method: 'PUT',
                url: 'http://localhost:8080/RestAPI/app/users/' + id,
                data: user
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

        function deleteUser(id) {
            return $http
            ({
                method: 'DELETE',
                url: 'http://localhost:8080/RestAPI/app/users/' + id
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

    }//end of userService


})();
