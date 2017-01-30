/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')
    .controller('createGroupController', function ($scope,$http,$window, $location) {
        $scope.message = "";
        $scope.title="";
        var number = [{id:50},{id:60},{id:70}, {id:80},{id:90}]
        $scope.modelExpirience={number};
        $scope.number=50;

        $scope.createGroup = function() {
            var data=[$scope.title, $scope.number]
            $http.post('/createGroup',data)
                .then(function successCallback(response) {
                    $scope.message = "Grupa zostala stworzona.";
                    $("form.css-form").hide();
                }, function errorCallback(response) {
                    $window.location.href = response.data;
                });
        };

        $scope.return = function() {
            $http.get('/getGroupList').then(function successCallback(response) {
                $window.location.href = response.data;
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }
    })