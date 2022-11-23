# API for notes sharing apps

## API provides the following endpoints:


### POST /api/v1/notes - note creation endpoint

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

### GET /api/v1/notes/<note_id> - get note endpoint, secured with basic authentication

Request should contain basic authorization header, e.g. "Authorization: Basic YjM3NmUwMTctZDMwZC00NjA1LThiNDUtYWI3NWYyNzQ1YjkxOmFiYw==
"

Response example

{
"text": "this is my awesome note"
}


