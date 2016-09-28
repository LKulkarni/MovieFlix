/**
 * Created by Loukik on 27-Sep-16.
 */
(function () {


    angular.module('app')
        .service('subscriptionService', subscriptionService);


    subscriptionService.$inject = ['$http', '$q'];
    function subscriptionService($http, $q) {

        var self = this;

        self.findAll = getPlans;
        self.findOne = getPlan;
        self.addNew = addPlan;
        self.update = updatePlan;
        self.deleteOne = deletePlan;

        function getPlans() {
            return $http
            ({
                method: 'GET',
                url: 'http://localhost:8080/RestAPI/app/userplans'
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

        function getPlan() {

        }

        function addPlan() {

        }

        function updatePlan() {

        }

        function deletePlan() {

        }

    }//end of subscriptionService


})();
