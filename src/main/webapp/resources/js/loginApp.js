'use strict';
 
// declare modules
angular.module('loginApp', []);
//angular.module('Home', []);
 
angular.module('BasicHttpAuthExample', [
    'loginApp', 'ngRoute',
    'ngCookies'
])
  
/*.config(['$routeProvider', function ($routeProvider) {
 
    $routeProvider
        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'modules/authentication/views/login.html'
        })
  
        .when('/', {
            controller: 'HomeController',
            templateUrl: 'modules/home/views/home.html'
        })
  
        .otherwise({ redirectTo: '/login' });
}]) */
  
.run(['$rootScope', '$location', '$cookieStore', '$http',
    function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
  
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
            if ($location.path() !== '/' && !$rootScope.globals.currentUser) {
                $location.path('/');
            }
        });
    }]);


'use strict';
  
angular.module('loginApp')
  
.factory('AuthenticationService',
    ['$http', '$cookieStore', '$rootScope', '$timeout',
    function ( $http, $cookieStore, $rootScope, $timeout) {
        var service = {};
 
        service.Login = function (username, password, callback) {
 
             
 
            /* Use this for real authentication
             ----------------------------------------------*/
            $http.post('http://localhost:9080/client-service/rest/user/login', { username: username, password: password })
                .success(function (response) {
                  console.log("success");
                  //alert(response);
                   // console.log(response);
                  callback(response);
               }).error(function (response) {
                  console.log("error");
                  //alert(response);
                   // console.log(response);
                  callback(response);
               });
 
        };
  
        service.SetCredentials = function (username, password) {
            var authdata = "";
            //Base64.encode(username + ':' + password);
  
            $rootScope.globals = {
                currentUser: {
                    username: username,
                    authdata: authdata
                }
            };
  
            $http.defaults.headers.common['Authorization'] = 'Bearer  ' + authdata; // jshint ignore:line
            $cookieStore.put('globals', $rootScope.globals);
        };
  
        service.ClearCredentials = function () {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common.Authorization = 'Bearer  ';
        };
  
        return service;
    }])
  







'use strict';
  
angular.module('loginApp')
  
.controller('LoginController',
    ['$scope', '$rootScope', '$location', 'AuthenticationService',
    function ($scope, $rootScope, $location, AuthenticationService) {
        // reset login status
        AuthenticationService.ClearCredentials();
  
        $scope.login = function () {
            $scope.dataLoading = true;
            AuthenticationService.Login($scope.username, $scope.password, function(response) {
                console.log(response);
               /* if(response="User confirmed") {
                  console.log("contoller success");
                   console.log(response);
                    AuthenticationService.SetCredentials($scope.username, $scope.password);
                    $location.path('/');
                } else {
                    //$scope.error = response.message;
                    $scope.dataLoading = false;
                } */
            });
        };
    }]);





