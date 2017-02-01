/**
 * Created by arthurn on 25.11.16.
 */
angular.module('leaveManagement')
    .controller('createLeaveController', function ($scope,$http,$window) {
        $scope.getLeave = function(){
            $http.get('/getLeaveDetails').then(function successCallback(response) {
                console.log(response.data);
                var thisYear=response.data.leaveThisYear-response.data.reamainingVacationLeaveThisYear;
                $scope.thisYear='dostepne: '+thisYear+', wykorzystano: '+response.data.reamainingVacationLeaveThisYear+', nalezne: '+response.data.leaveThisYear;
                var lastYear=response.data.leaveLastYear-response.data.reamainingVacationLeaveLastYear;
                $scope.lastYear='dostepne: '+lastYear+', wykorzystano: '+response.data.reamainingVacationLeaveLastYear+', nalezne: '+response.data.leaveLastYear;
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        };
        
        $scope.createVacationLeave = function() {
            $http.get('/getCreateVacationLeave').then(function successCallback(response) {
                $window.location.href = response.data;
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }

        $scope.createParentalLeave = function() {
            $http.get('/getCreateParentalLeave').then(function successCallback(response) {
                $window.location.href = response.data;
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }
        $scope.getLeave();
    })