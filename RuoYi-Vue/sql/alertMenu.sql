-- 库存盘点单表
CREATE TABLE `tb_inventory_check` (
                                      `id` BIGINT AUTO_INCREMENT COMMENT '盘点单ID',
                                      `check_no` VARCHAR(32) NOT NULL COMMENT '盘点单号',
                                      `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
                                      `merchant_id` BIGINT COMMENT '商家ID',
                                      `check_type` TINYINT DEFAULT 1 COMMENT '盘点类型：1全面盘点，2抽样盘点，3动态盘点',
                                      `status` TINYINT DEFAULT 0 COMMENT '状态：0草稿，1待审核，2已审核，3已取消',
                                      `total_quantity` INT DEFAULT 0 COMMENT '盘点总数量',
                                      `profit_quantity` INT DEFAULT 0 COMMENT '盘盈数量',
                                      `loss_quantity` INT DEFAULT 0 COMMENT '盘亏数量',
                                      `profit_amount` DECIMAL(12,2) DEFAULT 0 COMMENT '盘盈金额',
                                      `loss_amount` DECIMAL(12,2) DEFAULT 0 COMMENT '盘亏金额',
                                      `operator` VARCHAR(64) COMMENT '操作人',
                                      `operator_id` BIGINT COMMENT '操作人ID',
                                      `check_time` DATETIME COMMENT '盘点时间',
                                      `audit_time` DATETIME COMMENT '审核时间',
                                      `auditor` VARCHAR(64) COMMENT '审核人',
                                      `remark` VARCHAR(512) COMMENT '备注',
                                      `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      `create_by` VARCHAR(64) COMMENT '创建人',
                                      `update_by` VARCHAR(64) COMMENT '更新人',
                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `uk_check_no` (`check_no`),
                                      KEY `idx_warehouse_id` (`warehouse_id`),
                                      KEY `idx_merchant_id` (`merchant_id`),
                                      CONSTRAINT `fk_inventory_check_merchant` FOREIGN KEY (`merchant_id`) REFERENCES `tb_merchant` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                                      CONSTRAINT `fk_inventory_check_warehouse` FOREIGN KEY (`warehouse_id`) REFERENCES `tb_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存盘点单表';

-- 库存盘点单明细表
CREATE TABLE `tb_inventory_check_item` (
                                           `id` BIGINT AUTO_INCREMENT COMMENT '明细ID',
                                           `check_id` BIGINT NOT NULL COMMENT '盘点单ID',
                                           `check_no` VARCHAR(32) NOT NULL COMMENT '盘点单号',
                                           `product_id` BIGINT NOT NULL COMMENT '商品ID',
                                           `sku_id` BIGINT NOT NULL COMMENT 'SKU ID',
                                           `product_name` VARCHAR(200) NOT NULL COMMENT '商品名称',
                                           `sku_spec` VARCHAR(200) COMMENT 'SKU规格',
                                           `system_quantity` INT DEFAULT 0 COMMENT '系统数量',
                                           `actual_quantity` INT DEFAULT 0 COMMENT '实际数量',
                                           `difference_quantity` INT DEFAULT 0 COMMENT '差异数量',
                                           `cost_price` DECIMAL(10,2) COMMENT '成本价',
                                           `difference_amount` DECIMAL(12,2) DEFAULT 0 COMMENT '差异金额',
                                           `difference_type` TINYINT COMMENT '差异类型：1盘盈，2盘亏，0无差异',
                                           `remark` VARCHAR(512) COMMENT '备注',
                                           `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                           `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                           `create_by` VARCHAR(64) COMMENT '创建人',
                                           `update_by` VARCHAR(64) COMMENT '更新人',
                                           PRIMARY KEY (`id`),
                                           KEY `idx_check_id` (`check_id`),
                                           KEY `idx_check_no` (`check_no`),
                                           KEY `idx_product_id` (`product_id`),
                                           KEY `idx_sku_id` (`sku_id`),
                                           CONSTRAINT `fk_inventory_check_item` FOREIGN KEY (`check_id`) REFERENCES `tb_inventory_check` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存盘点单明细表';

-- 库存盘点单测试数据
INSERT INTO `tb_inventory_check`
(`check_no`, `warehouse_id`, `merchant_id`, `check_type`, `status`, `total_quantity`,
 `profit_quantity`, `loss_quantity`, `profit_amount`, `loss_amount`, `operator`,
 `operator_id`, `check_time`, `audit_time`, `auditor`, `remark`, `create_time`)
VALUES
-- 苹果官方旗舰店(ID=1)的盘点记录
('CHK20250410001', 1, 1, 1, 2, 150, 3, 5, 5997.00, 9995.00, '张经理',
 101, '2025-04-10 09:30:00', '2025-04-10 15:30:00', '系统管理员', '季度全面盘点', '2025-04-10 08:15:00'),

('CHK20250420001', 2, 1, 2, 1, 80, 0, 2, 0.00, 3998.00, '张助理',
 102, '2025-04-20 10:15:00', NULL, NULL, '月度抽样盘点', '2025-04-20 09:30:00'),

-- 三星官方旗舰店(ID=2)的盘点记录
('CHK20250412001', 3, 2, 1, 2, 120, 4, 2, 7996.00, 3998.00, '李经理',
 201, '2025-04-12 14:00:00', '2025-04-12 17:30:00', '系统管理员', '季度全面盘点', '2025-04-12 13:20:00'),

-- 耐克官方旗舰店(ID=3)的盘点记录
('CHK20250415001', 2, 3, 3, 0, 200, 0, 0, 0.00, 0.00, '王经理',
 301, NULL, NULL, NULL, '动态盘点草稿', '2025-04-15 11:45:00');

-- 库存盘点单明细测试数据
INSERT INTO `tb_inventory_check_item`
(`check_id`, `check_no`, `product_id`, `sku_id`, `product_name`, `sku_spec`,
 `system_quantity`, `actual_quantity`, `difference_quantity`, `cost_price`,
 `difference_amount`, `difference_type`, `remark`)
VALUES
-- 苹果官方旗舰店盘点明细 (check_id=1)
(1, 'CHK20250410001', 101, 1001, 'iPhone 15 Pro', '256GB 深空黑',
 50, 48, -2, 1999.00, -3998.00, 2, '可能有丢失'),

(1, 'CHK20250410001', 102, 1002, 'iPhone 15 Pro Max', '512GB 银色',
 30, 33, 3, 1999.00, 5997.00, 1, '系统录入错误'),

(1, 'CHK20250410001', 103, 1003, 'AirPods Pro', '第二代',
 70, 67, -3, 1999.00, -5997.00, 2, '展示样品未及时登记'),

-- 苹果官方旗舰店盘点明细 (check_id=2)
(2, 'CHK20250420001', 101, 1001, 'iPhone 15 Pro', '256GB 深空黑',
 40, 38, -2, 1999.00, -3998.00, 2, '待查明原因'),

(2, 'CHK20250420001', 104, 1004, 'iPad Air', '256GB WiFi版',
 40, 40, 0, 799.00, 0.00, 0, '数量一致'),

-- 三星官方旗舰店盘点明细 (check_id=3)
(3, 'CHK20250412001', 201, 2001, 'Galaxy S23', '256GB 午夜黑',
 45, 43, -2, 1999.00, -3998.00, 2, '展示样品未登记'),

(3, 'CHK20250412001', 202, 2002, 'Galaxy Tab S9', '128GB 银色',
 35, 39, 4, 1999.00, 7996.00, 1, '入库单漏录'),

(3, 'CHK20250412001', 203, 2003, 'Galaxy Watch 6', '44mm 黑色',
 40, 40, 0, 399.00, 0.00, 0, '数量一致');