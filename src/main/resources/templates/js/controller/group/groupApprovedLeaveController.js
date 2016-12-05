/**
 * Created by arthurn on 19.11.16.
 */
angular.module('leaveManagement')
    .controller('groupPlannedLeaveController', function ($scope,$http,$window, $location) {
        $scope.getGroupLeave = function(){
            $http.get('/getGroupLeave')
                .then(function(response) {
                    $scope.groupLeave = response.data;
                    console.log($scope.groupLeave);
                    // $scope.setLeave($scope.groupLeave);
                });
        };

        $scope.setLeave = function(groupLeave){
            $scope.plannedLeave=[];
            $scope.approvedLeave=[];
            for(var i=0;i<groupLeave.length;i++){
                if(data[i].active==true) {
                    $scope.memberList.push(data[i]);
                } else{
                    $scope.applyGroup.push(data[i]);
                }
            }
        };

        $scope.getDate = function(row){
            return new Date(row+3600*1000).toISOString().split('T')[0];
        };

        $scope.getGroupLeave();

    })