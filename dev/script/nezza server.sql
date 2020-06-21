create database db_neeza;
use db_neeza;

CREATE TABLE `api_mock_data` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT COMMENT '主键',
  `app_name` VARCHAR(1024) NOT NULL COMMENT 'app name',
  `method_type` int(4) NOT NULL COMMENT 'method_type: 1 ReturnBody 2 VoidReturn 3 ReturnNull',
  `api_name` MEDIUMTEXT NOT NULL COMMENT 'api',
  `api_data` MEDIUMTEXT NULL COMMENT 'mock data',
  PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8mb4 COMMENT = 'api mock data';