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
        dataVm.comment = null;
        var currRating = 0;
        var currRaters = 0;
        dataVm.addWatched = addWatched;
        dataVm.addComment = addComment;
        dataVm.updateRatings = updateRatings;
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
            currRating = video.ratings;
            currRaters = video.raters;
            dataVm.disableratebtn=false;
        }

        function addComment(user) {
            /* Set commenter*/
            dataVm.comment.commenter = user;
            console.log("Reached here");
            dataVm.currentVideo.comments.push(dataVm.comment);
            videoService.update(dataVm.currentVideo.id, dataVm.currentVideo)
                .then(function (response) {
                    dataVm.currentVideo = response;
                }, function (error) {
                    console.log(error);
                })
        }

        function updateRatings() {
            dataVm.disableratebtn = true;
            console.log("Ratings:" + currRating);
            console.log("Raters:" + currRaters);
            /* Update the ratings */
            dataVm.currentVideo.raters = dataVm.currentVideo.raters + 1;
            dataVm.currentVideo.rating = ((currRating * currRaters) + dataVm.currentVideo.ratings) / dataVm.currentVideo.raters;

            videoService.update(dataVm.currentVideo.id, dataVm.currentVideo)
                .then(function (response) {
                    dataVm.currentVideo = response;
                }, function (error) {
                    console.log("Error updating ratings");
                })

        }

    }


})();