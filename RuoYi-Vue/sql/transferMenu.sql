-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存调拨', '2113', '1', 'transfer', 'inventory/transfer/index', 1, 0, 'C', '0', '0', 'inventory:transfer:list', '#', 'admin', sysdate(), '', null, '库存调拨菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存调拨查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'inventory:transfer:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存调拨新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'inventory:transfer:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存调拨修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'inventory:transfer:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存调拨删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'inventory:transfer:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('库存调拨导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'inventory:transfer:export',       '#', 'admin', sysdate(), '', null, '');