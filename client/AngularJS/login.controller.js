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

        getCurrentUser();
        function init() {
            authFactory.clearCredentials();
        }

        function login() {
            authFactory.login(loginVm.username, loginVm.password, function (response) {
                /*User*/
                if (response.active && response.role == "User") {
                    loginVm.user = response;
                    authFactory.setCredentials(loginVm.username, loginVm.password, loginVm.firstName, loginVm.lastName);
                    $location.path('/userhome');

                }
                /*Admin*/
                else if (response.active && response.role == "Admin") {
                    $location.path('/admin/home');
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

            console.dir($rootScope.globals);
            return $rootScope.globals.currentUser;
        }

    }


})();