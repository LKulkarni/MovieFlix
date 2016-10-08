/**
 * Created by Loukik on 27-Sep-16.
 */
(function () {


    angular.module('movieflix', ['ngMessages', 'ngRoute', 'ngCookies','ngAnimate'])
        .config(moduleConfig)
        .run(moduleRun);

    moduleRun.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
    function moduleRun() {
        console.log('Movieflix started');
        run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
        function run($rootScope, $location, $cookieStore, $http) {
            // keep user logged in after page refresh
            $rootScope.globals = $cookieStore.get('globals') || {};
            if ($rootScope.globals.currentUser) {
                $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
            }

            $rootScope.$on('$locationChangeStart', function (event, next, current) {
                // redirect to login page if not logged in and trying to access a restricted page
                var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
                var loggedIn = $rootScope.globals.currentUser;
                if (restrictedPage && !loggedIn) {
                    $location.path('/login');
                }
            });
        }
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
                    templateUrl: '../UI/userRegistration.Templ.html',
                    controller: 'user_registrationController',
                    controllerAs: 'regCtrl'
                }
            )
            .when('/userhome',
                {
                    templateUrl: '../UI/userMovies.Templ.html',
                    controller: 'user_homeController',
                    controllerAs: 'homeCtrl'
                }
            )

            .when('/admin/home',
                {
                    templateUrl: '../UI/adminHome.Templ.html',
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