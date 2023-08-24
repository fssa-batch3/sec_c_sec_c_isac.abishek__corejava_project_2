
CREATE TABLE `EventList` (
  `event_id` int NOT NULL auto_increment ,
  `event_name` varchar(50) NOT NULL unique,
  `event_location` varchar(45) NOT NULL,
  `organizer_name` varchar(45) NOT NULL,
  `contact_number` varchar(45) NOT NULL,
  `event_date` datetime NOT NULL,
  `image_url` varchar(45) NOT NULL,
  `about_event` varchar(45) NOT NULL,
  PRIMARY KEY (`event_id`)
) ;
select * from EventList ;



CREATE TABLE `ProductList` (
  `Product_id` int NOT NULL auto_increment ,
  `Product_name` varchar(50) NOT NULL unique,
  `Product_description` varchar(45) NOT NULL,
  `Product_registerd_date` datetime NOT NULL,
  `image_url` varchar(45) NOT NULL,
  `event_id` int not null,
  PRIMARY KEY (`Product_id`)
) ;
select * from ProductList ;