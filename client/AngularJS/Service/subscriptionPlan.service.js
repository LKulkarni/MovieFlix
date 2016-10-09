/**
 * Created by Loukik on 27-Sep-16.
 */
(function () {


    angular.module('movieflix')
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

        function getPlan(id) {
            return $http
            ({
                method: 'GET',
                url: 'http://localhost:8080/RestAPI/app/userplans/' + id
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

        function addPlan(plan) {
            return $http
            ({
                method: 'POST',
                url: 'http://localhost:8080/RestAPI/app/userplans',
                data: plan
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

        function updatePlan(id, plan) {
            return $http
            ({
                method: 'PUT',
                url: 'http://localhost:8080/RestAPI/app/userplans/' + id,
                data: plan
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

        function deletePlan(id) {
            return $http
            ({
                method: 'DELETE',
                url: 'http://localhost:8080/RestAPI/app/userplans/' + id
            })
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                });
        }

    }//end of subscriptionService


})();
