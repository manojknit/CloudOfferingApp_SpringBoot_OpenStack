
angular.module('myApp', []);
angular.module('myApp').controller('myController',function($scope, $http) {
        $scope.title = "MyQuote App";
        //$scope.quotes = $resource('/api/quotes').query();
        console.log('JSON created: ' + $scope.title); 
        $http.get('http://rest-service.guides.spring.io/greeting').
        then(function(response) {
            $scope.greeting = response.data;
        });

        $http.get('http://localhost:8080/instances/').
        then(function(response) {
            $scope.vtinstences = response.data;
        });

        $scope.CreateInstance = function(instancetype) {
            //Post call by param
            console.log("create wordpress");
            var payload='{"instanceType":'+angular.toJson(instancetype)+'}';
            console.log(payload);
            $http({
                method : "POST",
                url : "http://localhost:8080/instances/",
                data : payload,
                headers : {
                    'Content-Type' : 'application/json'
                }
            }).then( $scope.status = 'Success');
        };
        //var day  = Date.now();
       
    });