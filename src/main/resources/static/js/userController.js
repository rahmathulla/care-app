careApp.controller('registerController', function($scope, $rootScope, $uibModalInstance, userService) {
	$scope.ph_numbr = /^\+?\d{10}$/;
	$scope.eml_add = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;
	$scope.userDto = {
		firstName : null,
		lastName : null,
		phone : null,
		email : null,
		password : null

	};

	$scope.registerUser = function() {
		$rootScope.loginMessage = '';
		$rootScope.loginMessageShow = false;
		$rootScope.logoutMessage = '';
		$rootScope.logoutMessageShow = false;
		console.log('registration');
		
		userService.registerUser($scope.userDto).then(function(response) {
			console.log("works");
			$scope.userDto = {
					firstName : null,
					lastName : null,
					phone : null,
					email : null,
					password : null

				};
			 $scope.registered = true;
		})
		.catch(function(response) {
			if(response.status == 409) {
				$scope.userAlreadyExist = true;
			}
		});
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
	
	$scope.close = function() {
		$uibModalInstance.close();
	};
	
	$scope.tryDifferentEmail = function() {
	    $uibModalInstance.dismiss('cancel');
	  };
});

careApp.controller('loginController', function($scope, $rootScope, $http, $location, $timeout, userService) {
	$scope.profileDto = {
			firstName : null,
			lastName : null,
			phone : null,
			email : null,
		};
	
	$scope.login = function() {
		$rootScope.logoutMessage = '';
		$rootScope.logoutMessageShow = false;
		
		console.log('login');
		// creating base64 encoded String from username and password
		var base64Credential = btoa($scope.email + ':' + $scope.password);
		
		// calling GET request for getting the user details
		userService.login(base64Credential).success(function(data, status, headers, config) {
			
			console.log(status); 
			console.log(data); 
			console.log(data.firstName); 

			
			if (status == 200) {
				$rootScope.loginMessage = '';
				$rootScope.loginMessageShow = false;
				// setting the same header value for all request calling from this app
				$http.defaults.headers.common['Authorization'] = 'Basic ' + base64Credential;
				$rootScope.profileDto = data;
				$location.path("/care/profile" );
			} else {
				$rootScope.loginMessage = 'Authetication Failed !';
				$rootScope.loginMessageShow = true;
				
			}
			
		}).error(function(error) {
			$rootScope.loginMessage = 'Authetication Failed !';
			$rootScope.loginMessageShow = true;
			
		});
	};
});


careApp.controller('profileController', function($scope, $http, $rootScope, $location) {
		$scope.logout = function() {
		// clearing the authorization header
		$http.defaults.headers.common['Authorization'] = null;
		// clearing all data
		$scope.profileDto = null;
		$rootScope.profileDto = null;
		$rootScope.logoutMessageShow = true;
		$rootScope.logoutMessage = 'Successfully logged out';
		console.log($scope.profileDto);
		console.log($rootScope.profileDto);
		$location.path("/care/home" );
		
	};
});

