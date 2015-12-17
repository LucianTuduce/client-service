angular.module('UVSClientApp')  
.controller('LoginController',
    ['$scope', '$rootScope', '$location', 'AuthenticationService',
    function ($scope, $rootScope, $location, AuthenticationService) {
        // reset login status
        AuthenticationService.ClearCredentials();
  
        $scope.login = function () {
            //$scope.dataLoading = true;
            AuthenticationService.Login($scope.username, $scope.password, function(response, status, headers, config) {   
                if(status == 200) {
                  console.log("controller success");                  
                    AuthenticationService.SetCredentials(headers);  //save token for future requests
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
.controller('HeaderController', function($scope, $rootScope, Scopes) {
    Scopes.store('HeaderController', $scope);

    var VehicleTypeVar = "";
    var CountryVar = "";
    var LanguageVar = "";
    $scope.VehicleTypeSelect = function(event) {
        VehicleTypeVar = event.target.text;
        $scope.vehicleType = VehicleTypeVar;
        var parentElement = angular.element(document.querySelector('#VehicleType'));
        // parentElement.html('<span class="glyphicon glyphicon-home"></span><span class="caret"></span><br/>' + VehicleTypeVar);
        parentElement.html(VehicleTypeVar);
    };

    $scope.CountrySelect = function(event) {
        CountryVar = event.target.text;
        $scope.Country = CountryVar;
        var parentElement = angular.element(document.querySelector('#CountryLanguage'));
        //parentElement.html('<span class="glyphicon glyphicon-globe"></span><span class="caret"></span><br/>' + CountryVar);
        parentElement.html(CountryVar);
    };

    $scope.LanguageSelect = function(event) {
        LanguageVar = event.target.text;
        $scope.Language = LanguageVar;
        var parentElement = angular.element(document.querySelector('#CountryLanguage'));
        parentElement.html(LanguageVar);

    };
});



angular.module('UVSClientApp')
.controller('CarSearchController', function($scope, $rootScope, Scopes, $http) {
    Scopes.store('CarSearchController', $scope);
    $scope.cars = [];

    $scope.search = function(event) {
        var FINVar = $scope.FIN;
        var modelVar = $scope.model;
        var FuelTypeVar = $scope.FuelType;
        var CapacityMinVar = $scope.CapacityMin;
        var CapacityMaxVar = $scope.CapacityMax;
        var YearMinVar = $scope.YearMin;
        var YearMaxVar = $scope.YearMax;
        var PriceMinVar = $scope.PriceMin;
        var PriceMaxVar = $scope.PriceMax;

        if ($scope.FIN == undefined) {
            FINVar = " ";
        }
        if ($scope.model == undefined) {
            modelVar = " ";
        }
        if ($scope.FuelType == undefined) {
            FuelTypeVar = "DEFAULT";
        }
        if ($scope.CapacityMin == undefined) {
            CapacityMinVar = 0;
        }
        if ($scope.CapacityMax == undefined) {
            CapacityMaxVar = 30000;
        }
        if ($scope.YearMin == undefined) {
            YearMinVar = 1900;
        }
        if ($scope.YearMax == undefined) {
            YearMaxVar = 2015;
        }
        if ($scope.PriceMin == undefined) {
            PriceMinVar = 0;
        }
        if ($scope.PriceMax == undefined) {
            PriceMaxVar = 0;
        }


        $http.post('http://localhost:9080/client-service/rest/vehicle/filtered', {
            fin: FINVar,
            model: modelVar,
            fuelType: FuelTypeVar,
            minCapacity: CapacityMinVar,
            maxCapacity: CapacityMaxVar,
            minYear: YearMinVar,
            maxYear: YearMaxVar,
            location: Scopes.get('HeaderController').Country,
            minPrice: PriceMinVar,
            maxPrice: PriceMaxVar,
            vehicleType: Scopes.get('HeaderController').vehicleType,
            pagination: {
                pageNumber: 1,
                elemetsPerPage: 20
            }
        }).success(function(data) {
            $scope.carsRetrieved = data.vehicles;
            $rootScope.$emit("CallParentMethod", {}); //trigger function on CarResultController



        });
    }
});


angular.module('UVSClientApp').controller('SearchHistoryController', function($scope, $rootScope, Scopes, $http) {
    $http({
        method: 'GET',
        url: 'http://localhost:9080/client-service/rest/vehicle/search/history'        
        
    }).then(function(response) {
        $scope.searches = response.data;
    });

    $rootScope.$on("CallParentMethod", function() { //Listen for trigger
        $http({
            method: 'GET',
            url: 'http://localhost:9080/client-service/rest/vehicle/search/history'
        }).then(function(response) {
            $scope.searches = response.data;
        });
    })
});


angular.module('UVSClientApp').controller('CarResultController', function($scope, $rootScope, Scopes, $http) {
    $rootScope.$on("CallParentMethod", function() { //Listen for trigger
        $scope.cars = Scopes.get('CarSearchController').carsRetrieved;
        console.log($scope.cars);
    });
});
