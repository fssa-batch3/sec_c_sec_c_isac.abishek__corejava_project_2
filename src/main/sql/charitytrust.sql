use charitytrust;

CREATE TABLE `events` (
  `event_id` int NOT NULL AUTO_INCREMENT,
  `event_name` varchar(25) NOT NULL,
  `event_location` varchar(100) NOT NULL,
  `organizer_name` varchar(45) NOT NULL,
  `contact_number` varchar(12) NOT NULL,
  `event_date` timestamp NOT NULL,
  `image_url` varchar(45) NOT NULL,
  `about_event` varchar(200) NOT NULL,
  created_at timestamp default current_timestamp,
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `event_name` (`event_name`),
  constraint event_date_chk check (event_date >= created_at)
);
use charitytrust;
SELECT * FROM charitytrust.events;

  
INSERT INTO `events` (`event_name`, `event_location`, `organizer_name`, `contact_number`, `event_date`, `image_url`, `about_event`)
VALUES
  ('Besantdonation', 'Convention Center, chennai', 'FreshTrust', '9751328805', '2023-12-15 10:00:00', 'https://iili.io/J90GGkb.webp', ' Donation and service for differently-abled .'),
  ('VRMall', 'City Park Stadium', 'King Sports Club', '9751328805', '2023-12-05 14:30:00', '  https://iili.io/J90jwCv.jpg', 'Donation and service for differently-abled .'),
  ('JPR', 'Solinganallur Chennai', 'Helping Hands Foundation', '9751328805', '2024-01-08 13:00:00', 'https://iili.io/J90Gs4t.jpg', 'Donation aids to support differently-abled .');

  
CREATE TABLE `products` (
  `Product_id` int NOT NULL AUTO_INCREMENT,
  `Product_name` varchar(50) NOT NULL,
  `Product_description` varchar(100) NOT NULL,
  `Product_registerd_date` datetime NOT NULL,
  `image_url` varchar(45) NOT NULL,
  `event_id` int NOT NULL,
  PRIMARY KEY (`Product_id`),
  UNIQUE KEY `Product_name` (`Product_name`,`event_id`)
);
INSERT INTO `products` (`Product_name`, `Product_description`, `Product_registerd_date`, `image_url`, `event_id`)
VALUES
  ('Wheelchair', 'Manual wheelchair for differently-abled', '2023-09-07 10:00:00', 'https://iili.io/HNRIvFn.jpg', 1),
  ('Hearing Aid', 'ISI Mark hearing-impaired individuals', '2023-09-07 11:30:00', 'https://iili.io/HNRzyBt.jpg', 2),
  ('Braille Book', 'ISI Mark visually impaired individuals', '2023-09-07 12:45:00', 'https://iili.io/J90aiEg.webp', 3),
  ('Prosthetic Leg', 'Advanced prosthetic leg for amputees', '2023-09-07 14:15:00', 'https://iili.io/HNRzs2a.jpg', 1);
SELECT * FROM charitytrust.products;
CREATE TABLE `requests` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `event_name` varchar(50) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `request_registerd_date` datetime NOT NULL,
  `contact_number` bigint(45) NOT NULL,
  UNIQUE KEY `contact_number` (`contact_number`),
  PRIMARY KEY (`request_id`),
  is_active BOOLEAN not null default true
);
select * from requests;










DROP table requests;