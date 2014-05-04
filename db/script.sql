CREATE TABLE `resumes` (
  `id` int(11) NOT NULL auto_increment,
  `resume_id` varchar(60) default NULL,
  `resume_name` varchar(45) default NULL,
  `resume_download_link` varchar(250) default NULL,
  `resume_link` varchar(250) default NULL,
  `bachelor_school` int(11) default NULL,
  `master_school` int(11) default NULL,
  `doctor_school` int(11) default NULL,
  `working_exp` varchar(2000) default NULL,
  `city` varchar(45) default NULL,
  `state` varchar(45) default NULL,
  `country` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) 

