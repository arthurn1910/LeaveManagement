/**
 * Created by arthurn on 18.11.16.
 */
angular.module('leaveManagement')
    .controller('createImportantDateController', function ($scope,$http,$window) {
        $scope.message='';
        $scope.dateCreate='';
        $scope.flag=true;
        $scope.create = function() {
            $scope.date=new Date($scope.dateCreate.getTime()+3600*1000).toISOString().split('T')[0];
            $http.post('/createImportantDate', $scope.date).then(function successCallback(response) {
                $scope.flag=false;
                $scope.message=response.data;
            });
        }
    })