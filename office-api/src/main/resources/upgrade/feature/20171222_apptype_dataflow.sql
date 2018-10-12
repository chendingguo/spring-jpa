-- 审核应用表
CREATE TABLE `audit_app_history` (
  `id` bigint(20) NOT NULL,
  `app` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  `audit_status` bit(1) DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL,
  `meta` bit(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_user` (`app`,`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 审核应用标签表
CREATE TABLE `audit_app_tag` (
  `id` bigint(20) NOT NULL,
  `tag_id` varchar(255) NOT NULL,
  `tag_level` int(11) DEFAULT NULL,
  `tag_name` varchar(255) DEFAULT NULL,
  `parent_tag_id` varchar(255) DEFAULT NULL,
  `parent_tag_name` varchar(255) DEFAULT NULL,
  `app` bigint(20) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `system_setting` (
  `conf_key` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`conf_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- dmp帐号配置
insert into system_setting values ('DMP_USER', 'xxxx@reyun.com');
