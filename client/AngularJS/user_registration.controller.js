/**
 * Created by Loukik on 28-Sep-16.
 */
(function () {

    angular.module('movieflix')
        .controller('user_registrationController', user_regController);

    user_regController.$inject = ['userService', 'subscriptionService']
    function user_regController(userService, subscriptionService) {
        var dataVm = this;

        dataVm.createUser = createUser;
        init();

        function init() {
            subscriptionService.findAll()
                .then(function (data) {
                    dataVm.allPlans = data;
                }, function (error) {
                    console.log(error);
                });
        }

        function createUser() {
           dataVm.user.userPlan=JSON.parse(dataVm.selectedUserPlan);
            userService.addNew(dataVm.user)
                .then(function (data) {
                    dataVm.user = data;
                    console.dir("Saved"+ dataVm.user);
                }, function (error) {
                    console.log(error);
                });

        }
    }

})();