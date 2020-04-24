

DROP TABLE IF EXISTS `person_data_t`;
CREATE TABLE person_data_t as
select
 0 gaTotal
;

DROP TABLE IF EXISTS `user_data_t`;
CREATE TABLE `user_data_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
INSERT INTO  `user_data_t`( `name`) VALUES ( 'wjs-schema-user');


CREATE TABLE IF NOT EXISTS `user_data_2_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO  `user_data_2_t`( `name`) VALUES ( 'wjs-schema-user');
