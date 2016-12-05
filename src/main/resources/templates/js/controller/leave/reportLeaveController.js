/**
 * Created by arthurn on 04.12.16.
 */
angular.module('leaveManagement')
    .controller('reportLeaveController', function ($scope,$http) {
        $scope.name='';
        $scope.getLeave = function(){
            $http.get('/getLeave')
                .then(function(response) {
                    $scope.leaveList = response.data;
                    console.log($scope.leaveList);
                    for(var i=0;i<$scope.leaveList.length;i++){
                        console.log('+++')
                        console.log(new Date($scope.leaveList[i].dateStart))
                        console.log(new Date($scope.leaveList[i].dateEnd))
                    }
                    $scope.reportLeave678=$scope.leaveList;
                    console.log($scope.leaveList);
                    setDate();

                });
        };

        $scope.getDate = function(row){
            return new Date(row+3600*1000).toISOString().split('T')[0];
        };

        var setDate = function () {
            console.log('*');
            var flag=true;
            var dateStart=new Date();
            var dateEnd=new Date();

            for(var i=0;i<$scope.leaveList.length;i++){
                if(flag==true){
                    console.log('a');
                    console.log(new Date($scope.leaveList[i].dateStart));
                    console.log(new Date($scope.leaveList[i].dateEnd));
                    dateStart=new Date($scope.leaveList[i].dateStart);
                    dateEnd=new Date($scope.leaveList[i].dateEnd);
                    flag=false;
                }else{
                    console.log('b');
                    console.log(new Date($scope.leaveList[i].dateStart));
                    console.log(new Date($scope.leaveList[i].dateEnd));
                    console.log('c');
                    console.log(new Date(dateStart));
                    console.log(new Date(dateEnd));
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
            console.log('list '+list.length);
            for(var i=0;i<$scope.leaveList.length;i++) {
                console.log('%!2')
                console.log(dateStart)
                console.log(dateEnd)
                console.log(new Date($scope.leaveList[i].dateStart))
                console.log(new Date($scope.leaveList[i].dateEnd))
                console.log('i');
                console.log(new Date($scope.leaveList[i].dateEnd) < new Date(dateStart))
                console.log(new Date($scope.leaveList[i].dateStart) > new Date(dateEnd))
                console.log((new Date($scope.leaveList[i].dateEnd) < new Date(dateStart)) ||
                    (new Date($scope.leaveList[i].dateStart) > new Date(dateEnd)))

                if (((new Date($scope.leaveList[i].dateEnd) < dateStart) || (new Date($scope.leaveList[i].dateStart) > dateEnd) )==false) {
                    list.push($scope.leaveList[i])
                    console.log('o');
                }
            }


            console.log('&*(');
            while($scope.reportLeave678.length>0)
                $scope.reportLeave678.pop();
            console.log(list);
            console.log('rep b');
            console.log($scope.reportLeave678);
            $scope.reportLeave678=list;
            console.log('rep a');
            console.log($scope.reportLeave678);
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
            console.log('123')
            console.log($scope.reportLeave678)
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