-- 商家表
CREATE TABLE `tb_merchant` (
                               `id` BIGINT AUTO_INCREMENT COMMENT '商家ID',
                               `name` VARCHAR(100) NOT NULL COMMENT '商家名称',
                               `code` VARCHAR(32) COMMENT '商家编码',
                               `contact_person` VARCHAR(50) COMMENT '联系人',
                               `contact_phone` VARCHAR(20) COMMENT '联系电话',
                               `email` VARCHAR(100) COMMENT '邮箱',
                               `address` VARCHAR(255) COMMENT '地址',
                               `business_license` VARCHAR(255) COMMENT '营业执照',
                               `category` VARCHAR(200) COMMENT '经营类目',
                               `profit_ratio` INT DEFAULT 0 COMMENT '分成比例(%)',
                               `account` VARCHAR(64) COMMENT '登录账号',
                               `password` VARCHAR(100) COMMENT '登录密码',
                               `status` TINYINT DEFAULT 1 COMMENT '状态：0禁用，1启用',
                               `level` INT DEFAULT 1 COMMENT '商家等级',
                               `score` INT DEFAULT 100 COMMENT '评分',
                               `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `create_by` VARCHAR(64) COMMENT '创建人',
                               `update_by` VARCHAR(64) COMMENT '更新人',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商家表';

-- 商家结算表
CREATE TABLE `tb_merchant_settlement` (
                                          `id` BIGINT AUTO_INCREMENT COMMENT '结算ID',
                                          `merchant_id` BIGINT NOT NULL COMMENT '商家ID',
                                          `settlement_no` VARCHAR(32) NOT NULL COMMENT '结算单号',
                                          `settlement_start` DATE NOT NULL COMMENT '结算开始日期',
                                          `settlement_end` DATE NOT NULL COMMENT '结算结束日期',
                                          `order_count` INT DEFAULT 0 COMMENT '订单数量',
                                          `total_amount` DECIMAL(12,2) DEFAULT 0 COMMENT '结算总金额',
                                          `platform_amount` DECIMAL(12,2) DEFAULT 0 COMMENT '平台分成',
                                          `merchant_amount` DECIMAL(12,2) DEFAULT 0 COMMENT '商家分成',
                                          `status` TINYINT DEFAULT 0 COMMENT '状态：0待结算，1已结算',
                                          `settlement_time` DATETIME COMMENT '结算时间',
                                          `remark` VARCHAR(512) COMMENT '备注',
                                          `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                          PRIMARY KEY (`id`),
                                          UNIQUE KEY `uk_settlement_no` (`settlement_no`),
                                          KEY `idx_merchant_id` (`merchant_id`),
                                          KEY `idx_settlement_time` (`settlement_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商家结算表';

INSERT INTO tb_merchant (name, code, contact_person, contact_phone, email, address, business_license, category, profit_ratio, account, password, status, level, score, create_time, update_time, create_by, update_by)
VALUES
    ('苹果官方旗舰店', 'MERCHANT001', '张经理', '13900001111', 'apple@example.com', '北京市朝阳区三里屯', 'https://example.com/licenses/apple.jpg', '电子产品', 15, 'apple_store', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', 1, 3, 98, NOW(), NOW(), 'admin', 'admin'),
    ('三星官方旗舰店', 'MERCHANT002', '李经理', '13900002222', 'samsung@example.com', '上海市浦东新区陆家嘴', 'https://example.com/licenses/samsung.jpg', '电子产品', 15, 'samsung_store', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', 1, 3, 96, NOW(), NOW(), 'admin', 'admin'),
    ('耐克官方旗舰店', 'MERCHANT003', '王经理', '13900003333', 'nike@example.com', '广州市天河区天河路', 'https://example.com/licenses/nike.jpg', '服装鞋帽', 20, 'nike_store', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', 1, 2, 95, NOW(), NOW(), 'admin', 'admin');

INSERT INTO tb_merchant_settlement (merchant_id, settlement_no, settlement_start, settlement_end, order_count, total_amount, platform_amount, merchant_amount, status, settlement_time, remark, create_time, update_time)
VALUES
    (1, 'SETTLE202304-M001', '2023-04-01', '2023-04-30', 120, 500000.00, 75000.00, 425000.00, 1, NOW(), '4月份结算完成', NOW(), NOW()),
    (2, 'SETTLE202304-M002', '2023-04-01', '2023-04-30', 80, 300000.00, 45000.00, 255000.00, 1, NOW(), '4月份结算完成', NOW(), NOW()),
    (3, 'SETTLE202304-M003', '2023-04-01', '2023-04-30', 150, 100000.00, 20000.00, 80000.00, 1, NOW(), '4月份结算完成', NOW(), NOW()),
    (1, 'SETTLE202305-M001', '2023-05-01', '2023-05-31', 130, 520000.00, 78000.00, 442000.00, 0, NULL, '5月份待结算', NOW(), NOW());