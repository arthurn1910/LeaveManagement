/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')
    .controller('userAccountController', function ($scope,$http,$window, $location) {
        $scope.accessLevel=function(data){
            var accesslevel ='';
            for (var level in data) {
                accesslevel += data[level] + " ";
            }
            return accesslevel;
        }

        $scope.accountDetails = function(){
            $http.get('/getAccountDetails')
                .then(function successCallback(response) {
                    $scope.account = response.data;
                }, function errorCallback(response) {
                    $window.location.href = response.data;
                });
        }

        $scope.change = function() {
            $http.get('/editAccountGet').then(function successCallback(response) {
                $window.location.href=response.data;
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }

        $scope.accountDetails();
})