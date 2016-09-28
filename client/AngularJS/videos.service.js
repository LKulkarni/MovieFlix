/**
 * Created by Loukik on 28-Sep-16.
 */
(function () {


    angular.module('app')
        .service('videoService', videoService);


    videoService.$inject = ['$http', '$q'];
    function videoService($http, $q) {

        var self = this;

        self.findAll = getVideos;
        self.findOne = getVideo;
        self.addNew = addVideo;
        self.update = updateVideo;
        self.deleteOne = deleteVideo;

        function getVideos() {
            return $http
            ({
                method: 'GET',
                url: 'http://localhost:8080/RestAPI/app/videos'
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

        function getVideo() {

        }

        function addVideo() {

        }

        function updateVideo() {

        }

        function deleteVideo() {

        }

    }//end of subscriptionService


})();
