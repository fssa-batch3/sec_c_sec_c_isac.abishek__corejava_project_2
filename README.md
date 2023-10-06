# sec_c_sec_c_isac.abishek__corejava_project_2
# MILESTONE 1
###  ** EVENT MODULE**
- Event Model
- Event Validator
- Event Dao
- Event ServiceLayer
- Event Test cases

 ###  ** EVENT MODULE FEATURES**
-Add event
-Read all event
-Filter Specific event by name
-update event
-Delete Event
# DATABASE_DESIGN
### EventList Table

### Table: events

| Column Name     | Data Type    | Constraints                             | Description                              |
|-----------------|--------------|-----------------------------------------|------------------------------------------|
| event_id        | int          | NOT NULL, AUTO_INCREMENT, PRIMARY KEY  | Unique identifier for each event.        |
| event_name      | varchar(25)  | NOT NULL, UNIQUE                        | Name of the event.                       |
| event_location  | varchar(100) | NOT NULL                                | Location where the event is held.       |
| organizer_name  | varchar(45)  | NOT NULL                                | Name of the event organizer.             |
| contact_number  | varchar(12)  | NOT NULL                                | Contact number for the event.           |
| event_date      | timestamp    | NOT NULL, CHECK (event_date >= created_at) | Date and time of the event.          |
| image_url       | varchar(45)  | NOT NULL                                | URL of the event's image.                |
| about_event     | varchar(200) | NOT NULL                                | Brief description about the event.       |
| created_at      | timestamp    | DEFAULT current_timestamp                | Timestamp indicating when the event was created. |




###  ** PRODUCT MODULE**
- Product Model
- Product Validator
- Product Dao
- Product ServiceLayer
- Product Test cases


 ###  ** Product MODULE FEATURES**
-Add Product
-Read all Product
-Filter Specific Product by name
- list product by specifc events
-update Product
-Delete Product


### Table: products

| Column Name            | Data Type          | Constraints                      | Description                               |
|------------------------|--------------------|----------------------------------|-------------------------------------------|
| Product_id             | int                | NOT NULL, AUTO_INCREMENT, PRIMARY KEY | Unique identifier for each product.       |
| Product_name           | varchar(50)        | NOT NULL, UNIQUE                | Name of the product.                      |
| Product_description    | varchar(100)       | NOT NULL                         | Description of the product.              |
| Product_registerd_date | datetime           | NOT NULL                         | Date and time when the product was registered. |
| image_url              | varchar(45)        | NOT NULL                         | URL of the product's image.               |
| event_id               | int                | NOT NULL                         | Identifier of the event associated with the product. |




## request
### Table: requests
###  ** request MODULE**
- Productrequest Model
- Productrequest Validator
- Productrequest Dao
- Productrequest ServiceLayer
- Productrequest Test cases
- 

 ###  ** request MODULE FEATURES**
- Add Productrequest
- Read all Productrequest


| Column Name            | Data Type          | Constraints                      | Description                      |
|------------------------|--------------------|----------------------------------|----------------------------------|
| request_id             | int                | NOT NULL, AUTO_INCREMENT, PRIMARY KEY | Unique identifier for each request. |
| event_name             | varchar(50)        | NOT NULL                         | Name of the event associated with the request. |
| product_name           | varchar(100)       | NOT NULL                         | Name of the product associated with the request. |
| request_registerd_date | datetime           | NOT NULL                         | Date and time when the request was registered. |
| contact_number         | bigint(45)         | NOT NULL, UNIQUE                | Contact number associated with the request. |
| is_active              | BOOLEAN            | NOT NULL, default true          | Indicates whether the request is active or not. |


###  ** USER MODULE**
- User Model
- User Validator
- User Dao
- User ServiceLayer
- User Test cases


 ###  ** User MODULE FEATURES**
-Add User
-Read specific User
-User Product
-User Product


### Table: User

| Column Name | Data Type          | Constraints                   | Description                          |
|-------------|--------------------|-------------------------------|--------------------------------------|
| id          | int                | NOT NULL, AUTO_INCREMENT      | Unique identifier for each user.    |
| Username    | varchar(50)        | NOT NULL                      | User's username.                     |
| Address     | varchar(100)       | NOT NULL                      | User's address.                      |
| Contact     | varchar(45)        | NOT NULL                      | User's contact information.          |
| Password    | varchar(200)       | NOT NULL                      | User's password (hashed and secured).|
| Email       | varchar(45)        | NOT NULL, UNIQUE              | User's email address (unique).       |
| Role        | varchar(45)        | NOT NULL                      | User's role or role identifier.      |
| is_Active   | boolean            | DEFAULT true                  | Indicates if the user account is active or not (default is active). |
| accessblity | boolean            | DEFAULT true                  | Indicates user's accessibility (default is accessible). |



