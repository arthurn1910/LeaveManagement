/**
 * Created by arthurn on 01.12.16.
 */
/**
 * Created by arthurn on 30.11.16.
 */
angular.module('leaveManagement')
    .controller('createVacationLeaveController', function ($scope,$http,$window) {
        $scope.type='';
        $scope.dateStart='';
        $scope.dateEnd='';
        $scope.flag=true;

        $scope.Date = function() {
            var minDate=new Date();
            var dateEnd=new Date(new Date().setFullYear(new Date().getFullYear() + 1));
            dateEnd.setMonth(11);
            dateEnd.setDate(31);
            var maxDate=dateEnd;
            $(function(){
                $('[name="dateStart"]').prop('min', function(){
                    return minDate.toJSON().split('T')[0];
                });
            });
            $(function(){
                $('[name="dateStart"]').prop('max', function(){
                    return maxDate.toJSON().split('T')[0];
                });
            });
        }

        $scope.deleteDate = function() {
            $scope.dateStart='';
            $scope.dateEnd='';
        }

        $scope.changeDateEnd = function() {
            $scope.dateEnd='';
            var dateTmp=$scope.dateStart
            console.log($scope.dateStart)
            dateTmp.setDate($scope.dateStart.getDate()+1);
            $(function(){
                $('[name="dateEnd"]').prop('min', function(){
                    return dateTmp.toJSON().split('T')[0];
                });
            });

            if($scope.type==1) {
                var dateEnd = new Date($scope.dateStart);
                countDate($scope.dateStart);

            }else if($scope.type==2){
                var dateEnd=new Date(new Date().setFullYear(new Date().getFullYear() + 1));
                dateEnd.setMonth(11);
                dateEnd.setDate(31);
                var dateStart2 = new Date($scope.dateStart)
                dateStart2.setDate(dateStart2.getDate()-1)
                setCloseDate(dateStart2,dateEnd);
            }
        }

        var countDate=function(dateStart){
            $http.get('/getLeaveDetails').then(function successCallback(response) {
                $scope.thisYear = response.data.leaveThisYear - response.data.reamainingVacationLeaveThisYear;
                $scope.lastYear = response.data.leaveLastYear - response.data.reamainingVacationLeaveLastYear;
                var date=countDateEnd(dateStart);
                var dateStart2 = new Date($scope.dateStart)
                dateStart2.setDate(dateStart2.getDate()-1)
                setCloseDate(dateStart2,date);
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }

        var countDateEnd=function(dateStart) {
            var dateTmp = new Date(dateStart);
            var days = $scope.thisYear + $scope.lastYear;
            while (days >= 1) {
                dateTmp.setDate(dateTmp.getDate() + 1);
                if (dateTmp.getDay() != 0 && dateTmp.getDay() != 6) {
                    days--;
                }
            }
            console.log(dateTmp)
            return new Date(dateTmp);
        }


        var setCloseDate=function(dateStart1, dateEnd1){
            $http.get('/getBlockDate').then(function successCallback(response) {
                console.log(response.data);
                var blockDate=response.data;
                var dateTMP=new Date(dateEnd1);

                for(var i=0;i<blockDate.length;i++){
                    console.log("$")
                    var blockdate2=new Date(blockDate[i]);
                    console.log(blockdate2)
                    console.log(blockdate2.getTime())
                    console.log(dateStart1.getTime())
                    if(blockdate2.getTime()===dateStart1.getTime()){
                        var date5=new Date(blockdate2.getTime());
                        date5.setDate(date5.getDate()-1);
                        dateTMP=new Date(date5.getTime())
                        console.log("3")
                        console.log(dateTMP.getTime())
                    }else if(blockdate2.getTime()>dateStart1.getTime() & blockdate2.getTime()<=dateEnd1.getTime() & blockdate2.getTime()<=dateTMP.getTime()){
                        var date5=new Date(blockdate2.getTime());
                        date5.setDate(date5.getDate());
                        dateTMP=new Date(date5.getTime())
                        console.log("4")
                        console.log(dateTMP.getTime())
                    }
                }
                console.log(dateTMP.toJSON().split('T')[0])
                $(function () {
                    $('[name="dateEnd"]').prop('max', function (){
                        return dateTMP.toJSON().split('T')[0];
                    });
                });

            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }


        $scope.create = function() {
            console.log('*');
            console.log($scope.dateStart);
            console.log($scope.dateEnd);
            console.log('**');
            $scope.leave=[$scope.type, $scope.dateStart, $scope.dateEnd,$scope.dateEnd]
            $http.post('/createLeave',$scope.leave)
                .then(function successCallback(response) {
                    $scope.message = response.data;
                    $scope.flag=false;
                }, function errorCallback(response) {
                    $window.location.href = response.data;
                });

        }

        $scope.return = function() {
            $http.get('/getCreateLeave').then(function successCallback(response) {
                $window.location.href = response.data;
            }, function errorCallback(response) {
                $window.location.href = response.data;
            });
        }


        $scope.Date();

    });