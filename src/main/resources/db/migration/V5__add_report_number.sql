alter table `iparking`.`user`
ADD COLUMN `report_number` INT NOT NULL DEFAULT 0 AFTER `password`;

alter table `iparking`.`report`
CHANGE COLUMN `id` `id` BIGINT NOT NULL AUTO_INCREMENT ;