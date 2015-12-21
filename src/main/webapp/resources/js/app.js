'use strict';
 
// declare modules
angular.module('UVSClientApp', []);
 
angular.module('loginApp', [
    'UVSClientApp', 'ngRoute'
])
  
.config(['$routeProvider', function ($routeProvider) {
 
    $routeProvider
        .when('/', {
            controller: 'LoginController',
            templateUrl: 'login.xhtml'
        })
  
        .when('/index', {
            controller: 'HeaderController',
            templateUrl: 'index.xhtml'
        })
        .when('/close', {
            controller: 'AddCarController',
            templateUrl: 'resources/js/windowclose.xhtml'
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


  






