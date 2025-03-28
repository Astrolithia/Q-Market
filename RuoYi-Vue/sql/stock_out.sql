-- 出库单表
CREATE TABLE `tb_stock_out` (
                                `id` BIGINT AUTO_INCREMENT COMMENT '出库单ID',
                                `out_no` VARCHAR(32) NOT NULL COMMENT '出库单号',
                                `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
                                `merchant_id` BIGINT COMMENT '商家ID',
                                `out_type` TINYINT DEFAULT 1 COMMENT '出库类型：1销售出库，2退货出库，3调拨出库',
                                `status` TINYINT DEFAULT 0 COMMENT '状态：0待审核，1已审核，2已取消',
                                `total_quantity` INT DEFAULT 0 COMMENT '出库总数量',
                                `total_amount` DECIMAL(12,2) DEFAULT 0 COMMENT '出库总金额',
                                `operator` VARCHAR(64) COMMENT '操作人',
                                `operator_id` BIGINT COMMENT '操作人ID',
                                `audit_time` DATETIME COMMENT '审核时间',
                                `auditor` VARCHAR(64) COMMENT '审核人',
                                `order_id` BIGINT COMMENT '关联订单ID',
                                `order_no` VARCHAR(32) COMMENT '关联订单号',
                                `remark` VARCHAR(512) COMMENT '备注',
                                `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `uk_out_no` (`out_no`),
                                KEY `idx_warehouse_id` (`warehouse_id`),
                                KEY `idx_merchant_id` (`merchant_id`),
                                KEY `idx_order_id` (`order_id`),
                                KEY `idx_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='出库单表';

-- 出库单明细表
CREATE TABLE `tb_stock_out_item` (
                                     `id` BIGINT AUTO_INCREMENT COMMENT '明细ID',
                                     `out_id` BIGINT NOT NULL COMMENT '出库单ID',
                                     `out_no` VARCHAR(32) NOT NULL COMMENT '出库单号',
                                     `product_id` BIGINT NOT NULL COMMENT '商品ID',
                                     `sku_id` BIGINT NOT NULL COMMENT 'SKU ID',
                                     `product_name` VARCHAR(200) NOT NULL COMMENT '商品名称',
                                     `sku_spec` VARCHAR(200) COMMENT 'SKU规格',
                                     `quantity` INT NOT NULL COMMENT '出库数量',
                                     `cost_price` DECIMAL(10,2) COMMENT '成本价',
                                     `amount` DECIMAL(12,2) DEFAULT 0 COMMENT '金额',
                                     `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     PRIMARY KEY (`id`),
                                     KEY `idx_out_id` (`out_id`),
                                     KEY `idx_out_no` (`out_no`),
                                     KEY `idx_product_id` (`product_id`),
                                     KEY `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='出库单明细表';

-- 出库单表测试数据
INSERT INTO `tb_stock_out` (`out_no`, `warehouse_id`, `merchant_id`, `out_type`, `status`, `total_quantity`, `total_amount`, `operator`, `operator_id`, `audit_time`, `auditor`, `order_id`, `order_no`, `remark`, `create_time`) VALUES
-- 销售出库-已审核
('OUT20230501001', 1, 101, 1, 1, 15, 4500.00, '张三', 1001, '2023-05-01 10:30:00', '李四', 10001, 'ORD20230501001', '正常销售出库', '2023-05-01 09:15:00'),
-- 销售出库-待审核
('OUT20230502001', 1, 101, 1, 0, 8, 2400.00, '张三', 1001, NULL, NULL, 10002, 'ORD20230502001', '待审核销售出库', '2023-05-02 09:20:00'),
-- 销售出库-已取消
('OUT20230503001', 2, 102, 1, 2, 5, 1500.00, '王五', 1002, NULL, NULL, 10003, 'ORD20230503001', '客户取消订单', '2023-05-03 11:25:00'),
-- 退货出库-已审核
('OUT20230504001', 2, 102, 2, 1, 3, 900.00, '赵六', 1003, '2023-05-04 14:30:00', '李四', NULL, NULL, '库存商品质量问题退货', '2023-05-04 13:15:00'),
-- 退货出库-待审核
('OUT20230505001', 3, 103, 2, 0, 2, 600.00, '王五', 1002, NULL, NULL, NULL, NULL, '过期商品退货', '2023-05-05 10:40:00'),
-- 调拨出库-已审核
('OUT20230506001', 1, 101, 3, 1, 10, 3000.00, '张三', 1001, '2023-05-06 16:30:00', '李四', NULL, NULL, '调拨至2号仓库', '2023-05-06 15:15:00'),
-- 调拨出库-待审核
('OUT20230507001', 3, 103, 3, 0, 6, 1800.00, '赵六', 1003, NULL, NULL, NULL, NULL, '调拨至1号仓库', '2023-05-07 09:55:00'),
-- 销售出库-大订单-已审核
('OUT20230508001', 2, 102, 1, 1, 25, 7500.00, '王五', 1002, '2023-05-08 11:30:00', '李四', 10004, 'ORD20230508001', '大客户批量采购', '2023-05-08 10:15:00'),
-- 销售出库-特价商品-已审核
('OUT20230509001', 1, 101, 1, 1, 12, 2880.00, '张三', 1001, '2023-05-09 14:30:00', '李四', 10005, 'ORD20230509001', '特价商品促销活动', '2023-05-09 13:20:00'),
-- 退货出库-大批量-待审核
('OUT20230510001', 3, 103, 2, 0, 20, 6000.00, '赵六', 1003, NULL, NULL, NULL, NULL, '批量退货处理', '2023-05-10 09:30:00');

-- 出库单明细表测试数据
INSERT INTO `tb_stock_out_item` (`out_id`, `out_no`, `product_id`, `sku_id`, `product_name`, `sku_spec`, `quantity`, `cost_price`, `amount`, `create_time`) VALUES
-- OUT20230501001的明细
(1, 'OUT20230501001', 1001, 2001, '高端智能手机', '黑色,256GB', 5, 2000.00, 10000.00, '2023-05-01 09:15:00'),
(1, 'OUT20230501001', 1002, 2002, '无线蓝牙耳机', '白色,标准版', 10, 200.00, 2000.00, '2023-05-01 09:15:00'),

-- OUT20230502001的明细
(2, 'OUT20230502001', 1003, 2003, '智能手表', '黑色,运动版', 8, 800.00, 6400.00, '2023-05-02 09:20:00'),

-- OUT20230503001的明细
(3, 'OUT20230503001', 1004, 2004, '电子书阅读器', '灰色,32GB', 5, 500.00, 2500.00, '2023-05-03 11:25:00'),

-- OUT20230504001的明细
(4, 'OUT20230504001', 1005, 2005, '游戏手柄', '黑色,无线版', 3, 300.00, 900.00, '2023-05-04 13:15:00'),

-- OUT20230505001的明细
(5, 'OUT20230505001', 1001, 2001, '高端智能手机', '黑色,256GB', 2, 2000.00, 4000.00, '2023-05-05 10:40:00'),

-- OUT20230506001的明细
(6, 'OUT20230506001', 1006, 2006, '便携式充电宝', '白色,20000mAh', 10, 150.00, 1500.00, '2023-05-06 15:15:00'),

-- OUT20230507001的明细
(7, 'OUT20230507001', 1003, 2003, '智能手表', '黑色,运动版', 6, 800.00, 4800.00, '2023-05-07 09:55:00'),

-- OUT20230508001的明细(多个商品)
(8, 'OUT20230508001', 1001, 2001, '高端智能手机', '黑色,256GB', 10, 2000.00, 20000.00, '2023-05-08 10:15:00'),
(8, 'OUT20230508001', 1002, 2002, '无线蓝牙耳机', '白色,标准版', 15, 200.00, 3000.00, '2023-05-08 10:15:00'),

-- OUT20230509001的明细(多个商品)
(9, 'OUT20230509001', 1004, 2004, '电子书阅读器', '灰色,32GB', 7, 500.00, 3500.00, '2023-05-09 13:20:00'),
(9, 'OUT20230509001', 1005, 2005, '游戏手柄', '黑色,无线版', 5, 300.00, 1500.00, '2023-05-09 13:20:00'),

-- OUT20230510001的明细
(10, 'OUT20230510001', 1006, 2006, '便携式充电宝', '白色,20000mAh', 20, 150.00, 3000.00, '2023-05-10 09:30:00');