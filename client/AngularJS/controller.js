/**
 * Created by Loukik on 27-Sep-16.
 */
(function () {


    angular.module('app')
        .controller('controller1', controller1);

    controller1.$inject = ['subscriptionService', 'videoService'];

    function controller1(subscriptionService, videoService) {
        var dataVm = this;

        init();

        function init() {
            subscriptionService.findAll()
                .then(function (data) {
                    dataVm.allPlans = data;
                    console.dir(dataVm.allPlans);
                }, function (error) {
                    console.log(error);
                });


            videoService.findAll()
                .then(function (data) {
                    var videos = data.slice(1, 5);
                    var index = Math.floor(Math.random() * videos.length);
                    dataVm.allVideos = videos.slice(index, index + 3);
                    console.dir(dataVm.allVideos);
                }, function (error) {
                    console.log(error);
                });
        }
    }

})();