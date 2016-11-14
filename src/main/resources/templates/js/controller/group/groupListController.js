/**
 * Created by arthurn on 10.11.16.
 */
angular.module('leaveManagement')
    .controller('groupListController', function ($scope,$http,$window, $location) {
        $scope.getGroupList = function(){
            $http.get('/groupListData')
                .then(function(response) {
                    $scope.rowCollection = response.data;
                    console.log($scope.rowCollection);
                });
        };
        $scope.getUserGroupDTO = function(){
            $http.get('/getUserGroupDTO')
                .then(function(response) {
                    $scope.userGroupDto = response.data;
                    console.log($scope.userGroupDto);
                });
        };

        $scope.createGroup = function() {
            $http.get('/getCreateGroup').success(function(response) {
                $window.location.href=response;
            }).error(function(){
                console.log("error createGroup");
            });
        }
        $scope.showGroup = function(data) {
            console.log('***');
            console.log(data.id);
            $http.post('/showGroup',data.id).success(function(response) {
                $window.location.href=response;
            }).error(function(){
                console.log("error showGroup");
            });
        }

        $scope.getGroupList();
        $scope.getUserGroupDTO();

    })