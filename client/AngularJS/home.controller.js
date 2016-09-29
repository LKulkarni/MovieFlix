/**
 * Created by Loukik on 27-Sep-16.
 */
(function () {


    angular.module('movieflix')
        .controller('homeController', homeController);

    homeController.$inject = ['subscriptionService', 'videoService', 'authFactory', '$location'];

    function homeController(subscriptionService, videoService, authFactory, $location) {
        var dataVm = this;

        dataVm.login = login;

        init();

        function init() {
            subscriptionService.findAll()
                .then(function (data) {
                    dataVm.allPlans = data;
                    console.dir(dataVm.allPlans);
                }, function (error) {
                    console.log(error);
                });


            videoService.findFew(3)
                .then(function (data) {
                    dataVm.allVideos = data;
                    console.dir(dataVm.allVideos);
                }, function (error) {
                    console.log(error);
                });

            authFactory.clearCredentials();

        }

        function login() {
            authFactory.login(dataVm.username, dataVm.password, function (response) {
                if (response.active && response.role == "User") {
                    authFactory.setCredentials(dataVm.username, dataVm.password);
                    $location.path('/userhome');
                    console.log("reached");
                }
                else if (response.active && response.role == "Admin") {
                    $location.path('/register');
                }

                else {
                    console.dir("reached2" + response);
                    $location.path('/');

                }
            });

        }
    }

})();