/**
 * Created by Loukik on 27-Sep-16.
 */
(function () {


    angular.module('movieflix', ['ngMessages', 'ngRoute', 'ngCookies', 'ngAnimate'])
        .config(moduleConfig)
        .run(moduleRun);

    moduleRun.$inject = ['$rootScope', '$location', '$cookies', '$http'];
    function moduleRun($rootScope, $location, $cookies, $http) {
        console.log('Movieflix started');

        // keep user logged in after page refresh
        $rootScope.globals = $cookies.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            //var restrictedPage = $.inArray($location.path(), ['/adminhome', '/userhome']) === 1;
            var restrictedPage = $location.path() == '/adminhome' || $location.path() == '/userhome';
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/');
            }
        });

    }

    moduleConfig.$inject = ['$routeProvider', '$locationProvider', '$httpProvider'];
    function moduleConfig($routeProvider, $locationProvider, $httpProvider) {
        $httpProvider.defaults.cache = false;


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
                    templateUrl: '../UI/user_registration.Templ.html',
                    controller: 'user_registrationController',
                    controllerAs: 'regCtrl'
                }
            )
            .when('/userhome',
                {
                    templateUrl: '../UI/user_movies.Templ.html',
                    controller: 'user_homeController',
                    controllerAs: 'homeCtrl'
                }
            )

            .when('/adminhome',
                {
                    templateUrl: '../UI/admin_home.Templ.html',
                    controller: 'admin_homeController',
                    controllerAs: 'adminHomeCtrl'
                }
            )

            .otherwise(
                {
                    redirectTo: '/'
                }
            )
    }
})();