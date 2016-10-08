/**
 * Created by Loukik on 28-Sep-16.
 */
(function () {

    angular.module('movieflix')
        .controller('user_registrationController', user_regController);

    user_regController.$inject = ['userService', 'subscriptionService','$location']
    function user_regController(userService, subscriptionService,$location) {
        var dataVm = this;
        dataVm.mm=[];
        dataVm.yyyy=[];

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


        function initComponents()
        {
            for(i=1;i<=12;i++)
            {
                dataVm.mm.push(i);
            }
            for(i=0;i<10;i++)
            {
                dataVm.yyyy.push(new Date().getFullYear()+i);
            }
        }

        function createUser() {

           dataVm.user.userPlan=JSON.parse(dataVm.selectedUserPlan);
           dataVm.user.userPayment.expiryDate=new Date('01-'+dataVm.cardMM+'-'+dataVm.cardYYYY);
            userService.addNew(dataVm.user)
                .then(function (data) {
                    dataVm.user = data;
                    console.dir("Saved"+ dataVm.user);
                    $location.path('/');
                }, function (error) {
                    console.log(error);
                });

        }
    }

})();