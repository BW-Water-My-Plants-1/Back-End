# Back-End  

## API: http://watermyplants-dg0511.herokuapp.com/  

### Table of Contents  
[Register and Login](#register-and-login)  
[User](#user)  
[Plant](#plant)  
[Role](#role)

## REGISTER AND LOGIN  
#### The login axios request should look like

```
axios.post('http://watermyplants-dg0511.herokuapp.com/login', `grant_type=password&username=${credentials.username}&password=${credentials.password}`, {
      headers: {
        // btoa is converting our client id/client secret into base64
        Authorization: `Basic ${btoa('XXXXXXX:XXXXXXXX')}`,
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    })
```

#### When registering a user the minimum required is
```
{
    "username": "someusername",
    "password": "somepassword",
    "email": "someEmail@email.local"
}
```
| Type  | Endpoint  | What it does                   | required                                  |
| :--:  | :-------: | :----------------------------: | :---------------------------------------: |
| POST  | /register | register user and return token | **username**, **email**, and **password** |
| POST  | /login    | logs in user and returns token | **username** and **password**             |
| GET   | /logout   | removes user token from store  |                                           |

## USER  
In order to change any user information the request must come from an admin or  
the corresponding user to the id provided in the endpoint.  

#### The user object is of a structure
```
{
    "userid": 3,
    "username": "admin",
    "password": "somepassword",
    "email": "admin@watermyplants.local",
    "phone": "(317)867-5309",
    "plants": [],
    "roles": []
}
```

| Type  | Endpoint          | What it does                                              | required                                  |
| :--:  | :----------:      | :----------------------------:                            | :---------------------------------------: |
| GET   | /users/users      | Returns full list of users                                | Token and Admin role                      |
| GET   | /users/user/{id}  | Returns specific user by id                               | Token and Admin role                      |
| GET   | /users/myinfo     | Returns current user's object                             | Token                                     |
| POST  | /users/user       | Adds new user to database and returns status of CREATED   | Token and Admin role                      |
| PUT   | /users/user/{id}  | Replaces entire user by id and returns status of CREATED  | Token and User object                     |
| PATCH | /users/user/{id}  | Replaces part of user object and returns status of CREATED| Token and User object                     |
|DELETE | /users/user/{id}  | Deletes user by id and returns status of OK                | Token                                     |

## PLANT  
In order to change any plant information the request must come from an admin or  
the corresponding user to the plant id provided in the endpoint.  

#### The plant object is of a structure
```
{
    "plantid": 4,
    "nickname": "Habanero",
    "species": "Pepper",
    "frequency": 2,
    "user": {}
}
```
| Type  | Endpoint          | What it does                                               | required                                  |
| :--:  | :----------:      | :----------------------------:                             | :---------------------------------------: |
| GET   | /plants/plants    | Returns full list of plants                                | Token and Admin role                      |
| GET   |/plants/plant/{id} | Returns specific plant by id                               | Token and Admin role                      |
| GET   | /plants/myplants  | Returns current user's plants                              | Token                                     |
| POST  | /plants/plant     | Adds new plant to database and returns status of CREATED   | Token and plant object                    |
| PUT   |/plants/plant/{id} | Replaces entire plant by id and returns status of CREATED  | Token and plant object                    |
| PATCH |/plants/plant/{id} | Replaces part of plant object and returns status of CREATED| Token and plant object                    |
|DELETE |/plants/plant/{id} | Deletes plant by id and returns status of OK               | Token                                     |

## ROLE

#### The role object is of a structure
```
{
    "roleid": 1,
    "name": "admin",
    "users": []
}
```
| Type  | Endpoint          | What it does                                            | required                                  |
| :--:  | :----------:      | :----------------------------:                          | :---------------------------------------: |
| GET   | /roles/roles      | Returns full list of roles                              | Token and Admin role                      |
| GET   | /roles/role/{id}  | Returns specific role by id                             | Token and Admin role                      |
| POST  | /roles/role       | Adds new role to database and returns status of CREATED | Token and role object                     |
| PATCH | /roles/role/{id}  | Replaces entire role by id and returns status of CREATED| Token and role object                     |
|DELETE | /roles/role/{id}  | Deletes role by id and returns status of OK             | Token                                     |
