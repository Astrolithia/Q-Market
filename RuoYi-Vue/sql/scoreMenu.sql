-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家评级', '2094', '1', 'score', 'merchant/score/index', 1, 0, 'C', '0', '0', 'merchant:score:list', '#', 'admin', sysdate(), '', null, '商家评级菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家评级查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'merchant:score:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家评级新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'merchant:score:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家评级修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'merchant:score:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家评级删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'merchant:score:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商家评级导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'merchant:score:export',       '#', 'admin', sysdate(), '', null, '');