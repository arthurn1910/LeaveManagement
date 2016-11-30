/**
 * Created by arthurn on 25.11.16.
 */
angular.module('leaveManagement')
    .controller('createLeaveController', function ($scope,$http,$window) {
        $scope.getLeave = function(){
            $http.get('/getLeaveDetails').then(function(response) {
                console.log(response.data);
                var thisYear=response.data.leaveThisYear-response.data.reamainingVacationLeaveThisYear;
                $scope.thisYear=thisYear+' / '+response.data.leaveThisYear;
                var lastYear=response.data.leaveLastYear-response.data.reamainingVacationLeaveLastYear;
                $scope.lastYear=lastYear+' / '+response.data.leaveLastYear;
            });
        };
        
        $scope.createVacationLeave = function() {
            $http.get('/getCreateVacationLeave').then(function successCallback(response) {
                $window.location.href = response.data;
            });
        }

        $scope.createParentalLeave = function() {
            $http.get('/getCreateParentalLeave').then(function successCallback(response) {
                $window.location.href = response.data;
            });
        }
        $scope.getLeave();
    })