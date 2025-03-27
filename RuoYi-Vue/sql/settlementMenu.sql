-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家结算管理', '2094', '1', 'settlement', 'merchant/settlement/index', 1, 0, 'C', '0', '0', 'merchant:settlement:list', '#', 'admin', sysdate(), '', null, '商家结算管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家结算管理查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'merchant:settlement:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家结算管理新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'merchant:settlement:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家结算管理修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'merchant:settlement:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家结算管理删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'merchant:settlement:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家结算管理导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'merchant:settlement:export',       '#', 'admin', sysdate(), '', null, '');