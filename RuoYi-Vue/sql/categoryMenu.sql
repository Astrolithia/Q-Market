-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品分类管理', '2001', '1', 'category', 'product/category/index', 1, 0, 'C', '0', '0', 'product:category:list', '#', 'admin', sysdate(), '', null, '商品分类管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品分类管理查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'product:category:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品分类管理新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'product:category:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品分类管理修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'product:category:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品分类管理删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'product:category:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品分类管理导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'product:category:export',       '#', 'admin', sysdate(), '', null, '');