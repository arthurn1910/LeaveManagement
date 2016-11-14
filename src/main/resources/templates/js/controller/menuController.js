/**
 * Created by arthurn on 05.11.16.
 */

'use strict';

angular.module('leaveManagement', [])
    .controller('menuController', function($scope, $http) {
        $scope.isAuthenticated = function() {
            $http.get('/isAuthenticated').then(function(response) {
                $scope.greeting = response.data;
                if($scope.greeting.authenticated == false){
                    $("li.login").show();
                    $("li.register").show();
                    $("li.logout").hide();
                    $("li.settings").hide();
                    $("li.administration").hide();
                    $("li.groups").hide();
                }
                if($scope.greeting.authenticated == true){
                    $("li.login").hide();
                    $("li.register").hide();
                    $("li.settings").show();
                    $("li.logout").show();
                    $("li.administration").show();
                    $("li.groups").show();
                }
            });
        }
        $scope.isAuthenticated();
    })