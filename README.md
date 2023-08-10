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

| Field          | Type        | Null  | Key | Default           | Extra          |
|----------------|-------------|-------|-----|-------------------|----------------|
| event_id       | int         | NO    | PRI | NULL              | auto_increment |
| event_name     | varchar(50) | NO    | UNI | NULL              |                |
| event_location | varchar(45) | NO    |     | NULL              |                |
| organizer_name | varchar(45) | NO    |     | NULL              |                |
| contact_number | varchar(45) | NO    |     | NULL              |                |
| event_date     | datetime    | NO    |     | CURRENT_TIMESTAMP |                |
| image_url      | varchar(45) | NO    |     | NULL              |                |
| about_event    | varchar(45) | NO    |     | NULL              |                |


###  ** PRODUCT MODULE**
- Product Model
- Product Validator
- Product Dao
- Product ServiceLayer
- Product Test cases
- 

 ###  ** Product MODULE FEATURES**
-Add Product
-Read all Product
-Filter Specific Product by name
- list product by specifc events
-update Product
-Delete Product


### ProductList Table

| Field                   | Type        | Null  | Key | Default           | Extra          |
|-------------------------|-------------|-------|-----|-------------------|----------------|
| Product_id              | int         | NO    | PRI | NULL              | auto_increment |
| Product_name            | varchar(50) | NO    | UNI | NULL              |                |
| Product_description     | varchar(45) | NO    |     | NULL              |                |
| Product_registered_date | datetime    | NO    |     | CURRENT_TIMESTAMP |                |
| image_url               | varchar(45) | NO    |     | NULL              |                |
| event_id                | int         | NO    |     | NULL              |                |
