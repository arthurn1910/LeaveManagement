/**
 * Created by arthurn on 12.11.16.
 */
angular.module('leaveManagement')
    .controller('showGroupController', function ($scope,$http,$window) {
        $scope.flag=0;
        $scope.getTeamGroup = function(){
            $http.get('/getTeamGroup')
                .then(function successCallback(response) {
                    $scope.flag=1;
                    $scope.teamGroup = response.data;
                });
        };

        $scope.getMemberList = function(){
            $http.get('/listMemberGroup')
                .then(function successCallback(response) {
                    $scope.setLists(response.data.teamGroupMemberList)
                }, function errorCallback(response) {
                    $window.location.href = response.data;
                });
        };

        $scope.getUserGroupDTO = function(){
            $http.get('/getUserGroupDTO')
                .then(function successCallback(response) {
                    $scope.userGroupDto = response.data;
                    $scope.getTeamGroup();
                    $scope.getMemberList();
                }, function errorCallback(response) {
                    $window.location.href = response.data;
                });
        };

        $scope.setLists = function(data){
            $scope.memberList=[];
            $scope.applyGroup=[];
            for(var i=0;i<data.length;i++){
                if(data[i].active==true) {
                    $scope.memberList.push(data[i]);
                } else{
                    $scope.applyGroup.push(data[i]);
                }
            }
        };

        $scope.activeTab = 1;

        $scope.setActiveTab = function(tabToSet) {
            $scope.activeTab = tabToSet;
        }

        $scope.return = function() {
            $http.get('/getGroupList').then(function successCallback(response) {
                $window.location.href = response.data;
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }

        $scope.administration = function() {
            $http.get('/getAdministrationGroup').then(function successCallback(response) {
                $window.location.href = response.data;
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }

        $scope.remove = function(data) {
            var list=[data.id, data.employee.login]
            $http.post('/removeMember', list).then(function successCallback(response) {
                $scope.getMemberList();
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }

        $scope.checkManager = function(login) {
            if($scope.flag==1){
                if($scope.userGroupDto.login==$scope.teamGroup.manager.login){
                    if(login==$scope.teamGroup.manager.login) {
                        return true
                    }else{
                        return false;
                    }
                }
            }
            return true;
        }
        $scope.getUserGroupDTO();
    })