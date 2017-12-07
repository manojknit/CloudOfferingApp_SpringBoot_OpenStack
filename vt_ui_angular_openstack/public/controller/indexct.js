
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
            var responsedata = response.data;

            var obj = {a: 1, b: 2};
            for (var key in responsedata) {
              //if (responsedataobj.hasOwnProperty(key)) {
                  var id = responsedata[key].id;
                  var email = angular.element($('#useremail')).val();
                if(key%2 == 0)
                {
                    responsedata[key].instanceName = "wordpress-"+id;
                    responsedata[key].instanceURL = email +"-wordpress-"+id+".cloudjibe.com";
                    responsedata[key].instanceType = "wordpress";
                //var val = obj[key];
                //console.log(val);
                }
                else{
                    responsedata[key].instanceName = "cirros-"+id;
                    responsedata[key].instanceURL = email +"-wordpress-"+id+".cloudjibe.com";
                    responsedata[key].instanceType = "cirros";
                }
              //}
            }

            $scope.vtinstences = responsedata;
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