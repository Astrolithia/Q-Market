-- 仓库表
CREATE TABLE `tb_warehouse` (
                                `id` BIGINT AUTO_INCREMENT COMMENT '仓库ID',
                                `name` VARCHAR(100) NOT NULL COMMENT '仓库名称',
                                `code` VARCHAR(32) COMMENT '仓库编码',
                                `address` VARCHAR(255) COMMENT '仓库地址',
                                `contact_person` VARCHAR(50) COMMENT '联系人',
                                `contact_phone` VARCHAR(20) COMMENT '联系电话',
                                `merchant_id` BIGINT COMMENT '商家ID',
                                `status` TINYINT DEFAULT 1 COMMENT '状态：0禁用，1启用',
                                `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                `create_by` VARCHAR(64) COMMENT '创建人',
                                `update_by` VARCHAR(64) COMMENT '更新人',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仓库表';

-- 库存表
CREATE TABLE `tb_inventory` (
                                `id` BIGINT AUTO_INCREMENT COMMENT '库存ID',
                                `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
                                `product_id` BIGINT NOT NULL COMMENT '商品ID',
                                `sku_id` BIGINT NOT NULL COMMENT 'SKU ID',
                                `quantity` INT DEFAULT 0 COMMENT '数量',
                                `lock_quantity` INT DEFAULT 0 COMMENT '锁定数量',
                                `available_quantity` INT DEFAULT 0 COMMENT '可用数量',
                                `alert_quantity` INT DEFAULT 0 COMMENT '预警数量',
                                `cost_price` DECIMAL(10,2) COMMENT '成本价',
                                `merchant_id` BIGINT COMMENT '商家ID',
                                `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `uk_warehouse_sku` (`warehouse_id`, `sku_id`),
                                KEY `idx_product_id` (`product_id`),
                                KEY `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存表';

-- 入库单表
CREATE TABLE `tb_stock_in` (
                               `id` BIGINT AUTO_INCREMENT COMMENT '入库单ID',
                               `in_no` VARCHAR(32) NOT NULL COMMENT '入库单号',
                               `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
                               `merchant_id` BIGINT COMMENT '商家ID',
                               `in_type` TINYINT DEFAULT 1 COMMENT '入库类型：1采购入库，2退货入库，3调拨入库',
                               `status` TINYINT DEFAULT 0 COMMENT '状态：0待审核，1已审核，2已取消',
                               `total_quantity` INT DEFAULT 0 COMMENT '入库总数量',
                               `total_amount` DECIMAL(12,2) DEFAULT 0 COMMENT '入库总金额',
                               `operator` VARCHAR(64) COMMENT '操作人',
                               `operator_id` BIGINT COMMENT '操作人ID',
                               `audit_time` DATETIME COMMENT '审核时间',
                               `auditor` VARCHAR(64) COMMENT '审核人',
                               `remark` VARCHAR(512) COMMENT '备注',
                               `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_in_no` (`in_no`),
                               KEY `idx_warehouse_id` (`warehouse_id`),
                               KEY `idx_merchant_id` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='入库单表';

-- 入库单明细表
CREATE TABLE `tb_stock_in_item` (
                                    `id` BIGINT AUTO_INCREMENT COMMENT '明细ID',
                                    `in_id` BIGINT NOT NULL COMMENT '入库单ID',
                                    `in_no` VARCHAR(32) NOT NULL COMMENT '入库单号',
                                    `product_id` BIGINT NOT NULL COMMENT '商品ID',
                                    `sku_id` BIGINT NOT NULL COMMENT 'SKU ID',
                                    `product_name` VARCHAR(200) NOT NULL COMMENT '商品名称',
                                    `sku_spec` VARCHAR(200) COMMENT 'SKU规格',
                                    `quantity` INT NOT NULL COMMENT '入库数量',
                                    `cost_price` DECIMAL(10,2) COMMENT '成本价',
                                    `amount` DECIMAL(12,2) DEFAULT 0 COMMENT '金额',
                                    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    PRIMARY KEY (`id`),
                                    KEY `idx_in_id` (`in_id`),
                                    KEY `idx_in_no` (`in_no`),
                                    KEY `idx_product_id` (`product_id`),
                                    KEY `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='入库单明细表';

INSERT INTO tb_warehouse (name, code, address, contact_person, contact_phone, merchant_id, status, create_time, update_time, create_by, update_by)
VALUES
    ('北京中央仓库', 'WH001', '北京市顺义区空港物流园区A区1号', '刘仓管', '13800001111', 1, 1, NOW(), NOW(), 'admin', 'admin'),
    ('上海分仓', 'WH002', '上海市嘉定区嘉定工业园区B区2号', '陈仓管', '13800002222', 2, 1, NOW(), NOW(), 'admin', 'admin'),
    ('广州分仓', 'WH003', '广州市白云区石井镇物流园C区3号', '黄仓管', '13800003333', 3, 1, NOW(), NOW(), 'admin', 'admin');

INSERT INTO tb_inventory (warehouse_id, product_id, sku_id, quantity, lock_quantity, available_quantity, alert_quantity, cost_price, merchant_id, update_time)
VALUES
    (1, 1, 1, 50, 0, 50, 10, 5000.00, 1, NOW()),
    (1, 1, 2, 50, 0, 50, 10, 5000.00, 1, NOW()),
    (2, 2, 3, 70, 0, 70, 10, 4000.00, 2, NOW()),
    (2, 2, 4, 80, 0, 80, 10, 4000.00, 2, NOW()),
    (3, 3, 5, 100, 0, 100, 20, 500.00, 3, NOW()),
    (3, 3, 6, 100, 0, 100, 20, 500.00, 3, NOW());

INSERT INTO tb_stock_in (in_no, warehouse_id, merchant_id, in_type, status, total_quantity, total_amount, operator, operator_id, audit_time, auditor, remark, create_time, update_time)
VALUES
    ('IN202305250001', 1, 1, 1, 1, 100, 500000.00, '刘仓管', 101, NOW(), '张审核', 'iPhone 14 Pro首批入库', NOW(), NOW()),
    ('IN202305250002', 2, 2, 1, 1, 150, 600000.00, '陈仓管', 102, NOW(), '李审核', 'Galaxy S23首批入库', NOW(), NOW()),
    ('IN202305250003', 3, 3, 1, 0, 200, 100000.00, '黄仓管', 103, NULL, NULL, 'Nike运动鞋入库，待审核', NOW(), NOW());

INSERT INTO tb_stock_in_item (in_id, in_no, product_id, sku_id, product_name, sku_spec, quantity, cost_price, amount, create_time)
VALUES
    (1, 'IN202305250001', 1, 1, 'iPhone 14 Pro', '{"颜色":"黑色","存储":"256GB"}', 50, 5000.00, 250000.00, NOW()),
    (1, 'IN202305250001', 1, 2, 'iPhone 14 Pro', '{"颜色":"白色","存储":"256GB"}', 50, 5000.00, 250000.00, NOW()),
    (2, 'IN202305250002', 2, 3, 'Galaxy S23', '{"颜色":"黑色","存储":"256GB"}', 70, 4000.00, 280000.00, NOW()),
    (2, 'IN202305250002', 2, 4, 'Galaxy S23', '{"颜色":"白色","存储":"256GB"}', 80, 4000.00, 320000.00, NOW()),
    (3, 'IN202305250003', 3, 5, 'Nike Air Max 270', '{"颜色":"黑色","尺码":"42"}', 100, 500.00, 50000.00, NOW()),
    (3, 'IN202305250003', 3, 6, 'Nike Air Max 270', '{"颜色":"黑色","尺码":"43"}', 100, 500.00, 50000.00, NOW());