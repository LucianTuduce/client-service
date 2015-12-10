angular.module('UVSClient', [])

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
})

.controller('HeaderController', function($scope, $rootScope, Scopes) {
    Scopes.store('HeaderController', $scope);

    var VehicleTypeVar = "";
    var CountryVar = "";
    var LanguageVar = "";
    $scope.VehicleTypeSelect = function(event) {
        VehicleTypeVar = event.target.text;
        $scope.vehicleType = VehicleTypeVar;
        var parentElement = angular.element(document.querySelector('#VehicleType:first-child'));
        parentElement.html('<span class="glyphicon glyphicon-home"></span><span class="caret"></span><br/>' + VehicleTypeVar);
    };

    $scope.CountrySelect = function(event) {
        CountryVar = event.target.text;
        $scope.Country = CountryVar;
        var parentElement = angular.element(document.querySelector('#Country:first-child'));
        parentElement.html('<span class="glyphicon glyphicon-globe"></span><span class="caret"></span><br/>' + CountryVar);
    };

    $scope.LanguageSelect = function(event) {
        LanguageVar = event.target.text;
        $scope.Language = LanguageVar;
        var parentElement = angular.element(document.querySelector('#Country:first-child'));
        parentElement.html('<span class="glyphicon glyphicon-globe"></span><span class="caret"></span><br/>' + LanguageVar);

    };
})

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
        }, {
            headers: {
                'Authorization': 'QmFzaWMgdXNlcjA6cGFzczA='
            }
        }).success(function(data) {
            $scope.carsRetrieved = data.vehicles;
            $rootScope.$emit("CallParentMethod", {}); //trigger function on CarResultController



        });
    }
})


.controller('SearchHistoryController', function($scope, $rootScope, Scopes, $http) {
    $rootScope.$on("CallParentMethod", function() { //Listen for trigger

        $http({
            method: 'GET',
            url: 'http://localhost:9080/client-service/rest/vehicle/search/history',
            headers: {
                'Authorization': 'QmFzaWMgdXNlcjA6cGFzczA='
            }
        }).then(function(response) {
            $scope.searches = response.data;
        });
    })


})


.controller('CarResultController', function($scope, $rootScope, Scopes, $http) {
    $rootScope.$on("CallParentMethod", function() { //Listen for trigger
        $scope.cars = Scopes.get('CarSearchController').carsRetrieved;
        console.log($scope.cars);
    });


})



;
