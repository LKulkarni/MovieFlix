/**
 * Created by Loukik on 27-Sep-16.
 */
(function () {


    angular.module('movieflix', ['ngMessages', 'ngRoute','ngCookies'])
        .config(moduleConfig)
        .run(moduleRun);

    function moduleRun() {
        console.log('Movieflix started');
    }

    moduleConfig.$inject=['$routeProvider','$locationProvider'];
    function moduleConfig($routeProvider,$locationProvider) {
        $routeProvider
            .when('/',
                {
                    templateUrl: '../UI/home_main.Templ.html',
                    controller: 'homeController',
                    controllerAs: 'ctrl'
                }
            )
            .when('/register',
                {
                    templateUrl: '../UI/userRegistration.Templ.html',
                    controller: 'user_registrationController',
                    controllerAs: 'regCtrl'
                }
            )
            .when('/userhome',
                {
                    templateUrl:'../UI/userMovies.Templ.html',
                    controller: 'user_homeController',
                    controllerAs: 'homeCtrl'
                }
            )

            .otherwise(
                {
                    redirectTo:'/'
                }
            )
    }
})();