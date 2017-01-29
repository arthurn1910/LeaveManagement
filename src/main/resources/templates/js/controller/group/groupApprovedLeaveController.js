/**
 * Created by arthurn on 19.11.16.
 */
angular.module('leaveManagement')
    .controller('groupPlannedLeaveController', function ($scope,$http,$window, $location) {
        $scope.getGroupLeave = function(){
            $http.get('/getGroupLeave')
                .then(function successCallback(response) {
                    $scope.groupLeave = response.data;
                    console.log($scope.groupLeave);
                    // $scope.setLeave($scope.groupLeave);
                }, function errorCallback(response) {
                    $window.location.href = response.data;
                });
        };

        $scope.setLeave = function(groupLeave){
            $scope.plannedLeave=[];
            $scope.approvedLeave=[];
            for(var i=0;i<groupLeave.length;i++){
                if(data[i].active==true) {
                    $scope.memberList.push(data[i]);
                } else{
                    $scope.applyGroup.push(data[i]);
                }
            }
        };

        $scope.getDate = function(row){
            return new Date(row+3600*1000).toISOString().split('T')[0];
        };

        var filterByDate = function(dateStart, dateEnd) {
            var list=[];
            for(var i=0;i<$scope.leaveList.length;i++) {
                if (((new Date($scope.leaveList[i].dateEnd) < dateStart) || (new Date($scope.leaveList[i].dateStart) > dateEnd) )==false) {
                    list.push($scope.leaveList[i])
                }
            }


            while($scope.reportLeave678.length>0)
                $scope.reportLeave678.pop();
            $scope.reportLeave678=list;
        }

        $("#slider").on("valuesChanging", function(e, data){
            var dateStart=new Date(data.values.min);
            var dateEnd=new Date(data.values.max);
            dateStart.setMilliseconds(0);
            dateStart.setSeconds(0);
            dateStart.setMinutes(0);
            dateStart.setHours(0);
            dateEnd.setMilliseconds(0);
            dateEnd.setSeconds(0);
            dateEnd.setMinutes(0);
            dateEnd.setHours(0);
            filterByDate(dateStart,dateEnd);
            $scope.$apply();
        });

        var setDate = function () {
            var flag=true;
            var dateStart=new Date();
            var dateEnd=new Date();

            for(var i=0;i<$scope.leaveList.length;i++){
                if(flag==true){
                    dateStart=new Date($scope.leaveList[i].dateStart);
                    dateEnd=new Date($scope.leaveList[i].dateEnd);
                    flag=false;
                }else{
                    if(dateStart>$scope.leaveList[i].dateStart){
                        dateStart=new Date($scope.leaveList[i].dateStart);
                    }
                    if(dateEnd<$scope.leaveList[i].dateEnd){
                        dateEnd=new Date($scope.leaveList[i].dateEnd);
                    }

                }
            }
            $("#slider").dateRangeSlider({
                defaultValues:{
                    min: new Date(dateStart),
                    max: new Date(dateEnd)
                },
                bounds: {
                    min: new Date(dateStart),
                    max: new Date(dateEnd)
                }
            });


        }

        $('#filterName').on( 'keyup', function () {
            filterByName();
            $scope.$apply();
        } );

        var filterByName = function(){
            var list=[];
            for(var i=0;i<$scope.leaveList.length;i++) {
                var tmp=$scope.leaveList[i].account.name+" "+$scope.leaveList[i].account.lastname;
                console.log(tmp);
                console.log($scope.name);
                console.log(tmp.toUpperCase())
                console.log($scope.name.toUpperCase())
                console.log(tmp.toUpperCase().indexOf($scope.name.toUpperCase()));
                if (tmp.toUpperCase().indexOf($scope.name.toUpperCase()) > -1) {
                    list.push($scope.leaveList[i])
                }
                // if (((new Date($scope.leaveList[i].dateEnd) < dateStart) || (new Date($scope.leaveList[i].dateStart) > dateEnd) )==false) {
                //     list.push($scope.leaveList[i])
                // }
            }


            while($scope.reportLeave678.length>0)
                $scope.reportLeave678.pop();
            $scope.reportLeave678=list;
        };

        $scope.getGroupLeave();

    })