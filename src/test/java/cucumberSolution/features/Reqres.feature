Feature:
  Reqres requests

Scenario: Get user by id
  When I perform GET operation for "/api/users/{id}"
  Then Assert status code is 200