/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')
    .controller('changeUserDataController', function ($scope,$http,$window) {
        var year = [];
        var month = [];
        var day = [];
        var workTime = [{id:50},{id:55},{id:60},{id:65},{id:70},{id:75},{id:80},{id:85},
            {id:90},{id:95},{id:100}]
        for(var i=0 ;i<60;i++) {
            year[i] = {
                id: i,
                name: i,
                selected: false
            };
            if (i <= 30) {
                day[i] = {
                    id: i,
                    name: i,
                    selected: false
                };
                if (i <= 12) {
                    month[i] = {
                        id: i,
                        name: i,
                        selected: false
                    };
                }
            }
        }
        $scope.setSelectBox= function(){
            $scope.modelExpirience.day[$scope.userAccount.expirienceYear].selected=true;
        }
        $scope.modelExpirience={year,month,day,workTime}

        $scope.getUserAccount = function() {
            $http.get('/getUserAccount').then(function successCallback(response) {
                $scope.userAccount = response.data;
                $scope.userAccount.startingDate=new Date($scope.userAccount.startingDate);
                $scope.setSelectBox();
                $scope.startingDate=$scope.userAccount.startingDate;

            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }
        $scope.saveUserAccount = function() {
            var data=[$scope.userAccount.login, $scope.userAccount.name,$scope.userAccount.lastname
                ,$scope.userAccount.email, $scope.userAccount.workTime,$scope.userAccount.startingDate, $scope.userAccount.expirienceYear
                , $scope.userAccount.expirienceMonth, $scope.userAccount.expirienceDay]
            $http.post('/saveUserAccount', data).then(function successCallback(response) {
                $scope.message=response.data;
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }

        $scope.return = function() {
            $window.location.href="/usersList";
        }

        $scope.getUserAccount();

    })