angular.module('UVSClientApp')
    .controller('LoginController', ['$scope', '$rootScope', '$location', 'AuthenticationService',
    function ($scope, $rootScope, $location, AuthenticationService) {
            // reset login status
            AuthenticationService.ClearCredentials();

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
    .controller('HeaderController', function ($scope, $rootScope, Scopes) {
        Scopes.store('HeaderController', $scope);

        var VehicleTypeVar = "";
        var CountryVar = "";
        var LanguageVar = "";
        $scope.VehicleTypeSelect = function (event) {
            VehicleTypeVar = event.target.text;
            $scope.vehicleType = VehicleTypeVar;
            var parentElement = angular.element(document.querySelector('#VehicleType'));            
            parentElement.html(VehicleTypeVar);
        };

        $scope.CountrySelect = function (event) {
            CountryVar = event.target.text;
            $scope.Country = CountryVar;
            var parentElement = angular.element(document.querySelector('#CountryLanguage'));            
            parentElement.html(CountryVar);
        };

        $scope.LanguageSelect = function (event) {
            LanguageVar = event.target.text;
            $scope.Language = LanguageVar;
            var parentElement = angular.element(document.querySelector('#CountryLanguage'));
            parentElement.html(LanguageVar);

        };
    });


angular.module('UVSClientApp')
    .controller('CarSearchController', ['$scope', 'Scopes', 'CarSearchService', function ($scope, Scopes, CarSearchService) {
        Scopes.store('CarSearchController', $scope);
        $scope.cars = [];

        $scope.search = function () {
            CarSearchService.CarSearch($scope.FIN, $scope.model, $scope.FuelType, $scope.CapacityMin, $scope.CapacityMax, $scope.YearMin, $scope.YearMax, $scope.PriceMin, $scope.PriceMax, Scopes.get('HeaderController').Country, Scopes.get('HeaderController').vehicleType, function (response, status, headers, config) {
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
    .controller('SearchHistoryController', ['$scope', '$rootScope','SearchHistoryService', function ($scope, $rootScope, SearchHistoryService) {
        //get search history on page load
        SearchHistoryService.GetSearchHistory(function(response) {
            $scope.searches = response.data;            
        });        
        
        //reload the search history once a new search is made
        $rootScope.$on("CarSearchMethod", function () { //Listen for trigger
            SearchHistoryService.GetSearchHistory(function(response) {
            $scope.searches = response.data;            
        });
        })
    }]);


angular.module('UVSClientApp')
    .controller('CarResultController', function ($scope, $rootScope) {
        //load the search results once a new search is made
        $rootScope.$on("CarSearchMethod", function () { //Listen for trigger
            $scope.cars = $rootScope.carsRetrieved;
           
        });
    });