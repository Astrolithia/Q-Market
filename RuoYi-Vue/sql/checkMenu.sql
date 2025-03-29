-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存盘点', '2113', '1', 'check', 'inventory/check/index', 1, 0, 'C', '0', '0', 'inventory:check:list', '#', 'admin', sysdate(), '', null, '库存盘点菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存盘点查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'inventory:check:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存盘点新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'inventory:check:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存盘点修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'inventory:check:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存盘点删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'inventory:check:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存盘点导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'inventory:check:export',       '#', 'admin', sysdate(), '', null, '');