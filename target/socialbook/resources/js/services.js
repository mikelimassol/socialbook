// File: chapter10/routing-example/app/scripts/services.js
angular
		.module('SocialBookApp')
		.factory(
				'UserService',
				[
						'$http',
						function($http) {
							return {

								browseUsers : function() {
									return $http
											.get('/socialbook/secure/admin/browse/users');
								},

								browseNotConnectedUsers : function() {
									return $http
											.get('/socialbook/secure/browse/users');
								},

								browseConnectedUsers : function() {
									return $http
											.get('/socialbook/secure/browse/connections/');
								},

								browseUserConnectedUsers : function(user) {
									return $http
											.get('/socialbook/secure/browse/connections/'
													+ user.id);
								},

								register : function(user) {
									var config = {
										headers : {
											'Content-Type' : 'application/json;charset=UTF-8'
										}
									};
									return $http.post('/socialbook/subcribe',
											user, config).then(
											function(response) {
												return response;
											});
								},

								connect : function(user) {

									var config = {
										headers : {
											'Content-Type' : 'application/json;charset=UTF-8'
										}
									};
									return $http.post(
											'/socialbook/secure/connect', user,
											config).then(function(response) {
										return response;
									});
								}
							}
						} ])
		.factory(
				'AuthService',
				[
						'$http',
						function($http) {
							var service = {
								isLoggedIn : false,
								authUser : {},
								session : function() {
									return $http
											.get('/socialbook/auth/session')
											.then(
													function(response) {
														service.isLoggedIn = true;
														service.authUser = response.data;

														return response;
													});
								},

								login : function(user) {
									var config = {
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
										}
									};
									return $http.post('/socialbook/login',
											$.param(user), config).then(
											function(response) {
												service.isLoggedIn = true;
												return response;
											});
								}
							};
							return service;
						} ]);