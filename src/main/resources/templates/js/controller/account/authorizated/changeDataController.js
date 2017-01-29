/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')
    .controller('changeDataController', function ($scope,$http,$window) {
        $scope.accountDetails = function(){
            $http.get('/getAccountDetails')
                .then(function successCallback(response) {
                    $scope.account = response.data;
                }, function errorCallback(response) {
                    $window.location.href = response.data;
                });
        }
        $scope.saveAccount = function() {
            var data=[$scope.account.name,$scope.account.lastname
                ,$scope.account.email]
            $http.post('/saveAccount', data).then(function successCallback(response) {
                $scope.message=response.data;
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }
        $scope.accountDetails();

    })