/**
 * Created by Loukik on 28-Sep-16.
 */
(function () {

    angular.module('movieflix')
        .controller('user_registrationController', user_regController);

    user_regController.$inject = ['userService', 'subscriptionService', '$location']
    function user_regController(userService, subscriptionService, $location) {
        var dataVm = this;
        dataVm.mm = [];
        dataVm.yyyy = [];

        dataVm.createUser = createUser;
        init();

        function init() {
            subscriptionService.findAll()
                .then(function (data) {
                    dataVm.allPlans = data;
                }, function (error) {
                    console.log(error);
                });
            initComponents();
        }

        // initialize form components
        function initComponents() {
            // init month dropdown
            for (i = 1; i <= 12; i++) {
                dataVm.mm.push(i);
            }
            // init year dropdown
            for (i = 0; i < 10; i++) {
                dataVm.yyyy.push(new Date().getFullYear() + i);
            }
        }


        function createUser() {
            //convert userPlan object to json
            dataVm.user.userPlan = JSON.parse(dataVm.selectedUserPlan);
            // set user payment expiry date
            dataVm.user.userPayment.expiryDate = new Date('01-' + dataVm.cardMM + '-' + dataVm.cardYYYY);
            userService.addNew(dataVm.user)
                .then(function (data) {
                    dataVm.user = data;
                    console.dir("Saved" + dataVm.user);
                    $location.path('/');
                }, function (error) {
                    console.log(error);
                });

        }
    }

})();