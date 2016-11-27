/**
 * Created by arthurn on 25.11.16.
 */
angular.module('leaveManagement')
    .controller('createLeaveController', function ($scope,$http) {
        $scope.getLeave = function(){
            $http.get('/getLeaveDetails').then(function(response) {
                console.log(response.data);
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
            });
        }

        $scope.getLeave();
    })