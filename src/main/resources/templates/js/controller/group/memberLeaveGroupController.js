/**
 * Created by arthurn on 20.11.16.
 */
angular.module('leaveManagement')
    .controller('memberLeaveGroupController', function ($scope,$http,$window) {
        $scope.getLeaveInGroup = function(){
            $http.get('/getLeaveInGroup')
                .then(function(response) {
                    $scope.leaveInGroup = response.data;
                    $scope.setLeave();
                });
        };

        $scope.getDate = function(row){
            return new Date(row+3600*1000).toISOString().split('T')[0];
        };

        $scope.setLeave = function(){
            $scope.plannedLeave=[];
            $scope.approvedLeave=[];
            for(var i=0;i<$scope.leaveInGroup.length;i++){
                if($scope.leaveInGroup[i].active==true) {
                    if ($scope.leaveInGroup[i].confirm == true) {
                        $scope.approvedLeave.push($scope.leaveInGroup[i]);
                    } else {
                        $scope.plannedLeave.push($scope.leaveInGroup[i]);
                    }
                }
            }
        };

        $scope.confirm = function(row) {
            $http.post('/confirmLeave', row).then(function successCallback(response) {
                $scope.message=response.data;
                $scope.getLeaveInGroup();
            });
        }

        $scope.reject = function(row) {
            $http.post('/rejectLeave', row).then(function successCallback(response) {
                $scope.message=response.data;
                $scope.getLeaveInGroup();
            });
        }

        $scope.getLeaveInGroup();
    })