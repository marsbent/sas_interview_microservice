# sas_interview_microservice

The microservice supports CRUD for users.  All users are stored in memory in a HashMap of strings in UserService::userData Each user has the following format / data:
```
{
  "userId": "123",
  "name": "John"
}
```

**Root URL: /users**

**Supported Methods**
* GET /users
    * Get a list of all users
* POST /users
    * Create a new user
* GET /users/{userId}
    * Get the individual user with {userId}
* PUT /users/{userId}
    * Update the user with {userId}
* DELETE /users/{userId}
    * Delete the user with {userId}
