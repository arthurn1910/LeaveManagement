/**
 * Created by Medion on 2016-10-07.
 */
angular.module('leaveManagement', []).controller('test', function($scope, $http) {
    $scope.imie = "bbb";
    // $scope.model = $http.post('/isAuthenticated');
    $http.get('/isAuthenticated').
    then(function(response) {
        $scope.greeting = response.data;
    });

});