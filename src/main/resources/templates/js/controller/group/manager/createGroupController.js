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
                    $scope.message = "Grupa została stworzona.";
                    $("form.css-form").hide();
                }, function errorCallback(error) {
                    $scope.message = "Błąd w trakcie tworzenia grupy.";
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