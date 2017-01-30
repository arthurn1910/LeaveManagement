    /**
     * Created by arthurn on 10.11.16.
     */
angular.module('leaveManagement')
    .controller('changeUserRoleController', function ($scope,$http,$window) {
        $scope.roleCollection=[{name:"ADMINISTRATOR", active:false},{name:"PRACOWNIK", active:false},
            {name:"MENADzER", active:false},{name:"KSIeGOWY", active:false}];
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

        $scope.checkIfAvailable = function(){
            if( $scope.roleCollection[0].active==true)
                return true;
            else if($scope.roleCollection[1].active==true)
                return true;
            else if($scope.roleCollection[2].active==true)
                return true;
            else if($scope.roleCollection[3].active==true)
                return true;
            return false;
        }

        $scope.getUserAccount = function() {
            $http.get('/getUserRole').then(function successCallback(response) {
                $scope.userRole = response.data;
                $scope.setUserRole();
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        };
        $scope.changeRole = function(roleName, status) {
            var roleNamePost='';
            if(roleName=="ADMINISTRATOR")
                roleNamePost="ADMINISTRATOR";
            else if(roleName=="MENADzER")
                roleNamePost="MANAGER";
            else if(roleName=="PRACOWNIK")
                roleNamePost="EMPLOYEE";
            else if(roleName=="KSIeGOWY")
                roleNamePost="ACCOUNTANT";

            var data=[$scope.userRole[0], roleNamePost, status];
            $http.post('/setUserRole',data).then(function successCallback(response) {
                $scope.getUserAccount();
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        };

        $scope.getUserAccount();

    })