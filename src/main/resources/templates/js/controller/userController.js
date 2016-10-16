/**
 * Created by Medion on 2016-10-04.
 */
'use strict';

angular.module('leaveManagement', []).controller('UserController', function($scope, $http) {
    $scope.registerDTO = {
        login:'',
        password:'',
        email:'',
        name:'',
        lastname:''
    };
    $scope.oi='12';
    $scope.confirmPassword='';

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
        $scope.oi='12asassa';
        $("li.logout").hide();
    }


});