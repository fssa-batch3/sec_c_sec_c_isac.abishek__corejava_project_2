# sec_c_sec_c_isac.abishek__corejava_project_2

# DATABASE_DESIGN
### ProductList Table

| Field                   | Type        | Null  | Key | Default           | Extra          |
|-------------------------|-------------|-------|-----|-------------------|----------------|
| Product_id              | int         | NO    | PRI | NULL              | auto_increment |
| Product_name            | varchar(50) | NO    | UNI | NULL              |                |
| Product_description     | varchar(45) | NO    |     | NULL              |                |
| Product_registered_date | datetime    | NO    |     | CURRENT_TIMESTAMP |                |
| image_url               | varchar(45) | NO    |     | NULL              |                |
| event_id                | int         | NO    |     | NULL              |                |


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
