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
        dataVm.years = [];
        dataVm.setCurrentVideo = setCurrentVideo;
        dataVm.deleteVideo = deleteVideo;
        dataVm.deleteUser = deleteUser;
        dataVm.addVideo = addVideo;
        dataVm.updateVideo = updateVideo;

        init();

        function init() {
            console.log("Admin Home Controller Running")
            getUsers();
            getVideos();
            initForm();
        }


        function initForm() {
            for (i = 1970; i <= new Date().getFullYear(); i++) {
                dataVm.years.push(i);
            }
            dataVm.filmRatings = ['G', 'PG', 'PG-13', 'R', 'NC-17', 'N/A', 'TV-14', 'TV-MA', 'TV-PG'];
            dataVm.langs = ['English', 'Russian', 'Hindi', 'French', 'German', 'Chinese', 'Spanish', 'Swahili',
                'Xhosa', 'Zulu', 'Mandarin', 'Sindarin', 'Old English', 'Italian', 'Arabic', 'Japanese', 'Latin', 'Hebrew',
                'Polish', 'Scottish Gaelic', 'Portuguese', 'Korean'];
            dataVm.genres = ['Action', 'Comedy', 'SCI-FI', 'Adventure', 'Thriller', 'Mystery', 'Drama', 'Biography', 'Crime',
                'Animation', 'Fantasy', 'War', 'News', 'Talk-show', 'History', 'Documentary', 'Romance', 'Western'];
            dataVm.countries = ['USA', 'UK', 'Russia', 'India', 'France', 'Germany', 'China', 'Australia', 'New Zealand', 'Canada', 'Ireland'];
            dataVm.types = ['movie', 'series'];
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


        function addVideo() {
            /* Conversion for multi-select (array to string)*/
            dataVm.newVideo.genre = dataVm.newVideo.genre.join(', ');
            dataVm.newVideo.language=dataVm.newVideo.language.join(', ');
            dataVm.newVideo.country= dataVm.newVideo.country.join(', ');

            videoService.addNew(dataVm.newVideo)
                .then(function (response) {
                    console.log('Movie added successfully id: ' + response.id);
                    dataVm.newVideo = null;
                }, function (error) {
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

        function setCurrentVideo(video) {

            dataVm.currentVideo = video;

            dataVm.currentVideo.year=parseInt(video.year);

            /* Conversion for date*/
            dataVm.currentVideo.released=new Date(video.released);

            /* Conversion for multi-select (string to array)*/
            dataVm.currentVideo.language= video.language.split(", ");
            dataVm.currentVideo.genre=video.genre.split(", ");
            dataVm.currentVideo.country=video.country.split(", ");
        }

        function updateVideo() {
            dataVm.updatedVideo=dataVm.currentVideo;
            /* Conversion for multi-select (array to string)*/
            dataVm.updatedVideo.language=dataVm.currentVideo.language.join(", ");
            dataVm.updatedVideo.genre=dataVm.currentVideo.genre.join(", ");
            dataVm.updatedVideo.country=dataVm.currentVideo.country.join(", ");

            videoService.update(dataVm.updatedVideo.id,dataVm.updatedVideo)
                .then(function (response) {
                    console.log(response.id+" updated successfully");
                    dataVm.updatedVideo=null;
                    $
                },function (error) {
                    console.log("failed to update "+dataVm.updatedVideo.id);
                })



        }

    }
})();