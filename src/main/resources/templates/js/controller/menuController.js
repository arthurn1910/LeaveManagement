/**
 * Created by arthurn on 05.11.16.
 */
angular.module('leaveManagement')
    .controller('menuController', function($scope, $http) {
        $scope.isAuthenticated = function() {
            $http.get('/isAuthenticated').then(function(response) {
                $scope.greeting = response.data;
                if($scope.greeting.authenticated == false){
                    $("li.login").show();
                    $("li.register").show();
                    $("li.logout").hide();
                    $("li.settings").hide();
                    $("li.leave").hide();
                    $("li.administration").hide();
                    $("li.groups").hide();
                }
                if($scope.greeting.authenticated == true){
                    $("li.login").hide();
                    $("li.register").hide();
                    $("li.settings").show();
                    $("li.logout").show();
                    if($scope.greeting.roleAccountant==true || $scope.greeting.roleAdministrator==true){
                        $("li.administration").show();
                    }else{
                        $("li.administration").hide();
                    }
                    if($scope.greeting.roleEmployee==true || $scope.greeting.roleManager==true){
                        $("li.groups").show();
                    }else{
                        $("li.groups").hide();
                    }
                    if($scope.greeting.roleEmployee==true || $scope.greeting.roleAccountant==true){
                        $("li.leave").show();
                        if($scope.greeting.roleEmployee==true){
                            $("li.viewLeave").show();
                            $("li.createLeave").show();
                            $("li.reportLeave").hide();
                        }
                        if($scope.greeting.roleAccountant==true){
                            $("li.viewLeave").hide();
                            $("li.createLeave").hide();
                            $("li.reportLeave").show();
                        }
                    }else{
                        $("li.leave").hide();
                    }
                }
            });
        }
        $scope.isAuthenticated();
    })