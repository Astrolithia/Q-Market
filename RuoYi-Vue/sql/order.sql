-- 订单表
CREATE TABLE `tb_order` (
                            `id` BIGINT AUTO_INCREMENT COMMENT '订单ID',
                            `order_no` VARCHAR(32) NOT NULL COMMENT '订单编号',
                            `customer_id` BIGINT NOT NULL COMMENT '客户ID',
                            `total_amount` DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
                            `pay_amount` DECIMAL(10,2) NOT NULL COMMENT '实付金额',
                            `discount_amount` DECIMAL(10,2) DEFAULT 0 COMMENT '优惠金额',
                            `shipping_fee` DECIMAL(10,2) DEFAULT 0 COMMENT '运费',
                            `pay_type` TINYINT COMMENT '支付方式：1支付宝，2微信，3银行卡',
                            `pay_time` DATETIME COMMENT '支付时间',
                            `pay_no` VARCHAR(64) COMMENT '支付流水号',
                            `order_status` TINYINT DEFAULT 0 COMMENT '订单状态：0待付款，1待发货，2待收货，3已完成，4已取消，5退款中，6已退款',
                            `consignee` VARCHAR(50) NOT NULL COMMENT '收货人',
                            `mobile` VARCHAR(20) NOT NULL COMMENT '联系电话',
                            `address` VARCHAR(255) NOT NULL COMMENT '收货地址',
                            `message` VARCHAR(512) COMMENT '订单备注',
                            `merchant_id` BIGINT COMMENT '商家ID',
                            `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0未删除，1已删除',
                            `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_order_no` (`order_no`),
                            KEY `idx_customer_id` (`customer_id`),
                            KEY `idx_order_status` (`order_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 订单明细表
CREATE TABLE `tb_order_item` (
                                 `id` BIGINT AUTO_INCREMENT COMMENT '明细ID',
                                 `order_id` BIGINT NOT NULL COMMENT '订单ID',
                                 `order_no` VARCHAR(32) NOT NULL COMMENT '订单编号',
                                 `product_id` BIGINT NOT NULL COMMENT '商品ID',
                                 `sku_id` BIGINT NOT NULL COMMENT 'SKU ID',
                                 `product_name` VARCHAR(200) NOT NULL COMMENT '商品名称',
                                 `sku_spec` VARCHAR(200) COMMENT 'SKU规格',
                                 `product_image` VARCHAR(255) COMMENT '商品图片',
                                 `price` DECIMAL(10,2) NOT NULL COMMENT '原价',
                                 `actual_price` DECIMAL(10,2) NOT NULL COMMENT '实际价格',
                                 `quantity` INT NOT NULL COMMENT '数量',
                                 `total_amount` DECIMAL(10,2) NOT NULL COMMENT '总金额',
                                 `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 PRIMARY KEY (`id`),
                                 KEY `idx_order_id` (`order_id`),
                                 KEY `idx_order_no` (`order_no`),
                                 KEY `idx_product_id` (`product_id`),
                                 KEY `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';

-- 订单物流表
CREATE TABLE `tb_order_logistics` (
                                      `id` BIGINT AUTO_INCREMENT COMMENT '物流ID',
                                      `order_id` BIGINT NOT NULL COMMENT '订单ID',
                                      `order_no` VARCHAR(32) NOT NULL COMMENT '订单编号',
                                      `shipping_method` VARCHAR(50) COMMENT '配送方式',
                                      `logistics_company` VARCHAR(50) COMMENT '物流公司',
                                      `logistics_no` VARCHAR(50) COMMENT '物流单号',
                                      `shipping_time` DATETIME COMMENT '发货时间',
                                      `delivery_time` DATETIME COMMENT '送达时间',
                                      `logistics_status` TINYINT DEFAULT 0 COMMENT '物流状态：0待发货，1已发货，2已签收',
                                      `logistics_info` TEXT COMMENT '物流信息',
                                      `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      PRIMARY KEY (`id`),
                                      KEY `idx_order_id` (`order_id`),
                                      KEY `idx_order_no` (`order_no`),
                                      KEY `idx_logistics_no` (`logistics_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单物流表';

INSERT INTO tb_order (order_no, customer_id, total_amount, pay_amount, discount_amount, shipping_fee, pay_type, pay_time, pay_no, order_status, consignee, mobile, address, message, merchant_id, is_deleted, create_time, update_time)
VALUES
    ('2023052500001', 1, 6999.00, 6649.05, 349.95, 0.00, 2, NOW(), 'WX202305250000000001', 1, '张三', '13800138001', '北京市海淀区中关村大街1号', '请尽快发货', 1, 0, NOW(), NOW()),
    ('2023052500002', 2, 5999.00, 5699.05, 299.95, 0.00, 1, NOW(), 'ZFB202305250000000001', 2, '李四', '13800138002', '上海市浦东新区陆家嘴1号', '周末送货', 2, 0, NOW(), NOW()),
    ('2023052500003', 3, 1798.00, 1528.30, 269.70, 0.00, 2, NOW(), 'WX202305250000000002', 3, '王五', '13800138003', '广州市天河区天河路1号', NULL, 3, 0, NOW(), NOW());

INSERT INTO tb_order_item (order_id, order_no, product_id, sku_id, product_name, sku_spec, product_image, price, actual_price, quantity, total_amount, create_time)
VALUES
    (1, '2023052500001', 1, 1, 'iPhone 14 Pro', '{"颜色":"黑色","存储":"256GB"}', 'https://example.com/images/iphone14pro_black.jpg', 6999.00, 6649.05, 1, 6649.05, NOW()),
    (2, '2023052500002', 2, 3, 'Galaxy S23', '{"颜色":"黑色","存储":"256GB"}', 'https://example.com/images/s23_black.jpg', 5999.00, 5699.05, 1, 5699.05, NOW()),
    (3, '2023052500003', 3, 5, 'Nike Air Max 270', '{"颜色":"黑色","尺码":"42"}', 'https://example.com/images/airmax270_black_42.jpg', 899.00, 764.15, 2, 1528.30, NOW());

INSERT INTO tb_order_logistics (order_id, order_no, shipping_method, logistics_company, logistics_no, shipping_time, delivery_time, logistics_status, logistics_info, create_time, update_time)
VALUES
    (1, '2023052500001', '快递', '顺丰速运', 'SF1001002003004', NOW(), NULL, 1, '[{"time":"2023-05-25 15:30:00","context":"快件已被揽收"}]', NOW(), NOW()),
    (2, '2023052500002', '快递', '京东物流', 'JD2001002003004', NOW(), NULL, 1, '[{"time":"2023-05-25 16:30:00","context":"快件已被揽收"}]', NOW(), NOW()),
    (3, '2023052500003', '快递', '中通快递', 'ZTO3001002003004', DATE_SUB(NOW(), INTERVAL 2 DAY), NOW(), 2, '[{"time":"2023-05-23 17:30:00","context":"快件已被揽收"},{"time":"2023-05-25 10:30:00","context":"快件已签收"}]', NOW(), NOW());