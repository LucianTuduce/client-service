angular.module('UVSClientApp').factory('AuthenticationService', ['$http', '$rootScope', '$timeout',
    function ($http, $rootScope, $timeout) {
        var service = {};

        service.Login = function (username, password, callback) {

            /* Use this for real authentication
             ----------------------------------------------*/
            $http.post('http://localhost:9080/client-service/rest/user/login', {
                    username: username,
                    password: password
                })
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
            $http.defaults.headers.common.Authorization = ''; //delete the token          
        };

        return service;
    }]);


angular.module('UVSClientApp').factory('CarSearchService', ['$http', '$rootScope', '$timeout',
    function ($http, $rootScope, $timeout) {
        var service = {};        

        service.CarSearch = function (FINVar, modelVar, FuelTypeVar, CapacityMinVar, CapacityMaxVar, YearMinVar, YearMaxVar, PriceMinVar, PriceMaxVar, CountryVar,VehicleTypeVar, callback) {
            if (FINVar == undefined || FINVar == '') {
                FINVar = " ";
            }
            if (modelVar == undefined) {
                modelVar = " ";
            }
            if (FuelTypeVar == undefined) {
                FuelTypeVar = "DEFAULT";
            }
            if (CapacityMinVar == undefined || CapacityMinVar == '') {
                CapacityMinVar = 0;
            }
            if (CapacityMaxVar == undefined || CapacityMaxVar == '') {
                CapacityMaxVar = 30000;
            }
            if (YearMinVar == undefined || YearMinVar == '') {
                YearMinVar = 1900;
            }
            if (YearMaxVar == undefined || YearMaxVar == '') {
                YearMaxVar = 2015;
            }
            if (PriceMinVar == undefined || PriceMinVar == '') {
                PriceMinVar = 0;
            }
            if (PriceMaxVar == undefined || PriceMaxVar == '') {
                PriceMaxVar = 0;
            }
            
            console.log(VehicleTypeVar);

            $http.post('http://localhost:9080/client-service/rest/vehicle/filtered', {
                fin: FINVar,
                model: modelVar,
                fuelType: FuelTypeVar,
                minCapacity: CapacityMinVar,
                maxCapacity: CapacityMaxVar,
                minYear: YearMinVar,
                maxYear: YearMaxVar,
                location: CountryVar,
                //location: Scopes.get('HeaderController').Country,
                minPrice: PriceMinVar,
                maxPrice: PriceMaxVar,
                vehicleType: VehicleTypeVar,
                //vehicleType: Scopes.get('HeaderController').vehicleType,
                pagination: {
                    pageNumber: 1,
                    elemetsPerPage: 20
                }
            }).success(function (response, status, headers, config) {
                callback(response, status, headers().authorization, config);
                $rootScope.carsRetrieved = response.vehicles; //store the results
                $rootScope.$emit("CarSearchMethod", {}); //trigger function on CarResultController

            }).error(function (response, status, headers, config) {
                callback(response, status, headers().authorization, config);
            });
        };
        return service;
                }]);



angular.module('UVSClientApp').factory('SearchHistoryService', ['$http', '$rootScope', '$timeout',
    function ($http, $rootScope, $timeout) {
        var service = {};

        service.GetSearchHistory = function (callback) {
            $http({
                method: 'GET',
                url: 'http://localhost:9080/client-service/rest/vehicle/search/history'
            }).then(function (response) {
                callback(response);
                //$scope.searches = response.data;
            });
        };
        return service;
    }]);


/* factory to connect controllers */
angular.module('UVSClientApp').factory('Scopes', function ($rootScope) {
    var mem = {};
    return {
        store: function (key, value) {
            mem[key] = value;
        },
        get: function (key) {
            return mem[key];
        }
    };
});