angular.module('UVSClientApp')
    .controller('LoginController', ['$scope', '$rootScope', '$location', 'AuthenticationService',
    function ($scope, $rootScope, $location, AuthenticationService) {
            // reset login status
            AuthenticationService.ClearCredentials();
            console.log("credentials reset");

            $scope.login = function () {
                //$scope.dataLoading = true;
                AuthenticationService.Login($scope.username, $scope.password, function (response, status, headers, config) {
                    if (status == 200) {
                        console.log("controller success");
                        AuthenticationService.SetCredentials(headers); //save token for future requests
                        $location.path('/index'); //go to index page
                    } else {
                        console.log("controller error");
                        $scope.error = "Username or passwords invalid";
                        // $scope.dataLoading = false;
                    }
                });
            };
    }]);


angular.module('UVSClientApp')
    .controller('HeaderController', ['$scope', '$rootScope', 'Scopes', 'CarSearchService', 'AuthenticationService', '$location', function ($scope, $rootScope, Scopes, CarSearchService, AuthenticationService, $location) {
        Scopes.store('HeaderController', $scope);
        $scope.VehicleType = "Vehicle Type";
        $scope.CountryLanguage = "Country/Language"
            //var VehicleTypeVar = "";
        var CountryVar = "";
        var LanguageVar = "";
        $scope.VehicleTypeSelect = function (event) {
            $scope.VehicleType = event.target.text;
        };

        $scope.CountrySelect = function (event) {
            $scope.CountryLanguage = event.target.text;
            $scope.Country = event.target.text; //only to pass on the the POST request
        };

        $scope.LanguageSelect = function (event) {
            $scope.CountryLanguage += "/" + event.target.text; //keep the country, add the language          
        };

        $scope.ShowAddCar = function () {
            $rootScope.$emit("ShowAddCar", {});
        };

        $scope.logout = function () {
            AuthenticationService.LogOut(function (response, status, headers, config) {
                if (status == 200) {
                    $location.path('/'); //go to login page
                } else {
                    console.log("Logout unsuccessful");
                    $scope.error = "Logout unsuccessful";
                    // $scope.dataLoading = false;
                }
            });
        };
    }]);



angular.module('UVSClientApp')
    .controller('AddCarController', ['$scope', '$rootScope', 'Scopes', 'AddCarService', '$location', function ($scope, $rootScope, Scopes, AddCarService, $location) {
        $rootScope.$on("ShowAddCar", function () { //Listen for trigger
            $scope.AddCarVar = 1;
        });


        $scope.addCar = function () {
            //$scope.dataLoading = true;
            AddCarService.AddCar($scope.FIN, $scope.OwnerName, $scope.DealerName, $scope.Country, $scope.VehicleType, $scope.Model, $scope.FabricationYear, $scope.Price, $scope.FuelType, $scope.Capacity, $scope.Weight, $scope.Height, $scope.Length, $scope.Suspension, $scope.TireCondition, function (response, status, headers, config) {
                if (status == 200) {
                    console.log("add car success");
                    $scope.AddCarVar = 0;

                } else {
                    console.log("add car error");
                }
            });
        };
    }]);



angular.module('UVSClientApp')
    .controller('CarSearchController', ['$scope', 'Scopes', 'CarSearchService', function ($scope, Scopes, CarSearchService) {
        Scopes.store('CarSearchController', $scope);
        $scope.cars = [];

        $scope.search = function () {
            CarSearchService.CarSearch($scope.FIN, $scope.model, $scope.FuelType, $scope.CapacityMin, $scope.CapacityMax, $scope.YearMin, $scope.YearMax, $scope.PriceMin, $scope.PriceMax, Scopes.get('HeaderController').Country, Scopes.get('HeaderController').VehicleType, function (response, status, headers, config) {
                if (status == 200) {
                    console.log("Car Search Result success");
                    //$scope.carsRetrieved = response.vehicles;
                } else {
                    console.log("Car Search Result could not be retrieved");
                }
            })
        };
           }]);



angular.module('UVSClientApp')
    .controller('SearchHistoryController', ['$scope', '$rootScope', 'SearchHistoryService', function ($scope, $rootScope, SearchHistoryService) {
        //get search history on page load
        SearchHistoryService.GetSearchHistory(function (response) {
            $scope.searches = response.data;
        });

        //reload the search history once a new search is made
        $rootScope.$on("CarSearchMethod", function () { //Listen for trigger
            SearchHistoryService.GetSearchHistory(function (response) {
                $scope.searches = response.data;
            });
        })
    }]);


angular.module('UVSClientApp')
    .controller('CarResultController', ['$scope', 'Scopes', '$rootScope', 'EnhancedVehicleService', function ($scope, Scopes, $rootScope, EnhancedVehicleService) {
        //load the search results once a new search is made
        Scopes.store('CarResultController', $scope);
        $rootScope.$on("CarSearchMethod", function () { //Listen for trigger
            $scope.cars = $rootScope.carsRetrieved;
        });

        $scope.CarInfo = function (FIN) {
            EnhancedVehicleService.GetCarInfo(FIN, function (response, status, headers, config) {
                if (status == 200) {
                    console.log("Car info success");
                    console.log(response.vehicleEnhanceds);
                    $scope.VehicleInfo = response.vehicleEnhanceds;
                    $rootScope.$emit("CarExtraInfo", {}); //trigger function on CarResultController
                } else {
                    console.log("Car info could not be retrieved");
                }
            });
        };
    }]);




angular.module('UVSClientApp')
    .controller('CarExtraInfoController', ['$scope', '$rootScope', 'Scopes', 'EnhancedVehicleService', function ($scope, $rootScope, Scopes, EnhancedVehicleService) {
        //load the search results once a new search is made
        $rootScope.$on("CarExtraInfo", function () { //Listen for trigger
            $scope.CarExtraInfo = Scopes.get('CarResultController').VehicleInfo; //get car extra info
            $scope.CarStandardInfo = $scope.CarExtraInfo[0].vehicle;
            console.log($scope.CarExtraInfo);
            console.log($scope.CarStandardInfo);
            $scope.fin = $scope.CarExtraInfo[0].vehicle.fin;
            $scope.owner = $scope.CarExtraInfo[0].owner;
            $scope.dealer = $scope.CarExtraInfo[0].dealer;
            $scope.vehicleType = $scope.CarExtraInfo[0].vehicle.vehicleType;
            $scope.model = $scope.CarExtraInfo[0].vehicle.model;
            $scope.bodyWeight = $scope.CarExtraInfo[0].bodyWeight;
            $scope.bodyHeight = $scope.CarExtraInfo[0].bodyHeight;
            $scope.bodyLenght = $scope.CarExtraInfo[0].bodyLenght;
            $scope.suspensionType = $scope.CarExtraInfo[0].suspensionType;
            $scope.tireCondition = $scope.CarExtraInfo[0].tireCondition;
            $scope.fuelType = $scope.CarExtraInfo[0].vehicle.fuelType;
            $scope.engineCapacity = $scope.CarExtraInfo[0].vehicle.engineCapacity;
            $scope.year = $scope.CarExtraInfo[0].vehicle.year;
            $scope.location = $scope.CarExtraInfo[0].vehicle.location;
            $scope.price = $scope.CarExtraInfo[0].vehicle.price;
        });
    }]);