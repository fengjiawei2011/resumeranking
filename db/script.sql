CREATE TABLE `resumerank`.`resume` (
  `resume_id` CHAR(13) NOT NULL,
  `resume_name` VARCHAR(45) NULL,
  `resume_download_link` VARCHAR(250) NULL,
  `resume_link` VARCHAR(250) NULL,
  `bachelor_school` INT NULL,
  `master_school` INT NULL,
  `doctor_school` INT NULL,
  `working_exp` VARCHAR(2000) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  PRIMARY KEY (`resume_id`));


