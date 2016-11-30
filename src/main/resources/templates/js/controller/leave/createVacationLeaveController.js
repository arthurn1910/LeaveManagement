/**
 * Created by arthurn on 01.12.16.
 */
/**
 * Created by arthurn on 30.11.16.
 */
angular.module('leaveManagement')
    .controller('createVacationLeaveController', function ($scope,$http,$window) {
        $scope.type='';
        $scope.dateStart='';
        $scope.dateEnd='';
        $scope.flag=true;

        $scope.Date = function() {
            $scope.minDate=new Date();
            var dateEnd=new Date(new Date().setFullYear(new Date().getFullYear() + 1));
            dateEnd.setMonth(11);
            dateEnd.setDate(31);
            $scope.maxDate=dateEnd;
            console.log($scope.minDate);
            console.log($scope.maxDate);
            $(function(){
                $('[type="date"]').prop('min', function(){
                    return $scope.minDate.toJSON().split('T')[0];
                });
            });
            $(function(){
                $('[type="date"]').prop('max', function(){
                    return $scope.maxDate.toJSON().split('T')[0];
                });
            });
        }


        $scope.create = function() {
            $scope.leave=[$scope.type, $scope.dateStart, $scope.lenght]
            $http.post('/createParentalLeave',$scope.leave)
                .then(function successCallback(response) {
                    $scope.message = response.data;
                    $scope.flag=false;
                }, function errorCallback(error) {
                    $scope.messageRegister = "Error.";
                });

        }

        $scope.return = function() {
            $http.get('/getCreateLeave').then(function successCallback(response) {
                $window.location.href = response.data;
            });
        }


        $scope.Date();

    });