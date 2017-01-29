/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')

    .controller('usersListController', function ($scope,$http,$window, $location) {
        $scope.getUsersList = function(){
            $http.get('/usersListData')
                .then(function successCallback(response) {
                    $scope.rowCollection = response.data;
                }, function errorCallback(response) {
                    $window.location.href = response.data;
                });
        }

        $scope.changeUserActiveStatus = function(data) {
            var account =[data.login, data.version];
            $http.post('/changeUserActiveStatus',account).then(function successCallback(response) {
                $scope.getUsersList();
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });

        }

        $scope.changeUserConfirmStatus = function(data) {
            var account =[data.login, data.version];
            $http.post('/changeUserConfirmStatus',account).then(function successCallback(response) {
                $scope.getUsersList();
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }

        $scope.changeUserPassword = function(data) {
            $http.post('/changeUserPassword',data.login).then(function successCallback(response) {
                $window.location.href=response.data;
            }).error(function(){
                console.log("error changeUserPassword");
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }

        $scope.changeUserData = function(data) {
            $http.post('/editUserAccount',data.login).then(function successCallback(response) {
                $window.location.href=response.data;
            }).error(function(){
                console.log("error changeUserData");
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }

        $scope.changeUserRole = function(data) {
            $http.post('/changeUserRole',data.login).then(function successCallback(response) {
                $window.location.href=response.data;
            }).error(function(){
                console.log("error changeUserRole");
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }

        $scope.accessLevel=function(data){
            var accesslevel ='';
            for (var level in data) {
                if(data[level]=='ADMINISTRATOR')
                    accesslevel += 'ADMINISTRATOR' + " ";
                else if(data[level]=='ACCOUNTANT')
                    accesslevel += 'KSIĘGOWY' + " ";
                else if(data[level]=='EMPLOYEE')
                    accesslevel += 'PRACOWNIK' + " ";
                else if(data[level]=='MANAGER')
                    accesslevel += 'MENADŻER' + " ";
            }
            return accesslevel;
        }
        $scope.getUsersList();

    })
