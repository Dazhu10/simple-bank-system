CREATE DATABASE banksys;
USE banksys;
CREATE TABLE `card_info` (
                             `account_num` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                             `password` varchar(255) NOT NULL,
                             `bal` decimal(26,4) NOT NULL,
                             `state` char(4) NOT NULL,
                             `make_user_name` varchar(255) NOT NULL,
                             `cert_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                             `mobile_no` varchar(30) NOT NULL,
                             `open_dt` date NOT NULL,
                             PRIMARY KEY (`account_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;