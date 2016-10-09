/**
 * Created by Loukik on 27-Sep-16.
 */
(function () {


    angular.module('movieflix')
        .controller('homeController', homeController);

    homeController.$inject = ['subscriptionService', 'videoService'];

    function homeController(subscriptionService, videoService) {
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

            // get 3 random videos for homepage posters
            videoService.findFew(3)
                .then(function (data) {
                    dataVm.allVideos = data;
                    console.dir(dataVm.allVideos);
                }, function (error) {
                    console.log(error);
                });


        }


    }

})();