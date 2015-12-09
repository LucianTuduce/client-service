/* function for opening submenus */

(function($) {
    $(document).ready(function() {
        $('ul.dropdown-menu [data-toggle=dropdown]').on('click', function(event) {
            event.preventDefault();
            event.stopPropagation();
            $(this).parent().siblings().removeClass('open');
            $(this).parent().toggleClass('open');
        });
    });
})(jQuery);




angular.module('UVSClient', [])

.run(function($rootScope) {
    $rootScope.$on('scope.stored', function(event, data) {
        console.log("scope.stored", data);
    });
})

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
        if ($scope.FIN == undefined) {
            $scope.FIN = " ";
        }
        if ($scope.model == undefined) {
            $scope.model = " ";
        }
        if ($scope.FuelType == undefined) {
            $scope.FuelType = "DEFAULT";
        }
        if ($scope.CapacityMin == undefined) {
            $scope.CapacityMin = 0;
        }
        if ($scope.CapacityMax == undefined) {
            $scope.CapacityMax = 30000;
        }
        if ($scope.YearMin == undefined) {
            $scope.YearMin = 1900;
        }
        if ($scope.YearMax == undefined) {
            $scope.YearMax = 2015;
        }
        if ($scope.PriceMin == undefined) {
            $scope.PriceMin = 0;
        }
        if ($scope.PriceMax == undefined) {
            $scope.PriceMax = 0;
        }




        $http.post('http://localhost:9080/client-service/rest/vehicle/filtered', {
            fin: $scope.FIN,
            model: $scope.model,
            fuelType: $scope.FuelType,
            minCapacity: $scope.CapacityMin,
            maxCapacity: $scope.CapacityMax,
            minYear: $scope.YearMin,
            maxYear: $scope.YearMax,
            location: Scopes.get('HeaderController').Country,
            minPrice: $scope.PriceMin,
            maxPrice: $scope.PriceMax,
            vehicleType: Scopes.get('HeaderController').vehicleType
        }, {
            headers: {
                'Authorization': 'QmFzaWMgdXNlcjA6cGFzczA='
            }
        }).success(function(data) {
            $scope.carsRetrieved = data.vehicles;            
                $rootScope.$emit("CallParentMethod", {});
            


        });
    }
})

.controller('CarResultController', function($scope, $rootScope, Scopes, $http) {
    $rootScope.$on("CallParentMethod", function() {
        $scope.cars = Scopes.get('CarSearchController').carsRetrieved;
        console.log($scope.cars);
    });


})

;
