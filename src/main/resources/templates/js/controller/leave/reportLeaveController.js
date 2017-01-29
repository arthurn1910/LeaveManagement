/**
 * Created by arthurn on 04.12.16.
 */
angular.module('leaveManagement')
    .controller('reportLeaveController', function ($scope,$http) {
        $scope.name='';
        $scope.getLeave = function(){
            $http.get('/getLeave')
                .then(function successCallback(response) {
                    $scope.leaveList = response.data;
                    $scope.reportLeave678=$scope.leaveList.slice(0);
                    setDate();
                }, function errorCallback(response) {
                    $window.location.href = response.data;
                });
        };

        $('#filterName').on( 'keyup', function () {
            filterByName();
            $scope.$apply();
        } );

        var filterByName = function(){
            var list=[];
            for(var i=0;i<$scope.leaveList.length;i++) {
                var tmp=$scope.leaveList[i].account.name+" "+$scope.leaveList[i].account.lastname;
                if (tmp.toUpperCase().indexOf($scope.name.toUpperCase()) > -1) {
                    list.push($scope.leaveList[i])
                }
            }


            while($scope.reportLeave678.length>0)
                $scope.reportLeave678.pop();
            $scope.reportLeave678=list;
        };

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

        $scope.print = function() {
            var divToPrint=document.getElementById("print");
            var newWin= window.open("");
            newWin.document.write(divToPrint.outerHTML);
            newWin.print();
            newWin.close();
        }


        $scope.getLeave();

    })