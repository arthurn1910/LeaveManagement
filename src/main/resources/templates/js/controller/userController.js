/**
 * Created by Medion on 2016-10-04.
 */
'use strict';

angular.module('leaveManagement', [])
    .controller('UserController', function($scope, $http) {
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

})
.controller('basicsCtrl', function ($scope,$http,$window) {
    $scope.accessLevel=function(data){
        var accesslevel ='';
        for (var level in data) {
            accesslevel += data[level] + " ";
        }
        return accesslevel;
    }

    $http.get('/usersListData')
        .then(function(response) {
            $scope.rowCollection = response.data;
        });

    $scope.changeUserActiveStatus = function(data) {
        var account =[data.login, data.version];
        $http.post('/changeUserActiveStatus',account);
        $window.location.reload();
    }

    $scope.changeUserConfirmStatus = function(data) {
        var account =[data.login, data.version];
        $http.post('/changeUserConfirmStatus',account);
        $window.location.reload();
    }
})
    .directive('compareTo', function () {
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