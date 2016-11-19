/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')
    .controller('changeUserDataController', function ($scope,$http,$window) {
        var year = [];
        var month = [];
        var day = [];
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
        $scope.modelExpirience={year,month,day}
        $scope.getUserAccount = function() {
            $http.get('/getUserAccount').success(function (response) {
                $scope.userAccount = response;
                $scope.userAccount.startingDate=new Date($scope.userAccount.startingDate);
                $scope.setSelectBox();
                $scope.startingDate=$scope.userAccount.startingDate;

            }).error(function () {
                console.log("error");
            });
        }
        $scope.saveUserAccount = function() {
            var data=[$scope.userAccount.login, $scope.userAccount.name,$scope.userAccount.lastname
                ,$scope.userAccount.email,$scope.userAccount.startingDate, $scope.userAccount.expirienceYear
                , $scope.userAccount.expirienceMonth, $scope.userAccount.expirienceDay]
            $http.post('/saveUserAccount', data).success(function(response) {
                $scope.message=response;
            }).error(function(){
                console.log("error");
            });
        }

        $scope.return = function() {
            $window.location.href="/usersList";
        }

        $scope.getUserAccount();

    })