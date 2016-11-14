/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')
    .controller('changeDataController', function ($scope,$http,$window) {
        $scope.accountDetails = function(){
            $http.get('/getAccountDetails')
                .then(function(response) {
                    $scope.account = response.data;
                });
        }
        $scope.saveAccount = function() {
            var data=[$scope.account.name,$scope.account.lastname
                ,$scope.account.email]
            $http.post('/saveAccount', data).success(function(response) {
                $scope.message=response;
            }).error(function(){
                console.log("error");
            });
        }
        $scope.accountDetails();

    })