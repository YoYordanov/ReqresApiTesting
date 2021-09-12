Feature:
  Reqres.in requests

Scenario: Get user by id
  When Get user with id "1" by calling "/api/users"
  Then Assert status code is 200

Scenario: Create user successfully
  When Create user with name "TestUser" and job "TestEngineer" by calling "/api/users"
  Then Assert status code is 201
  And Assert "TestUser" and "TestEngineer" are returned in body of the response
  When Hit "api/users" with the returned userid
  Then Assert status code is 200

Scenario: Register user successfully
  When Register user with email "TestUser@email.com" and password "password" by calling "/api/register"
  Then Assert status code is 200
  And Assert "id" and "token" fields are not null

Scenario: Register user unsuccessfully
  When Register user with email "error@email.com" and no password by calling "/api/register"
  Then Assert status code is 400
  And Assert "error" "Missing password" is returned

Scenario: Login successfully
  When Login with email "eve.holt@reqres.in" and password "cityslicka" by calling "/api/login"
  Then Assert "token" "QpwL5tke4Pnpja7X4" is returned

Scenario: Login unsuccessfully
  When Login with email "eve.holt@reqres.in" and no password by calling "/api/login"
  Then Assert status code is 400
  And Assert "error" "Missing password" is returned

Scenario: Update user
  When Update userId "2" with name "updatedName" and job "updatedJob" by calling "/api/users"
  Then Assert status code is 200
  And Assert "name" "updatedName" is returned
  And Assert "job" "updatedJob" is returned

Scenario: Get list of users
  When Get page "2" by calling "/api/users"
  Then Assert status code is 200
  And Assert "page" "2" is returned
  And Assert more than 1 id is returned

Scenario: Delete user
  When Delete user with id "2" by calling "/api/users"
  Then Assert status code is 204
  When Get user with id "2" by calling "/api/users"
  Then Assert status code is 404

Scenario: Validate response time
  When Get list of user with delay "3" seconds by calling "/api/users"
  Then Assert status code is 204
  And Response time is slower than 2 seconds