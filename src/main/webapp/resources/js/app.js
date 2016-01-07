//'use strict';
 
// declare modules
angular.module('UVSClientApp', []);

 
angular.module('loginApp', [
    'UVSClientApp', 'ngRoute', 'ui.bootstrap'
])
  
.config(['$routeProvider', function ($routeProvider) {
 
    $routeProvider
        .when('/', {
            controller: 'LoginController',
            templateUrl: 'login.html'
        })
  
        .when('/index', {
            controller: 'HeaderController',
            templateUrl: 'index.html'
        })
        .when('/close', {
            controller: 'AddCarController',
            templateUrl: 'resources/js/windowclose.xhtml'
        })
    .when('/add', {
            controller: 'AddCarController',
            templateUrl: 'addCar.html'
        })
  
        .otherwise({ redirectTo: '/' });
}]);
  
/*.run(['$rootScope', '$location', '$cookieStore', '$http',
    function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
  
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
            //if ($location.path() !== '/' && !$rootScope.globals.currentUser) {
            //    $location.path('/');
           // }
        });
    }]); */


  






