/**
 * Created by arthurn on 14.11.16.
 */
angular.module('leaveManagement')
    .controller('administrationGroupController', function ($scope,$http,$window) {

        $scope.return = function() {
            $http.get('/getShowGroup').then(function successCallback(response) {
                $window.location.href = response.data;
            });
        }
        $scope.active = function(login) {
            $http.post('/acceptApplication',login).then(function successCallback(response) {
                $window.location.reload();
            });
        }
        $scope.remove = function(login) {
            $http.post('/rejectApplication',login).then(function successCallback(response) {
                $window.location.reload();
            });
        }



    })