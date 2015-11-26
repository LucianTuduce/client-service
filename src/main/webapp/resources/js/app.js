(function(){
var app=angular.module('login', []);

app.controller('Main', function() {

});



app.controller('CountryCtrl', function ($scope, $http) {

   $http.get('countries.json').success(function(data) {

       $scope.countries = data;

   });

});



})();