Feature:
  Reqres.in requests

Scenario: Get user by id
  Given Setup base url and path
  When Get user with id "1" by calling "/users"
  Then Assert status code is 200

Scenario: Create user successfully
  Given Setup base url and path
  When Create user with name "TestUser" and job "TestEngineer" by calling "/users"
  Then Assert status code is 201

Scenario: Register user successfully
  Given Setup base url and path
  When Register user with email "TestUser@email.com" and password "password" by calling "/register"
  Then Assert status code is 200
  And Assert "id" and "token" fields are not null

Scenario: Register user unsuccessfully
  Given Setup base url and path
  When Register user with email "error@email.com" and no password by calling "/register"
  Then Assert status code is 400
  And Assert "error" "Missing password" is returned

Scenario: Login successfully
  Given Setup base url and path
  When Login with email "eve.holt@reqres.in" and password "cityslicka" by calling "/login"
  Then Assert "token" "QpwL5tke4Pnpja7X4" is returned

Scenario: Login unsuccessfully
  Given Setup base url and path
  When Login with email "eve.holt@reqres.in" and no password by calling "/login"
  Then Assert status code is 400
  And Assert "error" "Missing password" is returned

Scenario: Update user
  Given Setup base url and path
  When Update userId "2" with name "updatedName" and job "updatedJob" by calling "/users"
  Then Assert status code is 200
  And Assert "name" "updatedName" is returned
  And Assert "job" "updatedJob" is returned

Scenario: Get list of users
  Given Setup base url and path
  When Get page "2" by calling "/users"
  Then Assert status code is 200
  And Assert "page" "2" is returned
  And Assert more than 1 id is returned

Scenario: Delete user
  Given Setup base url and path
  When Delete user with id "2" by calling "/users"
  Then Assert status code is 204

Scenario: Validate response time
  Given Setup base url and path
  When Get list of user with delay "3" seconds by calling "/users"
  Then Assert status code is 200
  And Response time is slower than 2 seconds