//declare a module
 angular.module('BillingApp')
 .controller("loginController",['$window','$http','$scope','$location', function($window, $http, $scope, $location) { 

	$scope.url = "login";
	$scope.user = {};

	$scope.displayLoginError = false;
    
    $scope.authenticateUser = function (loginForm) {
    	var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}};
       
    	$http.post($scope.url, $.param($scope.user),config )
        .success(function (data) {
        	$window.location.href = '/BillingCrm/protected/home';
        })
        .error(function(data, status, headers, config) {
        	$scope.displayLoginError = true;
        });
    };
    
 }]);