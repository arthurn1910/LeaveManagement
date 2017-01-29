/**
 * Created by arthurn on 19.11.16.
 */
angular.module('leaveManagement')
    .controller('removeGroupController', function ($scope,$http,$window) {
        $scope.remove = function() {
            $http.get('/removeGroup').then(function successCallback(response) {
                $window.location.href = response.data;
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }
    })