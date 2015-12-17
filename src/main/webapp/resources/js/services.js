angular.module('UVSClientApp')  
.factory('AuthenticationService',
    ['$http', '$rootScope', '$timeout',
    function ( $http, $rootScope, $timeout) {
        var service = {};
 
        service.Login = function (username, password, callback) {           
 
            /* Use this for real authentication
             ----------------------------------------------*/
            $http.post('http://localhost:9080/client-service/rest/user/login', { username: username, password: password })
                .success(function (response, status, headers, config) {
                  callback(response, status, headers().authorization, config); // header() function because header is returned as a function
                  
               }).error(function (response, status, headers, config) {
                  callback(response, status, headers().authorization, config);
               });
 
        };
  
        service.SetCredentials = function (authorization) {
            var authdata = authorization;             
            $http.defaults.headers.common['Authorization'] = authdata; // here the token is saved for future use           
        };
        
  
        service.ClearCredentials = function () { 
            $http.defaults.headers.common.Authorization = '';  //delete the token          
        };
  
        return service;
    }]);


angular.module('UVSClientApp')
/*  */

/* factory to connect controllers */
.factory('Scopes', function($rootScope) {
    var mem = {};
    return {
        store: function(key, value) {
            mem[key] = value;
        },
        get: function(key) {
            return mem[key];
        }
    };
});