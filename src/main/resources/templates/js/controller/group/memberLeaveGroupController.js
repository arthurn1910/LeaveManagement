/**
 * Created by arthurn on 20.11.16.
 */
angular.module('leaveManagement')
    .controller('memberLeaveGroupController', function ($scope,$http,$window) {
        $scope.name='';
        $('#filterName').on( 'keyup', function () {
            filterByName();
            $scope.$apply();
        } );
        $scope.leaveInGroup;

        var filterByName = function(){
            var list=[];
            for(var i=0;i<$scope.approvedLeave.length;i++) {
                var tmp=$scope.approvedLeave[i].account.name+" "+$scope.approvedLeave[i].account.lastname;
                console.log(tmp);
                console.log($scope.name);
                console.log(tmp.toUpperCase())
                console.log($scope.name.toUpperCase())
                console.log(tmp.toUpperCase().indexOf($scope.name.toUpperCase()))
                if (tmp.toUpperCase().indexOf($scope.name.toUpperCase()) > -1) {
                    console.log(list)
                    list.push($scope.approvedLeave[i])
                }
            }
            console.log("a2 ")
            console.log(list)


            while($scope.leaveInGroupShow.length>0)
                $scope.leaveInGroupShow.pop();
            console.log($scope.leaveInGroupShow)
            $scope.leaveInGroupShow=list;
            console.log($scope.leaveInGroupShow)
        };

        $scope.getLeaveInGroup = function(){
            $http.get('/getLeaveInGroup')
                .then(function successCallback(response) {
                    $scope.leaveInGroup = response.data;
                    $scope.setLeave();
                }, function errorCallback(response) {
                    $window.location.href = response.data;
                });
        };

        $scope.getDate = function(row){
            return new Date(row+3600*1000).toISOString().split('T')[0];
        };

        $scope.setLeave = function(){
            $scope.plannedLeave=[];
            $scope.approvedLeave=[];
            for(var i=0;i<$scope.leaveInGroup.length;i++){
                if($scope.leaveInGroup[i].active==true) {
                    if ($scope.leaveInGroup[i].confirm == true) {
                        $scope.approvedLeave.push($scope.leaveInGroup[i]);
                    } else {
                        $scope.plannedLeave.push($scope.leaveInGroup[i]);
                    }
                }
            }
            $scope.leaveInGroupShow=$scope.approvedLeave.slice(0);
            setDate();
        };

        $scope.confirm = function(row) {
            $http.post('/confirmLeave', row).then(function successCallback(response) {
                $scope.message=response.data;
                $scope.getLeaveInGroup();
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }

        $scope.reject = function(row) {
            $http.post('/rejectLeave', row).then(function successCallback(response) {
                $scope.message=response.data;
                $scope.getLeaveInGroup();
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }



        var setDate = function () {
            var flag=true;
            var dateStart=new Date();
            var dateEnd=new Date();

            for(var i=0;i<$scope.leaveInGroupShow.length;i++){
                if(flag==true){
                    dateStart=new Date($scope.approvedLeave[i].dateStart);
                    dateEnd=new Date($scope.approvedLeave[i].dateEnd);
                    flag=false;
                }else{
                    if(dateStart>$scope.approvedLeave[i].dateStart){
                        dateStart=new Date($scope.approvedLeave[i].dateStart);
                    }
                    if(dateEnd<$scope.approvedLeave[i].dateEnd){
                        dateEnd=new Date($scope.approvedLeave[i].dateEnd);
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


        var filterByDate = function(dateStart, dateEnd) {
            var list=[];
            for(var i=0;i<$scope.approvedLeave.length;i++) {
                if (((new Date($scope.approvedLeave[i].dateEnd) < dateStart) || (new Date($scope.approvedLeave[i].dateStart) > dateEnd) )==false) {
                    list.push($scope.approvedLeave[i])
                }
            }


            while($scope.leaveInGroupShow.length>0)
                $scope.leaveInGroupShow.pop();
            $scope.leaveInGroupShow=list;
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



        $scope.getLeaveInGroup();
    })