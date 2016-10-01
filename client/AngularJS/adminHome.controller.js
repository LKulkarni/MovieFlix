/**
 * Created by Loukik on 30-Sep-16.
 */

(function () {
    angular.module("movieflix")
        .controller("admin_homeController", admin_homeController);

    admin_homeController.$inject = ['userService', 'videoService'];
    function admin_homeController(userService, videoService) {

        var dataVm = this;
        dataVm.users;
        dataVm.videos;
        dataVm.deleteVideo = deleteVideo;
        dataVm.deleteUser = deleteUser;

        init();

        function init() {
            console.log("Admin Home Controller Running")
            getUsers();
            getVideos()
            console.log(dataVm.users);
            console.log(dataVm.videos);
        }


        function getUsers() {
            userService.findAll()
                .then(function (response) {
                    dataVm.users = response;
                }, function (error) {
                    Console.log(error);
                });
        }


        function getVideos() {
            videoService.findAll()
                .then(function (response) {
                    dataVm.videos = response;
                }, function (error) {
                    console.log(error);
                });

        }

        function deleteVideo(id) {
            videoService.deleteOne(id)
                .then(function (response) {
                        console.log("Video " + id + " successfully deleted")
                    },
                    function (error) {
                        console.log("Video " + id + " delete failed")
                    });
            getVideos();
        }

        function deleteUser(id) {
            userService.deleteOne(id)
                .then(function (response) {
                        console.log("User " + id + " successfully deleted")
                    },
                    function (error) {
                        console.log("User " + id + " delete failed")
                    });
            getUsers();
        }

    }
})();