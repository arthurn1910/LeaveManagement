/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')
    .controller('registerController', function($scope, $http) {
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
                }, function errorCallback(response) {
                    $window.location.href = response.data;
                });
        }
    }).directive('compareTo', function () {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=compareTo"
        },
        link: function(scope, element, attributes, ngModel) {

            ngModel.$validators.compareTo = function(modelValue) {
                return modelValue == scope.otherModelValue;
            };

            scope.$watch("otherModelValue", function() {
                ngModel.$validate();
            });
        }
    }
});