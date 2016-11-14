/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')

    .controller('usersListController', function ($scope,$http,$window, $location) {
        $scope.getUsersList = function(){
            $http.get('/usersListData')
                .then(function(response) {
                    $scope.rowCollection = response.data;
                });
        }

        $scope.changeUserActiveStatus = function(data) {
            var account =[data.login, data.version];
            $http.post('/changeUserActiveStatus',account).then(function(response) {
                $scope.getUsersList();
            });

        }

        $scope.changeUserConfirmStatus = function(data) {
            var account =[data.login, data.version];
            $http.post('/changeUserConfirmStatus',account);
        }

        $scope.changeUserPassword = function(data) {
            $http.post('/changeUserPassword',data.login).success(function(response) {
                $window.location.href=response;
            }).error(function(){
                console.log("error changeUserPassword");
            });
        }

        $scope.changeUserData = function(data) {
            $http.post('/editUserAccount',data.login).success(function(response) {
                $window.location.href=response;
            }).error(function(){
                console.log("error changeUserData");
            });
        }

        $scope.changeUserRole = function(data) {
            $http.post('/changeUserRole',data.login).success(function(response) {
                $window.location.href=response;
            }).error(function(){
                console.log("error changeUserRole");
            });
        }
        $scope.getUsersList();

    })
