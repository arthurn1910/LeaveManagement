/**
 * Created by Medion on 2016-10-04.
 */
'use strict';

angular.module('leaveManagement').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.registerDTO={login:'12',password:'zaq12wsx',confirmPassword:'zaq12wsx',email:'saass@op.pl',name:'zxcxzcx',lastname:'zcxzcxxzc'};
    self.reset = reset;


    function createUser(){
        UserService.createUser(registerDTO)
            .then(
                function(errResponse){
                    console.error('Error while creating User');
                }
            );
    }


    function reset(){
        self.user={id:null,username:'',password:'',confirmPassword:'',name:'',email:'',lastname:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
