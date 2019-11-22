# notesapp

This is a Spring Boot Maven Application.

Initial Commit: Spring Initializr project using from https://start.spring.io/

# user api (was not a mandate though)

Add User in the system
----------------------
curl -X POST http://localhost:8080/users -H 'cache-control: no-cache' -H 'content-type:application/json' -d '{"email": "test1","password": "test1"}'

Fetch Users in the System
-------------------------
curl -X GET \  http://localhost:8080/users

# notes api 

Add a note against a user
-------------------------
curl -X POST \
  http://localhost:8080/user/1/note \
  -H 'authorization: Basic dGVzdDE6dGVzdDE=' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
"note": "note for user 1",
"title": "title for user 1"
}'

Get all the notes against a user
--------------------------------
curl -X GET \
  http://localhost:8080/user/1/note/ \
  -H 'authorization: Basic dGVzdDE6dGVzdDE=' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'

Get a particular note against a user
------------------------------------
curl -X GET \
  http://localhost:8080/user/1/note/1 \
  -H 'authorization: Basic dGVzdDE6dGVzdDE=' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'

Update a Note for a user
------------------------
curl -X POST \
  http://localhost:8080/user/1/note/1 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
"note": "trying to update the note",
"title": "trying to update the note"
}'

Delete a note for a user
------------------------
curl -X DELETE \
  http://localhost:8080/user/2/note/1 \
  -H 'authorization: Basic dGVzdDI6dGVzdA==' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'

# Security

Users API is not secured. In production application it should not be this way though.
----------------
Notes API is secured.
----------------

~~~
http
	  .csrf().disable().headers().frameOptions().sameOrigin().and()
	  .authorizeRequests()
      .antMatchers("/users/**").permitAll()
      .anyRequest().authenticated()
      .and()
      .httpBasic();
~~~

UserDetailsServiceImpl
------------
   
   It pulls details from User Table to verify the credentials.

# Important Classes

1. NoteController

2. UserController

# Database
## Currently applciation is configured to use H2 in memory database
## To point the applcation to a MySql Database
      ### Add: my sql connector dependecny in pom
      
      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
      </dependency>
      
      ### Add: add properties in application.properties
      spring.datasource.url=jdbc:mysql://localhost:3306/restapi
      spring.datasource.username=root
      spring.datasource.password=

# Improvement

Adding of Junit is a mandate but leaving it as such as of now cosnidering the time duration expected by this task is over.
Adding negative scenarios handling
Adding Javadoc
