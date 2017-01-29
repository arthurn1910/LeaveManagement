/**
 * Created by arthurn on 22.11.16.
 */
angular.module('leaveManagement')
    .controller('viewLeaveController', function ($scope,$http) {
        $scope.getLeave = function(){
            $http.get('/getYourLeave')
                .then(function successCallback(response) {
                    $scope.leaveList = response.data;
                    console.log($scope.leaveList);
                }, function errorCallback(response) {
                    $window.location.href = response.data;
                });
        };

        $scope.getDate = function(row){
            return new Date(row+3600*1000).toISOString().split('T')[0];
        };

        $scope.check = function(row) {
            if(row>=new Date())
                return true;
            return false;
        }

        $scope.remove = function(row) {
            $http.post('/removeLeave', row).then(function successCallback(response) {
                $scope.message=response.data;
                $scope.getLeave();
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }

        $scope.getLeave();
    })