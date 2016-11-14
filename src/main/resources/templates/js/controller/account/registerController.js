/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')
    .controller('RegisterController', function($scope, $http) {
        $scope.messageRegister = "";
        $scope.registerDTO = {
            login:'',
            password:'',
            email:'',
            name:'',
            lastname:''
        };
        $scope.confirmPassword="";
        $scope.register = function() {
            $http.post('/register',$scope.registerDTO)
                .then(function successCallback(response) {
                    $scope.messageRegister = "Account was created.";
                    $("form.css-form").hide();
                }, function errorCallback(error) {
                    $scope.messageRegister = "Error.";
                });
        }
    })