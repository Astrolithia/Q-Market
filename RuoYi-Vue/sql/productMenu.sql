-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动商品关联', '2075', '1', 'product', 'marketing/product/index', 1, 0, 'C', '0', '0', 'marketing:product:list', '#', 'admin', sysdate(), '', null, '活动商品关联菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动商品关联查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'marketing:product:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动商品关联新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'marketing:product:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动商品关联修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'marketing:product:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动商品关联删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'marketing:product:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动商品关联导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'marketing:product:export',       '#', 'admin', sysdate(), '', null, '');