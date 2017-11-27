# CloudOfferingApp_SpringBoot_OpenStack_REST_API
This document details out the REST API services

Create User

http://localhost:8080/users/
HTTP.POST

{
  "username":"sunder.thyagarajan@gmail.com",
  "password":"Welcome1"
}

Authenticate User

http://localhost:8080/users/authenticate
HTTP.POST

{
  "username":"sunder.thyagarajan@gmail.com.com",
  "password":"Welcome1"
}

Response errorMessage = "Success" or "Failure"


Get All Users

http://localhost:8080/users/
HTTP.GET

[{"id":1,"username":"sunder.thyagarajan@oracle.com","password":"Welcome1","instances":[{"id":12,"instanceId":"OpenStack12345V","instanceURL":"http://localhost:7001/connect","createdDate":"2015-07-07 10:00:00","terminatedDate":null,"instanceType":"WordPress","status":"Create"},{"id":11,"instanceId":"12345V23","instanceURL":"http://localhost:7001/connect","createdDate":"2015-07-07 10:00:00","terminatedDate":"2015-07-07 12:00:00","instanceType":"Cirros","status":"Terminated"}]},{"id":13,"username":"ravi.katta@oracle.com","password":"Welcome1","instances":[{"id":14,"instanceId":"OpenStack1234","instanceURL":"http://localhost:7001/connect","createdDate":"2015-07-07 10:00:00","terminatedDate":null,"instanceType":"WordPress","status":"Create"},{"id":15,"instanceId":"OpenStack57896","instanceURL":"http://localhost:7001/connect","createdDate":"2015-07-07 10:00:00","terminatedDate":null,"instanceType":"WordPress","status":"Create"}]}]


Get All Instances

http://localhost:8080/instances/
HTTP.GET

[{"id":12,"instanceId":"OpenStack12345V","instanceURL":"http://localhost:7001/connect","createdDate":"2015-07-07 10:00:00","terminatedDate":null,"instanceType":"WordPress","status":"Create"},{"id":11,"instanceId":"12345V23","instanceURL":"http://localhost:7001/connect","createdDate":"2015-07-07 10:00:00","terminatedDate":"2015-07-07 12:00:00","instanceType":"Cirros","status":"Terminated"},{"id":14,"instanceId":"OpenStack1234","instanceURL":"http://localhost:7001/connect","createdDate":"2015-07-07 10:00:00","terminatedDate":null,"instanceType":"WordPress","status":"Create"},{"id":15,"instanceId":"OpenStack57896","instanceURL":"http://localhost:7001/connect","createdDate":"2015-07-07 10:00:00","terminatedDate":null,"instanceType":"WordPress","status":"Create"}]



Create an Instance
http://localhost:8080/instances/
HTTP.POST

{
	"instanceId" : "OpenStack12345V",
	"instanceURL" : "http://localhost:7001/connect",
	"createdDate" : "2015-07-07 10:00:00",
	"status" : "Create",
	"instanceType" : "WordPress",
	"user" : { "id":1 }
	
}

{
	"instanceId" : "12345V23",
	"instanceURL" : "http://localhost:7001/connect",
	"createdDate" : "2015-07-07 10:00:00",
	"status" : "Create",
	"instanceType" : "Cirros",
     "user" : {"id":1}
	
}

Get User Specific Instances
http://localhost:8080/instances/ravi.katta@oracle.com
[{"id":14,"instanceId":"OpenStack1234","instanceURL":"http://localhost:7001/connect","createdDate":"2015-07-07 10:00:00","terminatedDate":null,"instanceType":"WordPress","status":"Create"},{"id":15,"instanceId":"OpenStack57896","instanceURL":"http://localhost:7001/connect","createdDate":"2015-07-07 10:00:00","terminatedDate":null,"instanceType":"WordPress","status":"Create"}]


Update Instance

http://localhost:8080/instances/{id}
HTTP.PUT
http://localhost:8080/instances/11

Update Status

{
	"instanceId" : "OpenStack12345V",
	"instanceURL" : "http://localhost:7001/connect",
	"createdDate" : "2015-07-07 10:00:00",
	"status" : "Paused",
	"instanceType" : "WordPress"
	
}

Update Terminated date
{
	"instanceId" : "OpenStack12345V",
	"instanceURL" : "http://localhost:7001/connect",
	"createdDate" : "2015-07-07 10:00:00",
    "terminatedDate" : "2015-07-07 12:00:00",
	"status" : "Terminated",
	"instanceType" : "WordPress"
	
}