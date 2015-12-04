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

.controller('HeaderController', function($scope, Scopes) {
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

.controller('CarSearchController', function($scope, Scopes, $http) {
    Scopes.store('CarSearchController', $scope);
    $scope.cars = [];

    $scope.search = function(event) {
        $http.post('cars.json', {
            fin: $scope.FIN,
            model: $scope.model,
            fuelType: $scope.FuelType,
            engineCapacitMin: $scope.CapacityMin,
            engineCapacityMax: $scope.CapacityMax,
            yearMin: $scope.YearMin,
            yearMax: $scope.YearMax,
            location: Scopes.get('HeaderController').Country,
            priceMin: $scope.PriceMin,
            priceMax: $scope.PriceMax,
            vehicleType: Scopes.get('HeaderController').vehicleType
        }).success(function(data) {
            $scope.cars = data;
        });
    }
});
