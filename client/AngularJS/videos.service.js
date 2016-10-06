/**
 * Created by Loukik on 28-Sep-16.
 */
(function () {


    angular.module('movieflix')
        .service('videoService', videoService);


    videoService.$inject = ['$http', '$q'];
    function videoService($http, $q) {

        var self = this;

        self.findAll = getVideos;
        self.findFew = getFewVideos;
        self.findOne = getVideo;
        self.addNew = addVideo;
        self.update = updateVideo;
        self.deleteOne = deleteVideo;

        function getVideos() {
            return $http
            ({
                method: 'GET',
                cache:false,
                url: 'http://localhost:8080/RestAPI/app/videos'
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }


        function getFewVideos(count) {
            return $http
            ({
                method: 'GET',
                cache:false,
                url: 'http://localhost:8080/RestAPI/app/videos/few/' + count
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

        function getVideo(id) {
            return $http
            ({
                method: 'GET',
                cache:false,
                url: 'http://localhost:8080/RestAPI/app/videos/' + id
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

        function addVideo(video) {
            return $http
            ({
                method: 'POST',
                url: 'http://localhost:8080/RestAPI/app/videos',
                data: video
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

        function updateVideo(id, video) {
            return $http
            ({
                method: 'PUT',
                url: 'http://localhost:8080/RestAPI/app/videos/' + id,
                data: video
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

        function deleteVideo(id) {
            return $http
            ({
                method: 'DELETE',
                url: 'http://localhost:8080/RestAPI/app/videos/' + id
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

    }//end of videoService


})();
