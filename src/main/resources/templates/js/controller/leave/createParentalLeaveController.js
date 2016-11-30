/**
 * Created by arthurn on 30.11.16.
 */
angular.module('leaveManagement')
    .controller('createParentalLeaveController', function ($scope,$http,$window) {
        $scope.type='';
        $scope.dateStart='';
        $scope.lenght='';
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

        $scope.changeTypeLeave = function() {
            $scope.lenght='';
            if($scope.type==5){
                $scope.selectLenght=[{id:14},{id:20}];
            }else if($scope.type==6) {
                $scope.selectLenght = [{id: 32}, {id: 34}];
            }else if($scope.type==3) {
                $scope.selectLenght = [{id: 6}];
            }
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