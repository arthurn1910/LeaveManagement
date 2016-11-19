/**
 * Created by arthurn on 16.11.16.
 */
angular.module('leaveManagement')
    .controller('importantDatesController', function ($scope,$http,$window) {
        $scope.getImportantDates = function(){
            $http.get('/getImportantDates')
                .then(function(response) {
                    $scope.importantDates = response.data;
                    console.log($scope.importantDates);
                });
        };

        $scope.getDate = function(row){
            return new Date(row+3600*1000).toISOString().split('T')[0];
        };

        $scope.remove = function(row) {
            $http.post('/removeImportantDates', row).then(function successCallback(response) {
                $scope.getImportantDates();
            });
        }

        $scope.getImportantDates();
    })