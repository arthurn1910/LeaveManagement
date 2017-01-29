/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')
    .controller('changePasswordController', function ($scope,$http,$window) {
        $scope.actualPassword="";
        $scope.newPassword="";
        $scope.confirmPassword="";
        $scope.savePassword = function() {
            var data=[$scope.actualPassword, $scope.newPassword]
            $http.post('/savePassword', data).then(function successCallback(response) {
                $scope.message=response.data;
                $scope.actualPassword="";
                $scope.newPassword="";
                $scope.confirmPassword="";
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