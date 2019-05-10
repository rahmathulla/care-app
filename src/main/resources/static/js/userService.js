

careApp.factory('userService',function($http, CAREAPP_URLS){
	var service = {};

	service.registerUser = function(userDto) {
		return $http.post(CAREAPP_URLS.registerUser, userDto);
	}
	
	service.login = function(base64Credential) {
		var authHeader = {headers : {
				// setting the Authorization Header
				'Authorization' : 'Basic ' + base64Credential
			}
		};
		
		return $http.get(CAREAPP_URLS.loginUser, authHeader);
	}
	return service;
});