

careApp.controller('homeController', function($scope, $uibModal) {
	$scope.openRegisterModal = function () {
      $scope.registered = false;
		  $uibModal.open({
		      templateUrl: '/views/register.html',
		      controller: 'registerController'
		    })
	};
	
});