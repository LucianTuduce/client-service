




var VehicleTypeVar="";
var CountryVar="";

$("#VehicleChoice li a").click(function(){
  $("#VehicleType:first-child").html('<span class="glyphicon glyphicon-home"></span><span class="caret"></span><br/>'+$(this).text()); 

  VehicleTypeVar = $(this).text(); 
});


$(".location-choice li a").click(function(){
  $(".location-choice:first-child").html('<span class="glyphicon glyphicon-globe"></span><span class="caret"></span><br/>'+$(this).text()); 

  VehicleTypeVar = $(this).text(); 
});


angular.module('UVSClient', [])
    .controller('Header', function() {

    })

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
        	location: $scope.CountryVar,
        	priceMin: $scope.PriceMin,
        	priceMax: $scope.PriceMax,
        	vehicleType: VehicleTypeVar
    		}).success(function(data) {
        		$scope.cars = data;
    		});
	}	

	});



