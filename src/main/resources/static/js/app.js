'use strict';

var careApp = angular.module('careApp', ['ngRoute','ngResource', 'ui.bootstrap']);

careApp.config(function($routeProvider, $locationProvider, $httpProvider){
	//$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    $routeProvider
	    .when('/care/home',{
	        templateUrl: '/views/home.html',
	        controller: 'homeController'
	    })
        .when('/care/profile',{
            templateUrl: '/views/profile.html',
            controller: 'profileController'
        })
        .otherwise(
            { redirectTo: '/care/home'}
        );
    
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
 });
});

careApp.constant("CAREAPP_URLS", {
	registerUser : "/care/user/register",
	loginUser : "/care/user/login"
});



careApp.directive("compareTo", function() {
    return {
      require: "ngModel",
      scope: {
        confPassword: "=compareTo"
      },
      link: function(scope, element, attributes, modelVal) {

        modelVal.$validators.compareTo = function(val) {
          return val == scope.confPassword;
        };

        scope.$watch("confPassword", function() {
          modelVal.$validate();
        });
      }
    };
  });



