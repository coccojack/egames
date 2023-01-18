-- account_role

CREATE TABLE `account_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- customer

CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `birthdate` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `account_role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ACCOUNT_ROLE` (`account_role_id`),
  CONSTRAINT `FK_ACCOUNT_ROLE` FOREIGN KEY (`account_role_id`) REFERENCES `account_role` (`id`)
);

-- address

CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `country` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CUSTOMER_ADDRESS` (`customer_id`),
  CONSTRAINT `FK_CUSTOMER_ADDRESS` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
);

-- status

CREATE TABLE `status` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- purchase

CREATE TABLE `purchase` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `purchase_date` date DEFAULT NULL,
  `total` float DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PURCHASE_ADDRESS` (`address_id`),
  KEY `FK_CUSTOMER` (`customer_id`),
  KEY `FK_PURCHASE_STATUS` (`status_id`),
  CONSTRAINT `FK_CUSTOMER` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_PURCHASE_ADDRESS` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK_PURCHASE_STATUS` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
);

-- genre

CREATE TABLE `genre` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- theme

CREATE TABLE `theme` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- game_mode

CREATE TABLE `game_mode` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `internet_required` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- software_house

CREATE TABLE `software_house` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `country` varchar(255) DEFAULT NULL,
  `foundation_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- specialty

CREATE TABLE `specialty` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- developer

CREATE TABLE `developer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `birth_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `software_house_id` bigint DEFAULT NULL,
  `specialty_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_SOFTWARE_HOUSE_DEVELOPER` (`software_house_id`),
  KEY `FK_SPECIALTY_DEVELOPER` (`specialty_id`),
  CONSTRAINT `FK_SOFTWARE_HOUSE_DEVELOPER` FOREIGN KEY (`software_house_id`) REFERENCES `software_house` (`id`),
  CONSTRAINT `FK_SPECIALTY_DEVELOPER` FOREIGN KEY (`specialty_id`) REFERENCES `specialty` (`id`)
);

-- language

CREATE TABLE `language` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- producer

CREATE TABLE `producer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `country` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- platform

CREATE TABLE `platform` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpu` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `production_date` date DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `support` varchar(255) DEFAULT NULL,
  `producer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PRODUCER` (`producer_id`),
  CONSTRAINT `FK_PRODUCER` FOREIGN KEY (`producer_id`) REFERENCES `producer` (`id`)
);

-- input_type

CREATE TABLE `input_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- videogame

CREATE TABLE `videogame` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `adult_game` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` mediumblob,
  `ingame_purchases` bit(1) NOT NULL,
  `pegi` varchar(255) DEFAULT NULL,
  `player_num` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `stock_quantity` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `audio_dev_id` bigint DEFAULT NULL,
  `game_dev_id` bigint DEFAULT NULL,
  `genre_id` bigint DEFAULT NULL,
  `graph_dev_id` bigint DEFAULT NULL,
  `platform_id` bigint DEFAULT NULL,
  `software_house_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_AUDIO_DEV` (`audio_dev_id`),
  KEY `FK_GAME_DEV` (`game_dev_id`),
  KEY `FK_GENRE_VIDEOGAME` (`genre_id`),
  KEY `FK_GRAPH_DEV` (`graph_dev_id`),
  KEY `FK_PLATFORM_VIDEOGAME` (`platform_id`),
  KEY `FK_SOFTWARE_HOUSE_VIDEOGAME` (`software_house_id`),
  CONSTRAINT `FK_AUDIO_DEV` FOREIGN KEY (`audio_dev_id`) REFERENCES `developer` (`id`),
  CONSTRAINT `FK_GAME_DEV` FOREIGN KEY (`game_dev_id`) REFERENCES `developer` (`id`),
  CONSTRAINT `FK_GENRE_VIDEOGAME` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`),
  CONSTRAINT `FK_GRAPH_DEV` FOREIGN KEY (`graph_dev_id`) REFERENCES `developer` (`id`),
  CONSTRAINT `FK_PLATFORM_VIDEOGAME` FOREIGN KEY (`platform_id`) REFERENCES `platform` (`id`),
  CONSTRAINT `FK_SOFTWARE_HOUSE_VIDEOGAME` FOREIGN KEY (`software_house_id`) REFERENCES `software_house` (`id`)
);

-- purchase_videogame

CREATE TABLE `purchase_videogame` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `purchase_id` bigint DEFAULT NULL,
  `videogame_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PURCHASE_MTM` (`purchase_id`),
  KEY `FK_VIDEOGAME_MTM` (`videogame_id`),
  CONSTRAINT `FK_PURCHASE_MTM` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`id`),
  CONSTRAINT `FK_VIDEOGAME_MTM` FOREIGN KEY (`videogame_id`) REFERENCES `videogame` (`id`)
);

-- preferred_videogame

CREATE TABLE `preferred_videogame` (
  `customer_id` bigint NOT NULL,
  `videogame_id` bigint NOT NULL,
  PRIMARY KEY (`customer_id`,`videogame_id`),
  KEY `FK_VIDEOGAME_PREFERRED_MTM` (`videogame_id`),
  CONSTRAINT `FK_PREFERRED_VIDEOGAME_MTM` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_VIDEOGAME_PREFERRED_MTM` FOREIGN KEY (`videogame_id`) REFERENCES `videogame` (`id`)
);

-- platform_input_type

CREATE TABLE `platform_input_type` (
  `platform_id` bigint NOT NULL,
  `input_type_id` bigint NOT NULL,
  PRIMARY KEY (`platform_id`,`input_type_id`),
  KEY `FK_INPUT_TYPE_MTM` (`input_type_id`),
  CONSTRAINT `FK_INPUT_TYPE_MTM` FOREIGN KEY (`input_type_id`) REFERENCES `input_type` (`id`),
  CONSTRAINT `FK_PLATFORM_INPUT_TYPE_MTM` FOREIGN KEY (`platform_id`) REFERENCES `platform` (`id`)
);

-- videogame_theme

CREATE TABLE `videogame_theme` (
  `videogame_id` bigint NOT NULL,
  `theme_id` bigint NOT NULL,
  PRIMARY KEY (`videogame_id`,`theme_id`),
  KEY `FK_THEME_VG_MTM` (`theme_id`),
  CONSTRAINT `FK_THEME_VG_MTM` FOREIGN KEY (`theme_id`) REFERENCES `theme` (`id`),
  CONSTRAINT `FK_VG_THEME_MTM` FOREIGN KEY (`videogame_id`) REFERENCES `videogame` (`id`)
);

-- videogame_game_mode

CREATE TABLE `videogame_game_mode` (
  `videogame_id` bigint NOT NULL,
  `gamemode_id` bigint NOT NULL,
  PRIMARY KEY (`videogame_id`,`gamemode_id`),
  KEY `FK_GAMEMODE_VG_MTM` (`gamemode_id`),
  CONSTRAINT `FK_GAMEMODE_VG_MTM` FOREIGN KEY (`gamemode_id`) REFERENCES `game_mode` (`id`),
  CONSTRAINT `FK_VG_GAMEMODE_MTM` FOREIGN KEY (`videogame_id`) REFERENCES `videogame` (`id`)
);

-- videogame_language

CREATE TABLE `videogame_language` (
  `videogame_id` bigint NOT NULL,
  `language_id` bigint NOT NULL,
  PRIMARY KEY (`videogame_id`,`language_id`),
  KEY `FK_LAN_VG_MTM` (`language_id`),
  CONSTRAINT `FK_LAN_VG_MTM` FOREIGN KEY (`language_id`) REFERENCES `language` (`id`),
  CONSTRAINT `FK_VG_LAN_MTM` FOREIGN KEY (`videogame_id`) REFERENCES `videogame` (`id`)
);

-- videogame_input_type

CREATE TABLE `videogame_input_type` (
  `videogame_id` bigint NOT NULL,
  `input_type_id` bigint NOT NULL,
  PRIMARY KEY (`videogame_id`,`input_type_id`),
  KEY `FK_INPUTTYPE_VIDEOGAME_MTM` (`input_type_id`),
  CONSTRAINT `FK_INPUTTYPE_VIDEOGAME_MTM` FOREIGN KEY (`input_type_id`) REFERENCES `input_type` (`id`),
  CONSTRAINT `FK_VIDEOGAME_INPUTTYPE_MTM` FOREIGN KEY (`videogame_id`) REFERENCES `videogame` (`id`)
);

