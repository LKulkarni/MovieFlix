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
        dataVm.years=[];
        dataVm.deleteVideo = deleteVideo;
        dataVm.deleteUser = deleteUser;
        dataVm.addVideo=addVideo;
        init();

        function init() {
            console.log("Admin Home Controller Running")
            getUsers();
            getVideos();
            initForm();
        }


        function initForm() {
            for(i=1970;i<=new Date().getFullYear();i++)
            {
                dataVm.years.push(i);
            }
            dataVm.filmRatings=['G','PG','PG-13','R','NC-17'];
            dataVm.langs=['English','Russian','Hindi','French','German','Chinese'];
            dataVm.genres=['Action','Comedy','SCI-FI','Adventure','Thriller','Mystery','Drama','Biography','Crime',
            'Animation','Fantasy','War','News','Talk-show','History','Documentary','Romance','Western'];
            dataVm.countries=['USA','UK','Russia','India','France','Germany','China'];
            dataVm.types=['movie','series'];
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


        function addVideo()
        {
            dataVm.newVideo.genre=dataVm.newVideo.genre.join(', ');
            videoService.addNew(dataVm.newVideo)
                .then(function (response) {
                    console.log('Movie added successfully id: '+response.id);
                    dataVm.newVideo=null;
                },function (error) {
                    console.error('Failed to add movie');
                });

        }

        function deleteVideo(id) {
            videoService.deleteOne(id)
                .then(function (response) {
                        console.log("Video " + id + " successfully deleted")
                        getVideos();
                    },
                    function (error) {
                        console.log("Video " + id + " delete failed")
                    });

        }

        function deleteUser(id) {
            userService.deleteOne(id)
                .then(function (response) {
                        console.log("User " + id + " successfully deleted")
                        getUsers();
                    },
                    function (error) {
                        console.log("User " + id + " delete failed")
                    });

        }

    }
})();