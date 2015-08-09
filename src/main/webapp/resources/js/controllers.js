//declare a module
angular
		.module('SocialBookApp')

		.controller('MainCtrl', [ 'AuthService', function(AuthService) {

			var self = this;
			self.authService = AuthService;

			// Check if the user is logged in when the application
			// loads
			// User Service will automatically update isLoggedIn
			// after this call finishes
			AuthService.session();

		} ])

		.controller(
				'UsersController',
				[
						'UserService',
						'$log',
						function(UserService, $log) {
							var self = this;

							self.getUsers = function() {
								UserService.browseNotConnectedUsers().then(
										function(result) {
											if (result.data == 0) {
												self.errorMessage = "No Users";
											}
											self.users = result.data;
											return result;
										}, function(error) {

										})
							}

							self.connect = function(user) {
								UserService
										.connect(user)
										.then(
												function(success) {
													self.successMessage = "Connected to user "
															+ user.name;
													self.getUsers(); // update
																		// the
																		// users
												},
												function(error) {
													self.errorMessage = "Already Connected to User";
												})
							}

							self.getUsers();

						} ])

		.controller('LoginCtrl',
				[ 'AuthService', '$location', function(AuthService, $location) {
					var self = this;
					self.user = {
						username : '',
						password : ''
					};
					self.errorMessage = "";

					self.login = function() {
						AuthService.login(self.user).then(function(success) {
							$location.path('/');
						}, function(error) {
							self.errorMessage = "Invalid Username or Password";
						})
					};

				} ])

		.controller(
				'HomeController',
				[
						'UserService',
						'$log',
						'$modal',
						function(UserService, $log, $modal) {
							var self = this;

							self.connectedUsers;

							self.getConnectedUsers = function() {
								UserService
										.browseConnectedUsers()
										.then(
												function(result) {
													if (result.data == 0) {
														self.errorMessage = "No Connected Users";
													}
													self.users = result.data;
													return result;
												}, function(error) {

												})
							}

							self.getUserConnections = function(user) {
								return UserService
										.browseUserConnectedUsers(user)
										.then(
												function(result) {
													if (result.data == 0) {
														self.errorMessage = "No Connected Users";
													}
													self.connectedUsers = result.data;
													return result.data;
												}, function(error) {

												})
							}

							self.viewUserConnections = function(user) {

								var connectionsModalInstance = $modal
										.open({
											animation : true,
											templateUrl : '/socialbook/resources/views/users/modalList.html',
											controller : 'ConnectionsModalCtrl as conModCtrl',
											size : '',
											resolve : {

												connectedUsers : function() {
													return self
															.getUserConnections(user);
												}
											}

										});

								connectionsModalInstance.result.then(function(
										connectedUsers) {
									self.connectedUsers = connectedUsers;
								}, function() {
									$log.info('Modal dismissed at: '
											+ new Date());
								});

							};

							self.getConnectedUsers();

						} ])

		.controller(
				'BrowseAllUsersController',
				[
						'UserService',
						'$log',
						'$modal',
						function(UserService, $log, $modal) {
							var self = this;

							self.connectedUsers;

							self.getConnectedUsers = function() {
								UserService
										.browseUsers()
										.then(
												function(result) {
													if (result.data == 0) {
														self.errorMessage = "No Connected Users";
													}
													self.users = result.data;
													return result;
												}, function(error) {

												})
							}

							self.getUserConnections = function(user) {
								return UserService
										.browseUserConnectedUsers(user)
										.then(
												function(result) {
													if (result.data == 0) {
														self.errorMessage = "No Connected Users";
													}
													self.connectedUsers = result.data;
													return result.data;
												}, function(error) {

												})
							}

							self.viewUserConnections = function(user) {

								var connectionsModalInstance = $modal
										.open({
											animation : true,
											templateUrl : '/socialbook/resources/views/users/modalList.html',
											controller : 'ConnectionsModalCtrl as conModCtrl',
											size : '',
											resolve : {

												connectedUsers : function() {
													return self
															.getUserConnections(user);
												}
											}

										});

								connectionsModalInstance.result.then(function(
										connectedUsers) {
									self.connectedUsers = connectedUsers;
								}, function() {
									$log.info('Modal dismissed at: '
											+ new Date());
								});

							};

							self.getConnectedUsers();

						} ])

		.controller(
				'ConnectionsModalCtrl',
				[ '$modalInstance', 'connectedUsers', 'UserService',
						function($modalInstance, connectedUsers, UserService) {

							var self = this;

							self.connectedUsers = connectedUsers;

							self.cancel = function() {
								$modalInstance.dismiss('cancel');
							};

						} ])

		.controller(
				'RegisterController',
				[
						'$modal',
						'$log',
						function($modal, $log) {

							var self = this;

							self.user = {};

							self.open = function(size) {

								var modalInstance = $modal
										.open({
											animation : true,
											templateUrl : '/socialbook/resources/views/users/register.html',
											controller : 'RegisterModalInstanceCtrl as regModCtrl',
											size : size,
											resolve : {
												user : function() {
													return self.user;
												}
											}
										});

								modalInstance.result.then(function(user) {
									self.user = user;
									$log.info('Selected Item : ' + user.name);
									self.user = {};
								}, function() {
									$log.info('Modal dismissed at: '
											+ new Date());
								});

							};

						} ])

		.controller(
				'RegisterModalInstanceCtrl',
				[
						'$modalInstance',
						'user',
						'UserService',
						function($modalInstance, user, UserService) {

							var self = this;

							self.user = user;

							self.errorMessage = "";
							self.successMessage = "";
							self.userRegistered = false;

							self.ok = function() {

								UserService
										.register(self.user)
										.then(
												function(success) {
													self.successMessage = "User Created Successfully!.";
													self.errorMessage = "";
													self.user = {};
													// $modalInstance.close(self.user);
												},
												function(error) {
													self.errorMessage = "Invalid User, Try Different Email Address";
												})
							};

							self.cancel = function() {
								$modalInstance.dismiss('cancel');
							};
						} ]);
