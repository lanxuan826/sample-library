CREATE TABLE `t_order202001` (
  `order_id` bigint(20) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `period` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `t_order202002` (
  `order_id` bigint(20) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `period` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

