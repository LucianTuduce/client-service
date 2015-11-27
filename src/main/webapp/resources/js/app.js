angular.module('UVSClient', [])
    .controller('Main', function() {})

	.controller('CarSearchController', function($scope, $http) {
	$scope.cars = [];
	$scope.SearchOptions = ''; //cate una pt fiecare
	$http.get('cars.json').success(function(data) {
    	$scope.cars = data;
	});

	$scope.search = function(event) {
   		 $http.post('cars.json', {
       		fin: $scope.FIN,
        	model: $scope.model,
        	fuelType: $scope.FuelType,
        	engineCapacitMin: $scope.CapacityMin,
        	engineCapacityMax: $scope.CapacityMax,
        	yearMin: $scope.YearMin,
        	yearMax: $scope.YearMax,
        	location: $scope.carLocation,
        	priceMin: $scope.PriceMin,
        	priceMax: $scope.PriceMax,
        	vehicleType: $scope.VehicleType
    		}).success(function(data) {
        		$scope.cars = data;
    		});
	}	

	});



