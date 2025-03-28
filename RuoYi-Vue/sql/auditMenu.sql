-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家审核', '2094', '1', 'audit', 'merchant/audit/index', 1, 0, 'C', '0', '0', 'merchant:audit:list', '#', 'admin', sysdate(), '', null, '商家审核菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家审核查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'merchant:audit:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家审核新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'merchant:audit:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家审核修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'merchant:audit:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家审核删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'merchant:audit:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家审核导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'merchant:audit:export',       '#', 'admin', sysdate(), '', null, '');