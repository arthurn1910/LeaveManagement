/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')
    .controller('groupListController', function ($scope,$http,$window, $location) {
        $scope.flag=false;

        $scope.getGroupList = function(){
            $http.get('/groupListData')
                .then(function successCallback(response) {
                    $scope.rowCollection = response.data;
                }, function errorCallback(response) {
                    $window.location.href = response.data;
                });
        };
        $scope.getUserGroupDTO = function(){
            $http.get('/getUserGroupDTO')
                .then(function successCallback(response) {
                    $scope.userGroupDto = response.data;
                    $scope.flag=true;
                    console.log($scope.userGroupDto);
                }, function errorCallback(response) {
                    $window.location.href = response.data;
                });
        };

        $scope.createGroup = function() {
            $http.get('/getCreateGroup').then(function successCallback(response) {
                $window.location.href=response.data;
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }
        $scope.showGroup = function(data) {
            $http.post('/showGroup',data.id).then(function successCallback(response) {
                $window.location.href=response.data;
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }

        $scope.checkYourGroup = function(data) {
            if($scope.flag==true) {
                for (var i = 0; i < $scope.userGroupDto.teamGroupDTOList.length; i++) {
                    if ($scope.userGroupDto.teamGroupDTOList[i].id == data.id)
                        return false;
                }
            }
            return true;
        }

        $scope.checkIfUserApply = function(data) {
            if($scope.flag==true) {
                if($scope.userGroupDto.teamGroupDTOList.length==0) {
                    for(var i=0;i<$scope.userGroupDto.applyTeamGroupDTOList.length;i++){
                        if(data.id==$scope.userGroupDto.applyTeamGroupDTOList[i].id)
                            return true;
                    }
                    return false;
                }
            }
            return true;
        }

        $scope.applyToGroup = function(data) {
            console.log('juz');
            $http.post('/applyToGroup',data.id).then(function successCallback(response) {
                $scope.getUserGroupDTO();
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }


        $scope.getGroupList();
        $scope.getUserGroupDTO();

    })