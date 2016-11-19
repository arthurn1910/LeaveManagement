    /**
     * Created by arthurn on 10.11.16.
     */
angular.module('leaveManagement')
    .controller('changeUserRoleController', function ($scope,$http,$window) {
        $scope.roleCollection=[{name:"ADMINISTRATOR", active:false},{name:"EMPLOYEE", active:false},
            {name:"MANAGER", active:false},{name:"ACCOUNTANT", active:false}];
        $scope.return = function() {
            $window.location.href="/usersList";
        };
        $scope.setUserRole = function() {
            for(var i=0;i<$scope.roleCollection.length;i++){
                $scope.roleCollection[i].active=false;
            }
            for(var i=1; i<$scope.userRole.length;i++){
                var role=$scope.userRole[i];
                if(role=="ROLE_ADMINISTRATOR")
                    $scope.roleCollection[0].active=true;
                else if(role=="ROLE_MANAGER")
                    $scope.roleCollection[2].active=true;
                else if(role=="ROLE_EMPLOYEE")
                    $scope.roleCollection[1].active=true;
                else if(role=="ROLE_ACCOUNTANT")
                    $scope.roleCollection[3].active=true;
            }
        };
        $scope.getUserAccount = function() {
            $http.get('/getUserRole').success(function (response) {
                $scope.userRole = response;
                $scope.setUserRole();
            }).error(function () {
                console.log("error");
            });
        };
        $scope.changeRole = function(roleName, status) {
            var data=[$scope.userRole[0], roleName, status];
            $http.post('/setUserRole',data).success(function (response) {
                $scope.getUserAccount();
            }).error(function () {
                console.log("error");
            });
        };

        $scope.getUserAccount();

    })