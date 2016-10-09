/**
 * Created by Medion on 2016-10-04.
 */
'use strict';
angular.module('leaveManagement').factory('UserService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = '/register';

    var factory = {
        createUser: createUser
    };

    return factory;

    function createUser(registerDTO) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, registerDTO)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating User');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);