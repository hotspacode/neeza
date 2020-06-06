create database db_neeza;
use db_neeza;

CREATE TABLE `api_mock_data` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT COMMENT '主键',
  `app_id` INT (11) NOT NULL COMMENT 'app id',
  `api_name` MEDIUMTEXT NOT NULL COMMENT 'api',
  `api_data` MEDIUMTEXT NULL COMMENT 'mock data',
  PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8mb4 COMMENT = 'api mock data';