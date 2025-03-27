-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品SKU', '2001', '1', 'sku', 'sku/sku/index', 1, 0, 'C', '0', '0', 'sku:sku:list', '#', 'admin', sysdate(), '', null, '商品SKU菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品SKU查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'sku:sku:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品SKU新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'sku:sku:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品SKU修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'sku:sku:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品SKU删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'sku:sku:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品SKU导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'sku:sku:export',       '#', 'admin', sysdate(), '', null, '');