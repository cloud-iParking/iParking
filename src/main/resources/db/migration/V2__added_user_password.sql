ALTER TABLE `iparking`.`user`
ADD COLUMN `password` VARCHAR(45) NOT NULL,
ADD UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE;
