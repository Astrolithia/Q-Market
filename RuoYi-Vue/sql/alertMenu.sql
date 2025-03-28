-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存预警', '2113', '1', 'alert', 'inventory/alert/index', 1, 0, 'C', '0', '0', 'inventory:alert:list', '#', 'admin', sysdate(), '', null, '库存预警菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存预警查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'inventory:alert:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存预警新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'inventory:alert:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存预警修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'inventory:alert:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存预警删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'inventory:alert:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存预警导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'inventory:alert:export',       '#', 'admin', sysdate(), '', null, '');