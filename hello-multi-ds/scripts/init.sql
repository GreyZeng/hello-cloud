/**multidata-source-spring-cloud-alibaba**/
CREATE DATABASE IF NOT EXISTS `spring_cloud_alibaba_practice_master`;
CREATE DATABASE IF NOT EXISTS `spring_cloud_alibaba_practice_slave1`;
CREATE DATABASE IF NOT EXISTS `spring_cloud_alibaba_practice_slave2`;

use `spring_cloud_alibaba_practice_master`;
DROP TABLE IF EXISTS `example2_product`;
CREATE TABLE `example2_product` (
                                    `id` bigint NOT NULL,
                                    `good_id` bigint NOT NULL DEFAULT '0',
                                    `num` bigint DEFAULT '0',
                                    `good_name` varchar(20) NOT NULL,
                                    `version` int NOT NULL DEFAULT '0',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='第三章商品信息表';

LOCK TABLES `example2_product` WRITE;
INSERT INTO `example2_product` VALUES (7823734,5678,100,'master库测试商品',0);
UNLOCK TABLES;
commit;

use `spring_cloud_alibaba_practice_slave1`;
DROP TABLE IF EXISTS `example2_product`;
CREATE TABLE `example2_product` (
                                    `id` bigint NOT NULL,
                                    `good_id` bigint NOT NULL DEFAULT '0',
                                    `num` bigint DEFAULT '0',
                                    `good_name` varchar(20) NOT NULL,
                                    `version` int NOT NULL DEFAULT '0',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='第三章商品信息表';

LOCK TABLES `example2_product` WRITE;

INSERT INTO `example2_product` VALUES (3457676,5678,100,'slave1库测试商品',0);
UNLOCK TABLES;
commit;

use `spring_cloud_alibaba_practice_slave2`;
DROP TABLE IF EXISTS `example2_product`;
CREATE TABLE `example2_product` (
                                    `id` bigint NOT NULL,
                                    `good_id` bigint NOT NULL DEFAULT '0',
                                    `num` bigint DEFAULT '0',
                                    `good_name` varchar(20) NOT NULL,
                                    `version` int NOT NULL DEFAULT '0',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='第三章商品信息表';

LOCK TABLES `example2_product` WRITE;
INSERT INTO `example2_product` VALUES (2334343434,5678,100,'slave2测试商品',0);
UNLOCK TABLES;
commit;
/**multidata-source-spring-cloud-alibaba**/