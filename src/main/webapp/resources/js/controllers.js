//declare a module
 angular.module('SocialBookApp')
 
 
 .controller('MainCtrl', ['AuthService',
    function(AuthService) {
	 
	 
      var self = this;
      self.authService = AuthService;

      // Check if the user is logged in when the application
      // loads
      // User Service will automatically update isLoggedIn
      // after this call finishes
      AuthService.session();
      
  }])
  
  .controller('RegisterCtrl', ['UserService',
    function(UserService) {
	       
  }])
  
  .controller('LoginCtrl', ['AuthService', '$location', 
    function(AuthService, $location) {
	  
	  
      var self = this;
      self.user = {username: '', password: ''};
      self.errorMessage = "";

      self.login = function() {
    	AuthService.login(self.user).then(function(success) {
          $location.path('/');
        }, function(error) {
          self.errorMessage = "Invalid Username or Password";
        })
      };
      
  }])
  
  
   .controller('HomeCtrl',
	['$location', '$routeParams',
	function($location, $routeParams) {
	  var self = this;
	
	}])
	
  .controller('RegisterController', ['$modal', '$log', function ($modal, $log) {
	  
	  var self = this;

	  self.user = {};
	
	  self.open = function (size) {
	
	    var modalInstance = $modal.open({
	      animation: true,
	      templateUrl: 'myModalContent.html',
	      controller: 'RegisterModalInstanceCtrl as regModCtrl',
	      size: size,
	      resolve: {
	        items: function () {
	          return self.user;
	        }
	      }
	    });
	
	    modalInstance.result.then(function (user) {
	      self.user = user;
	      $log.info('Selected Item : ' + user.name);
	      self.user = {};
	    }, function () {
	      $log.info('Modal dismissed at: ' + new Date());
	    });
	    
  };



}])

.controller('RegisterModalInstanceCtrl', ['$modalInstance', 'items', 'UserService' ,function ($modalInstance, user, UserService) {
	
  var self = this;

  self.user = user;

  self.ok = function () {
   
	  UserService.register(self.user).then(function(success) {
    	 $modalInstance.close(self.user);
      }, function(error) {
        self.errorMessage = "Invalid User";
      })
  };

  self.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
}])
	
	
	
	
	;
 
 
 