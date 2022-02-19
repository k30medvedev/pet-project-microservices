INSERT INTO public.users (user_id, name, email, password, rating, created_at, updated_at, active, activation_key)
VALUES ('f3aa0ba4-47e6-47b3-853f-3ed8cda54cd3', 'John', 'john@mail.com', '$2y$12$iSWotGHbgM7CPFL6sRXOrO4aSifHFavNXb8XemzhJE11IrKUxO2aa ', null, '2021-04-14 14:45:12.046301', null, true, 'gqwtogtmy5go48wsnhi8w9');
INSERT INTO public.users (user_id, name, email, password, rating, created_at, updated_at, active, activation_key)
VALUES ('62fb43f8-1217-4c28-8c2f-53bc11c3ffb1', 'Alex', 'alex@mail.com', '$2y$12$iSWotGHbgM7CPFL6sRXOrO4aSifHFavNXb8XemzhJE11IrKUxO2aa ', 5, '2021-04-14 14:45:12.046301', null, true, 'gqwtogtmy5go48wsnhi8w9');
INSERT INTO public.users (user_id, name, email, password, rating, created_at, updated_at, active, activation_key)
VALUES ('a92c3afd-338b-4b0d-963e-2a79658e1f15', 'Nik', 'nik@mail.com', '$2y$12$iSWotGHbgM7CPFL6sRXOrO4aSifHFavNXb8XemzhJE11IrKUxO2aa ', null, '2021-04-14 14:45:12.046301', null, true, 'gqwtogtmy5go48wsnhi8w9');
INSERT INTO public.users (user_id, name, email, password, rating, created_at, updated_at, active, activation_key)
VALUES ('9fb9d1f9-e5f6-4e72-940e-3cfa2e17dd44', 'Bob', 'bob@mail.com', '$2y$12$iSWotGHbgM7CPFL6sRXOrO4aSifHFavNXb8XemzhJE11IrKUxO2aa ', 8, '2021-04-14 14:45:12.046301', null, true, 'gqwtogtmy5go48wsnhi8w9');
INSERT INTO public.users (user_id, name, email, password, rating, created_at, updated_at, active, activation_key)
VALUES ('5c40359c-9671-471a-a918-deee078cda9d', 'Karl', 'karl@mail.com', '$2y$12$iSWotGHbgM7CPFL6sRXOrO4aSifHFavNXb8XemzhJE11IrKUxO2aa ', 8, '2021-04-14 14:45:12.046301', null, true, 'gqwtogtmy5go48wsnhi8w9');
INSERT INTO public.users (user_id, name, email, password, rating, created_at, updated_at, active, activation_key)
VALUES ('56acfe7d-2865-46b3-bbf0-e418f6f7524c', 'Ivan', 'ivan@mail.com', '$2y$12$iSWotGHbgM7CPFL6sRXOrO4aSifHFavNXb8XemzhJE11IrKUxO2aa ', 9, '2021-04-14 14:45:12.046301', null, true, 'gqwtogtmy5go48wsnhi8w9');
INSERT INTO public.users (user_id, name, email, password, rating, created_at, updated_at, active, activation_key)
VALUES ('4bca0309-3707-4de7-819b-fb7c13ff8183', 'Oleg', 'oleg@mail.com', '$2y$12$iSWotGHbgM7CPFL6sRXOrO4aSifHFavNXb8XemzhJE11IrKUxO2aa ', 7, '2021-04-14 14:45:12.046301', null, true, 'gqwtogtmy5go48wsnhi8w9');
INSERT INTO public.users (user_id, name, email, password, rating, created_at, updated_at, active, activation_key)
VALUES ('6910a877-27a7-4b93-972b-f84b0fd933d3', 'Vasia', 'vasia@mail.com', '$2y$12$iSWotGHbgM7CPFL6sRXOrO4aSifHFavNXb8XemzhJE11IrKUxO2aa ', 10, '2021-04-14 14:45:12.046301', null, true, 'gqwtogtmy5go48wsnhi8w9');


INSERT INTO public.roles (id, name)
VALUES (1, 'ADMIN');
INSERT INTO public.roles (id, name)
VALUES (2, 'DRIVER');
INSERT INTO public.roles (id, name)
VALUES (3, 'OPERATOR');


INSERT INTO public.users_roles (user_id, role_id)
VALUES ('f3aa0ba4-47e6-47b3-853f-3ed8cda54cd3', 1);
INSERT INTO public.users_roles (user_id, role_id)
VALUES ('62fb43f8-1217-4c28-8c2f-53bc11c3ffb1', 2);
INSERT INTO public.users_roles (user_id, role_id)
VALUES ('a92c3afd-338b-4b0d-963e-2a79658e1f15', 3);
INSERT INTO public.users_roles (user_id, role_id)
VALUES ('f3aa0ba4-47e6-47b3-853f-3ed8cda54cd3', 2);

INSERT INTO public.permissions (id, name) VALUES ('d50c981d-c39f-4ccd-a352-5b945e43e21e', 'user:read');
INSERT INTO public.permissions (id, name) VALUES ('9635d129-cf15-4d45-97b0-d6070e037dfc', 'user:write');

INSERT INTO public.roles_permissions (role_id, permission_id) VALUES (1, 'd50c981d-c39f-4ccd-a352-5b945e43e21e');
INSERT INTO public.roles_permissions (role_id, permission_id) VALUES (1, '9635d129-cf15-4d45-97b0-d6070e037dfc');
