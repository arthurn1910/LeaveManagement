/**
 * Created by Medion on 2016-10-04.
 */
'use strict';

angular.module('leaveManagement', [])
    .controller('UserController', function($scope, $http, $location, $window) {
        $scope.registerDTO = {
            login:'',
            password:'',
            email:'',
            name:'',
            lastname:''
        };

        $http.get('/isAuthenticated').
            then(function(response) {
                $scope.greeting = response.data;

                if($scope.greeting.authenticated == false){
                    $("li.login").show();
                    $("li.register").show();
                    $("li.logout").hide();
                }
                if($scope.greeting.authenticated == true){
                    $("li.login").hide();
                    $("li.register").hide();
                    $("li.logout").show();
                }
            });
        $scope.register = function() {
            $http.post('/register',$scope.registerDTO)
                .then(function successCallback(response) {
                    // $window.location.href = 'account/index';
                    // $location.path( "/login" );
                    $window.location.href = '/';
                }, function errorCallback(response) {
                    $scope.loginTaken='That login is taken. Try another.'
                });


        }
}).directive('validPasswordC', function () {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function (viewValue, $scope) {
                var noMatch = viewValue != scope.RegisterForm.password.$viewValue
                ctrl.$setValidity('noMatch', !noMatch)
            })
        }
    }
});