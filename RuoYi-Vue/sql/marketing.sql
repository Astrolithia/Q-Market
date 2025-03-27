-- 优惠券表
CREATE TABLE `tb_coupon` (
                             `id` BIGINT AUTO_INCREMENT COMMENT '优惠券ID',
                             `name` VARCHAR(100) NOT NULL COMMENT '优惠券名称',
                             `type` TINYINT NOT NULL COMMENT '优惠券类型：1满减券，2折扣券，3直减券',
                             `value` DECIMAL(10,2) NOT NULL COMMENT '优惠券面值',
                             `min_point` DECIMAL(10,2) DEFAULT 0 COMMENT '使用门槛，0表示无门槛',
                             `start_time` DATETIME NOT NULL COMMENT '开始时间',
                             `end_time` DATETIME NOT NULL COMMENT '结束时间',
                             `total` INT DEFAULT 0 COMMENT '发行量，0表示不限量',
                             `used` INT DEFAULT 0 COMMENT '已领取数量',
                             `receive_count` INT DEFAULT 1 COMMENT '每人限领数量',
                             `status` TINYINT DEFAULT 1 COMMENT '状态：0禁用，1启用',
                             `description` VARCHAR(512) COMMENT '使用说明',
                             `merchant_id` BIGINT COMMENT '商家ID',
                             `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `create_by` VARCHAR(64) COMMENT '创建人',
                             `update_by` VARCHAR(64) COMMENT '更新人',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券表';

-- 客户优惠券表
CREATE TABLE `tb_customer_coupon` (
                                      `id` BIGINT AUTO_INCREMENT COMMENT 'ID',
                                      `coupon_id` BIGINT NOT NULL COMMENT '优惠券ID',
                                      `customer_id` BIGINT NOT NULL COMMENT '客户ID',
                                      `status` TINYINT DEFAULT 0 COMMENT '状态：0未使用，1已使用，2已过期',
                                      `use_time` DATETIME COMMENT '使用时间',
                                      `order_id` BIGINT COMMENT '订单ID',
                                      `get_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '领取时间',
                                      PRIMARY KEY (`id`),
                                      KEY `idx_coupon_id` (`coupon_id`),
                                      KEY `idx_customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户优惠券表';

-- 营销活动表
CREATE TABLE `tb_promotion` (
                                `id` BIGINT AUTO_INCREMENT COMMENT '活动ID',
                                `name` VARCHAR(100) NOT NULL COMMENT '活动名称',
                                `type` TINYINT NOT NULL COMMENT '活动类型：1秒杀，2拼团，3满减，4限时折扣',
                                `start_time` DATETIME NOT NULL COMMENT '开始时间',
                                `end_time` DATETIME NOT NULL COMMENT '结束时间',
                                `status` TINYINT DEFAULT 0 COMMENT '状态：0未开始，1进行中，2已结束，3已取消',
                                `rules` TEXT COMMENT '活动规则，JSON格式',
                                `description` VARCHAR(512) COMMENT '活动描述',
                                `banner` VARCHAR(255) COMMENT '活动banner',
                                `merchant_id` BIGINT COMMENT '商家ID',
                                `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                `create_by` VARCHAR(64) COMMENT '创建人',
                                `update_by` VARCHAR(64) COMMENT '更新人',
                                PRIMARY KEY (`id`),
                                KEY `idx_type` (`type`),
                                KEY `idx_status` (`status`),
                                KEY `idx_time` (`start_time`, `end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='营销活动表';

-- 活动商品关联表
CREATE TABLE `tb_promotion_product` (
                                        `id` BIGINT AUTO_INCREMENT COMMENT 'ID',
                                        `promotion_id` BIGINT NOT NULL COMMENT '活动ID',
                                        `product_id` BIGINT NOT NULL COMMENT '商品ID',
                                        `sku_id` BIGINT COMMENT 'SKU ID',
                                        `promotion_price` DECIMAL(10,2) COMMENT '活动价格',
                                        `stock_limit` INT DEFAULT 0 COMMENT '限购数量，0表示不限购',
                                        `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        PRIMARY KEY (`id`),
                                        KEY `idx_promotion_id` (`promotion_id`),
                                        KEY `idx_product_id` (`product_id`),
                                        KEY `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动商品关联表';