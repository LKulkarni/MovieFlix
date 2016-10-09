/**
 * Created by Loukik on 28-Sep-16.
 */
(function () {

    angular.module('movieflix')
        .controller('user_homeController', user_homeController);

    user_homeController.$inject = ['userService', 'videoService']
    function user_homeController(userService, videoService) {
        var dataVm = this;
        dataVm.currentVideo = null;
        dataVm.addWatched = addWatched;
        dataVm.setCurrent = setCurrentMovie;


        init();

        function init() {
            videoService.findAll()
                .then(function (data) {
                    dataVm.allVideos = data;
                }, function (error) {
                    console.log(error);
                });
        }

        // add to watched list of user
        function addWatched(user, video) {
            user.watched.push(video)
            userService.update(user.id, user)
                .then(function (response) {
                    dataVm.user = response;
                }, function (error) {
                    console.log(error);
                });
        }

        // set current movie for viewing info/watching
        function setCurrentMovie(video) {
            dataVm.currentVideo = video;
        }


    }


})();