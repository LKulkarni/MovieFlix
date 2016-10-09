/**
 * Created by Loukik on 29-Sep-16.
 */
(function () {

    angular.module('movieflix')
        .controller('loginController', loginController)


    loginController.$inject = ['userService', 'authFactory', '$location', '$rootScope']
    function loginController(userService, authFactory, $location, $rootScope) {
        var loginVm = this;
        loginVm.user = null;
        loginVm.login = login;
        loginVm.logout = logout;
        loginVm.currentUser = getCurrentUser;

        init();


        function init() {
            authFactory.clearCredentials();
            getCurrentUser();
        }

        function login() {
            authFactory.login(loginVm.username, loginVm.password, function (response) {
                /* For User */
                if (response.active && response.role == "User") {
                    loginVm.user = response;
                    authFactory.setCredentials(loginVm.username, loginVm.password, loginVm.user.firstName, loginVm.user.lastName, response);
                    $location.path('/userhome');

                }
                /* For Admin */
                else if (response.active && response.role == "Admin") {
                    loginVm.user = response;
                    authFactory.setCredentials(loginVm.username, loginVm.password, loginVm.user.firstName, loginVm.user.lastName, response)
                    $location.path('/adminhome');
                }

                else {

                    $location.path('/');

                }
            });

        }


        function logout() {
            authFactory.clearCredentials();
            loginVm.user = null;
            $location.path('/');
        }

        function getCurrentUser() {

            loginVm.user = $rootScope.globals.user;
        }

    }


})();