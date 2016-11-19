/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')
    .controller('createGroupController', function ($scope,$http,$window, $location) {
        $scope.message = "";
        $scope.title="";
        $scope.createGroup = function() {
            $http.post('/createGroup',$scope.title)
                .then(function successCallback(response) {
                    $scope.message = "Group was created.";
                    $("form.css-form").hide();
                }, function errorCallback(error) {
                    $scope.message = "Error create group.";
                });
        };

        $scope.return = function() {
            console.log('!111');
            $http.get('/getGroupList').then(function successCallback(response) {
                console.log('!222');
                $window.location.href = response.data;
            });
        }
    })