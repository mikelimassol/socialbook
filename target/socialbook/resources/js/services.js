// File: chapter10/routing-example/app/scripts/services.js
angular.module('SocialBookApp')
  .factory('UserService', ['$http',
    function($http) {
      return {
        browseUsers: function() {
          return $http.get('/socialbook/secure/browse/users');
        },
        
        register: function(user) {
        	var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}};
            return $http.post('/socialbook/subcribe',  $.param(user), config)
              .then(function(response) {
                return response;
            });
          }

//        getTeamDetails: function(code) {/subcribe
//          return $http.get('/api/team/' + code);
//        }
      }
  }])
  .factory('AuthService', ['$http', function($http) {
    var service = {
      isLoggedIn: false,
      authUser: {},
      session: function() {
        return $http.get('/socialbook/auth/session')
              .then(function(response) {
          service.isLoggedIn = true;
          service.authUser = response.data;
          return response;
        });
      },	

      login: function(user) {
    	var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}};
        return $http.post('/socialbook/login',  $.param(user), config)
          .then(function(response) {
            service.isLoggedIn = true;
            return response;
        });
      }
      
    	  
      
    };
    return service;
  }]);