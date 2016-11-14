// /**
//  * Created by Medion on 2016-10-04.
//  */
// 'use strict';
//
// angular.module('leaveManagement', [])
// //     .controller('UserController', function($scope, $http) {
// //         $scope.isAuthenticated = function() {
// //             $http.get('/isAuthenticated').then(function(response) {
// //                 $scope.greeting = response.data;
// //                 if($scope.greeting.authenticated == false){
// //                     $("li.login").show();
// //                     $("li.register").show();
// //                     $("li.logout").hide();
// //                     $("li.settings").hide();
// //                     $("li.administration").hide();
// //                     $("li.groups").hide();
// //                 }
// //                 if($scope.greeting.authenticated == true){
// //                     $("li.login").hide();
// //                     $("li.register").hide();
// //                     $("li.settings").show();
// //                     $("li.logout").show();
// //                     $("li.administration").show();
// //                     $("li.groups").show();
// //                 }
// //             });
// //         }
// //         $scope.isAuthenticated();
// //
// // })
//     .controller('RegisterController', function($scope, $http) {
//         $scope.messageRegister = "";
//         $scope.registerDTO = {
//             login:'',
//             password:'',
//             email:'',
//             name:'',
//             lastname:''
//         };
//         $scope.confirmPassword="";
//         $scope.register = function() {
//             $http.post('/register',$scope.registerDTO)
//                 .then(function successCallback(response) {
//                     $scope.messageRegister = "Account was created.";
//                     $("form.css-form").hide();
//                 }, function errorCallback(error) {
//                     $scope.messageRegister = "Error.";
//                 });
//         }
// })
//
//     .controller('usersListController', function ($scope,$http,$window, $location) {
//         $scope.getUsersList = function(){
//             $http.get('/usersListData')
//                 .then(function(response) {
//                     $scope.rowCollection = response.data;
//                 });
//         }
//
//         $scope.changeUserActiveStatus = function(data) {
//             var account =[data.login, data.version];
//             $http.post('/changeUserActiveStatus',account).then(function(response) {
//                 $scope.getUsersList();
//             });
//
//         }
//
//         $scope.changeUserConfirmStatus = function(data) {
//             var account =[data.login, data.version];
//             $http.post('/changeUserConfirmStatus',account);
//         }
//
//         $scope.changeUserPassword = function(data) {
//             $http.post('/changeUserPassword',data.login).success(function(response) {
//                 $window.location.href=response;
//             }).error(function(){
//                 console.log("error changeUserPassword");
//             });
//         }
//
//         $scope.changeUserData = function(data) {
//             $http.post('/editUserAccount',data.login).success(function(response) {
//                 $window.location.href=response;
//             }).error(function(){
//                 console.log("error changeUserData");
//             });
//         }
//
//         $scope.changeUserRole = function(data) {
//             $http.post('/changeUserRole',data.login).success(function(response) {
//                 $window.location.href=response;
//             }).error(function(){
//                 console.log("error changeUserRole");
//             });
//         }
//         $scope.getUsersList();
//
// })
//     .controller('userAccountController', function ($scope,$http,$window, $location) {
//         $scope.accessLevel=function(data){
//             var accesslevel ='';
//             for (var level in data) {
//                 accesslevel += data[level] + " ";
//             }
//             return accesslevel;
//         }
//
//         $scope.accountDetails = function(){
//             $http.get('/getAccountDetails')
//                 .then(function(response) {
//                     $scope.account = response.data;
//                 });
//         }
//
//         $scope.change = function() {
//             $http.get('/editAccountGet').success(function(response) {
//                 $window.location.href=response;
//             }).error(function(){
//                 console.log("error changeUserData");
//             });
//         }
//
//         $scope.accountDetails();
// })
//     .controller('changeUserPasswordController', function ($scope,$http,$window) {
//         $scope.password="";
//         $scope.getUserAccount = function() {
//             $http.get('/getUserAccount').success(function (response) {
//                 $scope.userAccount = response;
//             }).error(function () {
//                 console.log("error");
//             });
//         }
//         $scope.saveUserPassword = function() {
//             var data=[$scope.userAccount.login, $scope.password]
//             $http.post('/saveUserPassword', data).success(function(response) {
//                 $scope.message=response;
//             }).error(function(){
//                 console.log("error");
//             });
//         }
//
//         $scope.return = function() {
//             $window.location.href="/usersList";
//         }
//
//         $scope.getUserAccount();
// })
//     .controller('changePasswordController', function ($scope,$http,$window) {
//         $scope.actualPassword="";
//         $scope.newPassword="";
//         $scope.confirmPassword="";
//         $scope.savePassword = function() {
//             var data=[$scope.actualPassword, $scope.newPassword]
//             $http.post('/savePassword', data).success(function(response) {
//                 $scope.message=response;
//                 $scope.actualPassword="";
//                 $scope.newPassword="";
//                 $scope.confirmPassword="";
//             }).error(function(){
//                 console.log("error");
//             });
//         }
//
//     })
//     .controller('changeUserDataController', function ($scope,$http,$window) {
//         var year = [];
//         var month = [];
//         var day = [];
//         for(var i=0 ;i<60;i++) {
//             year[i] = {
//                 id: i,
//                 name: i,
//                 selected: false
//             };
//             if (i <= 30) {
//                 day[i] = {
//                     id: i,
//                     name: i,
//                     selected: false
//                 };
//                 if (i <= 12) {
//                     month[i] = {
//                         id: i,
//                         name: i,
//                         selected: false
//                     };
//                 }
//             }
//         }
//         $scope.setSelectBox= function(){
//             $scope.modelExpirience.day[$scope.userAccount.expirienceYear].selected=true;
//         }
//         $scope.modelExpirience={year,month,day}
//         $scope.getUserAccount = function() {
//             $http.get('/getUserAccount').success(function (response) {
//                 $scope.userAccount = response;
//                 $scope.userAccount.startingDate=new Date($scope.userAccount.startingDate);
//                 $scope.setSelectBox();
//                 $scope.startingDate=$scope.userAccount.startingDate;
//
//             }).error(function () {
//                 console.log("error");
//             });
//         }
//         $scope.saveUserAccount = function() {
//             var data=[$scope.userAccount.login, $scope.userAccount.name,$scope.userAccount.lastname
//                 ,$scope.userAccount.email,$scope.userAccount.startingDate, $scope.userAccount.expirienceYear
//                 , $scope.userAccount.expirienceMonth, $scope.userAccount.expirienceDay]
//             $http.post('/saveUserAccount', data).success(function(response) {
//                 $scope.message=response;
//             }).error(function(){
//                 console.log("error");
//             });
//         }
//
//         $scope.return = function() {
//             $window.location.href="/usersList";
//         }
//
//         $scope.getUserAccount();
//
// })
//     .controller('changeDataController', function ($scope,$http,$window) {
//         $scope.accountDetails = function(){
//             $http.get('/getAccountDetails')
//                 .then(function(response) {
//                     $scope.account = response.data;
//                 });
//         }
//         $scope.saveAccount = function() {
//             var data=[$scope.account.name,$scope.account.lastname
//                 ,$scope.account.email]
//             $http.post('/saveAccount', data).success(function(response) {
//                 $scope.message=response;
//             }).error(function(){
//                 console.log("error");
//             });
//         }
//         $scope.accountDetails();
//
// })
//     .controller('changeUserRoleController', function ($scope,$http,$window) {
//         $scope.roleCollection=[{name:"ADMINISTRATOR", active:false},{name:"EMPLOYEE", active:false},
//             {name:"MANAGER", active:false},{name:"ACCOUNTANT", active:false}];
//         $scope.return = function() {
//             $window.location.href="/usersList";
//         };
//         $scope.setUserRole = function() {
//             for(var i=0;i<$scope.roleCollection.length;i++){
//                 $scope.roleCollection[i].active=false;
//             }
//             for(var i=1; i<$scope.userRole.length;i++){
//                 var role=$scope.userRole[i];
//                 if(role=="ROLE_ADMINISTRATOR")
//                     $scope.roleCollection[0].active=true;
//                 else if(role=="ROLE_MANAGER")
//                     $scope.roleCollection[2].active=true;
//                 else if(role=="ROLE_EMPLOYEE")
//                     $scope.roleCollection[1].active=true;
//                 else if(role=="ROLE_ACCOUNTANT")
//                     $scope.roleCollection[3].active=true;
//             }
//         };
//         $scope.getUserAccount = function() {
//             $http.get('/getUserRole').success(function (response) {
//                 $scope.userRole = response;
//                 $scope.setUserRole();
//             }).error(function () {
//                 console.log("error");
//             });
//         };
//         $scope.changeRole = function(roleName, status) {
//             var data=[$scope.userRole[0], roleName, status];
//             $http.post('/setUserRole',data).success(function (response) {
//                 $scope.getUserAccount();
//             }).error(function () {
//                 console.log("error");
//             });
//         };
//
//         $scope.getUserAccount();
//
// })
//     .directive('compareTo', function () {
//         return {
//             require: "ngModel",
//             scope: {
//                 otherModelValue: "=compareTo"
//             },
//             link: function(scope, element, attributes, ngModel) {
//
//                 ngModel.$validators.compareTo = function(modelValue) {
//                     return modelValue == scope.otherModelValue;
//                 };
//
//                 scope.$watch("otherModelValue", function() {
//                     ngModel.$validate();
//                 });
//             }
//         }
// });