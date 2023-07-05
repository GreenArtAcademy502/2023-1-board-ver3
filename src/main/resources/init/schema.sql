drop table if exists `t_board_cmt`;
drop table if exists `t_board`;
drop table if exists `t_user`;

CREATE TABLE IF NOT EXISTS `t_user` (
  `iuser` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(20) NOT NULL,
  `upw` varchar(100) NOT NULL,
  `nm` varchar(30) NOT NULL,
  `gender` char(1) NOT NULL CHECK (`gender` in ('M','F')),
  `addr` varchar(100) NOT NULL,
  `main_pic` varchar(100) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`iuser`),
  UNIQUE KEY `uid` (`uid`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `t_board` (
  `iboard` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `ctnt` text NOT NULL,
  `iuser` bigint(20) unsigned DEFAULT NULL COMMENT '작성자',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`iboard`),
  KEY `iuser` (`iuser`),
  CONSTRAINT `t_board_ibfk_1` FOREIGN KEY (`iuser`) REFERENCES `t_user` (`iuser`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `t_board_cmt` (
  `iboard_cmt` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `iboard` bigint(20) unsigned NOT NULL COMMENT '글번호',
  `iuser` bigint(20) unsigned NOT NULL COMMENT '작성자',
  `ctnt` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`iboard_cmt`),
  KEY `iboard` (`iboard`),
  KEY `iuser` (`iuser`),
  CONSTRAINT `t_board_cmt_ibfk_1` FOREIGN KEY (`iboard`) REFERENCES `t_board` (`iboard`),
  CONSTRAINT `t_board_cmt_ibfk_2` FOREIGN KEY (`iuser`) REFERENCES `t_user` (`iuser`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;