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
        $scope.active = function(data) {
            var list=[data.id, data.employee.login]
            $http.post('/acceptApplication',list).then(function successCallback(response) {
                if(response.data.length>=26){
                    $scope.messageResponse=response.data;
                    console.log($scope.messageResponse)
                } else {
                    $window.location.reload();
                }
            });
        }
        $scope.remove = function(data) {
            var list=[data.id, data.employee.login]
            console.log(list);
            $http.post('/rejectApplication',list).then(function successCallback(response) {
                $window.location.reload();
            });
        }

        $scope.messageResponse= "";


    })