# API for notes sharing apps

## API provides the following endpoints:


### POST /api/v1/notes

Note creation endpoint

Request body example:

{
"text" : "this is my awesome note",
"expiredAt" : " 2023-12-03T10:15:30",
"password": "abc"
}

text - mandatory field
expiredAt - optional
password - optional

Response example

{
"id": "4afcab3e-af9c-4136-9ff9-727463ea639b",
"text": "this is my awesome note"
}

### GET /api/v1/notes/<note_id>

Get note endpoint, secured with basic authentication

**Important note!** Request should contain basic authorization header, e.g. "Authorization: Basic YjM3NmUwMTctZDMwZC00NjA1LThiNDUtYWI3NWYyNzQ1YjkxOmFiYw==". Use note id as a username and password set for the note as a password, e.g. 4afcab3e-af9c-4136-9ff9-727463ea639b:abc for the example above. Then encode it using any tool to base64.

Response example

{
"text": "this is my awesome note"
}

# Local development

Requirements: 
Java 11+
Mysql 8+

1. Clone the repo from github: https://github.com/blestka/notes-api.git
2. Setup mysql database with password "root" for root user locally. Apply sql/V1__notes-table.sql to create and setup database.
3. Run "./gradlew clean build bootRun" from project root. It should start notes-api app at port 8080: http://localhost:8080/api/v1/notes.

# Docker
Requirements: docker, docker-compose

1. Run "./gradlew clean build" from project root to build jar executable.
2. Run "docker-compose up" to spin up containers. App will be available at port 8080: http://localhost:8080/api/v1/notes.






