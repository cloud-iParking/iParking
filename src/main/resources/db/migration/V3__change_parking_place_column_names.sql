ALTER TABLE `iparking`.`parking_place`
    CHANGE COLUMN `availableFrom` `available_from` DATETIME NOT NULL ,
    CHANGE COLUMN `availableUntil` `available_until` DATETIME NOT NULL ,
    CHANGE COLUMN `isFree` `is_free` TINYINT(1) NOT NULL ;