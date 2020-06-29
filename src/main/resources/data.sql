insert into user(id, name, username, password, access, logged) values (9, "admin", "admin", "admin", false, false);
insert into role (role, user_id) values ('ROLE_ADMIN', 9);
insert into admin(id) values (9);