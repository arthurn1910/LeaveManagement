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
                .then(function(response) {
                    $scope.account = response.data;
                });
        }

        $scope.change = function() {
            $http.get('/editAccountGet').success(function(response) {
                $window.location.href=response;
            }).error(function(){
                console.log("error changeUserData");
            });
        }

        $scope.accountDetails();
})