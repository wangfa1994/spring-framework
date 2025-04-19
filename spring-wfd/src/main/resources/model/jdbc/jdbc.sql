

CREATE TABLE `product_info` (
                                `id` bigint(20) NOT NULL,
                                `product_name` varchar(50) DEFAULT NULL,
                                `product_price` decimal(10,2) DEFAULT NULL,
                                `stock_count` int(11) DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `order_info` (
                              `id` bigint(20) NOT NULL,
                              `order_no` varchar(50) DEFAULT '',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `stu` (
                       `id` int(11) NOT NULL,
                       `stuname` varchar(255) DEFAULT NULL,
                       `address` char(10) DEFAULT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `springtran`.`stu` (`id`, `stuname`, `address`) VALUES (1, 'zhangsan', 'shanghai');
INSERT INTO `springtran`.`stu` (`id`, `stuname`, `address`) VALUES (2, 'lisi', '中国');
