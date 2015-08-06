 $billingApp = angular.module('SocialBookApp', ['ngRoute','ui.bootstrap']) 
  .config(function($routeProvider) {

	    $routeProvider
	    
	    .when('/home', {
	      templateUrl: '/socialbook/resources/views/home.html',
	      controller: 'HomeCtrl as homeCtrl',
	      resolve: {
		        auth: ['$q', '$location', 'AuthService', checkAuthentication]
		  }
	    })
	    .when('/login', {
	      templateUrl: '/socialbook/resources/views/login.html'
	    });
//	    .when('/browse', {
//	      templateUrl: '/socialbook/resources/views/users/list.html',
//	      controller:'TeamDetailsCtrl as teamDetailsCtrl',
//	      resolve: {
//	        auth: ['$q', '$location', 'UserService', checkAuthentication]
//	      }
//	    });
	    $routeProvider.otherwise({
	      redirectTo: '/home'
	    });
	  });
	
	
checkAuthentication = function($q, $location, AuthService) {
    return AuthService.session().then(
               function(success) {},
               function(err) {
                  $location.path('/login');
                  $location.replace();
                  return $q.reject(err);
             });
 }
