/**
 * Created by Medion on 2016-10-04.
 */
'use strict';

angular.module('leaveManagement', [])
    .controller('UserController', function($scope, $http, $location, $window) {
        $scope.messageRegister = "";
        $scope.registerDTO = {
            login:'',
            password:'',
            email:'',
            name:'',
            lastname:''
        };
        $scope.confirmPassword="";

        $http.get('/isAuthenticated').
            then(function(response) {
                $scope.greeting = response.data;

                if($scope.greeting.authenticated == false){
                    $("li.login").show();
                    $("li.register").show();
                    $("li.logout").hide();
                    $("li.settings").hide();
                    $("li.changePassword").hide();
                }
                if($scope.greeting.authenticated == true){
                    console.log($scope.greeting.login)
                    console.log("!!! " + $scope.greeting.login);
                    $("li.login").hide();
                    $("li.register").hide();
                    $("li.settings").show();
                    $("li.logout").show();
                    $("li.changePassword").show();
                }
            });
        $scope.register = function() {
            $http.post('/register',$scope.registerDTO)
                .then(function successCallback(response) {
                    $scope.messageRegister = "Account was created.";
                    $("form.css-form").hide();
                }, function errorCallback(error) {
                    $scope.messageRegister = "Error.";
                });


        }

}).directive('compareTo', function () {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=compareTo"
        },
        link: function(scope, element, attributes, ngModel) {

            ngModel.$validators.compareTo = function(modelValue) {
                return modelValue == scope.otherModelValue;
            };

            scope.$watch("otherModelValue", function() {
                ngModel.$validate();
            });
        }
    }
});