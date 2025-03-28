-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存查询', '2113', '1', 'inventory', 'inventory/inventory/index', 1, 0, 'C', '0', '0', 'inventory:inventory:list', '#', 'admin', sysdate(), '', null, '库存查询菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存查询查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'inventory:inventory:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存查询新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'inventory:inventory:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存查询修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'inventory:inventory:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存查询删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'inventory:inventory:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存查询导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'inventory:inventory:export',       '#', 'admin', sysdate(), '', null, '');