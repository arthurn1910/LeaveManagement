/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')
    .controller('changeUserPasswordController', function ($scope,$http,$window) {
        $scope.password="";
        $scope.getUserAccount = function() {
            $http.get('/getUserAccount').success(function (response) {
                $scope.userAccount = response;
            }).error(function () {
                console.log("error");
            });
        }
        $scope.saveUserPassword = function() {
            var data=[$scope.userAccount.login, $scope.password]
            $http.post('/saveUserPassword', data).success(function(response) {
                $scope.message=response;
            }).error(function(){
                console.log("error");
            });
        }

        $scope.return = function() {
            $window.location.href="/usersList";
        }

        $scope.getUserAccount();
})