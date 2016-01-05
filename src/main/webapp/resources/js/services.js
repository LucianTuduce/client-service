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
            $rootScope.GlobalAuthorization = authdata;
           // console.log($rootScope.GlobalAuthorization);
           // console.log(authdata);
        };


        service.ClearCredentials = function () {
            $http.defaults.headers.common.Authorization = ''; //delete the token          
        };

        service.LogOut = function (callback) {
            $http.post('http://localhost:9080/client-service/rest/user/logout', {})
                .success(function (response, status, headers, config) {
                    callback(response, status, headers().authorization, config); // header() function because header is returned as a function

                }).error(function (response, status, headers, config) {
                    callback(response, status, headers().authorization, config);
                });

        }

        return service;
    }]);



angular.module('UVSClientApp').factory('AddCarService', ['$http', '$rootScope', '$timeout', 
    function ($http, $rootScope, $timeout) {
        var service = {};

        service.AddCar = function (FIN, OwnerName, DealerName, Country, VehicleType, Model, FabricationYear, Price, FuelType, Capacity, Weight, Height, Length, Suspension, TireCondition, callback) {
            $http.post('http://localhost:9080/client-service/rest/vehicle/add', {

                    owner: OwnerName,
                    dealer: DealerName,
                    bodyHeight: Height,
                    bodyWeight: Weight,
                    bodyLength: Length,
                    suspensionType: Suspension,
                    tireCondition: TireCondition,
                    vehicle: {
                        fin: FIN,
                        model: Model,
                        fuelType: FuelType,
                        engineCapacity: Capacity,
                        year: FabricationYear,
                        location: Country,
                        //location: Scopes.get('HeaderController').Country,
                        price: Price,
                        vehicleType: VehicleType
                    }
                } , {
                    headers: {
                        'Authorization': $rootScope.GlobalAuthorization
                    }
                }  )
                .success(function (response, status, headers, config) {
                    callback(response, status, headers().authorization, config); // header() function because header is returned as a function

                }).error(function (response, status, headers, config) {
                    callback(response, status, headers().authorization, config);
                });
        };
       // console.log($rootScope.GlobalAuthorization);
        return service;
    }]);



angular.module('UVSClientApp').factory('CarSearchService', ['$http', '$rootScope', '$timeout',
    function ($http, $rootScope, $timeout) {
        var service = {};

        service.CarSearch = function (FINVar, modelVar, FuelTypeVar, CapacityMinVar, CapacityMaxVar, YearMinVar, YearMaxVar, PriceMinVar, PriceMaxVar, CountryVar, VehicleTypeVar, callback) {
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
                YearMaxVar = 2016;
            }
            if (PriceMinVar == undefined || PriceMinVar == '') {
                PriceMinVar = 0;
            }
            if (PriceMaxVar == undefined || PriceMaxVar == '') {
                PriceMaxVar = 0;
            }

            //console.log(VehicleTypeVar);

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
        
        
        
        service.SaveCarSearch = function (FINVar, modelVar, FuelTypeVar, CapacityMinVar, CapacityMaxVar, YearMinVar, YearMaxVar, PriceMinVar, PriceMaxVar, CountryVar, VehicleTypeVar,SaveNameVar, callback) {
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
                YearMaxVar = 2016;
            }
            if (PriceMinVar == undefined || PriceMinVar == '') {
                PriceMinVar = 0;
            }
            if (PriceMaxVar == undefined || PriceMaxVar == '') {
                PriceMaxVar = 0;
            }


            $http.post('http://localhost:9080/client-service/rest/vehicle/search/history/save/'+SaveNameVar, {
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
                
            }).success(function (response, status, headers, config) {
                callback(response, status, headers().authorization, config);
               //$rootScope.carsRetrieved = response.vehicles; //store the results
               $rootScope.$emit("SaveCarSearch", {}); //trigger function on CarResultController

            }).error(function (response, status, headers, config) {
                callback(response, status, headers().authorization, config);
            });
        };
        
        service.UpdateSearchFormFunction = function (SearchVar) {
            // split the string that was delivered from the server
            var SearchTempArrayVar = SearchVar.split(", "); //split the parameters
            var SearchArrayVar= [];
            for(var i=0;i<SearchTempArrayVar.length; i++) //split parameter value from parameter name
                {
                    var temp = SearchTempArrayVar[i].split(": ");
                    SearchArrayVar.push(temp[1]); //get value                    
                    
                }
            console.log(SearchArrayVar);
            
            return SearchArrayVar;
            
        
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

angular.module('UVSClientApp').factory('EnhancedVehicleService', ['$http', '$rootScope', '$timeout',
    function ($http, $rootScope, $timeout) {
        var service = {};

      service.GetCarInfo = function (FINVar, callback) {
            $http.get('http://localhost:9080/client-service/rest/vehicle/enhanced/'+FINVar)
                .success(function (response, status, headers, config) {
                callback(response, status, headers().authorization, config);                
                //$rootScope.$emit("CarSearchMethod", {}); //trigger function on CarResultController

            }).error(function (response, status, headers, config) {
                callback(response, status, headers().authorization, config);
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
