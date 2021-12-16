insert into role(name) values('any role');
insert into users(name, role_id) values('Stepan', 1);
insert into rules(description) values('unknown');
insert into rules_role(rule_id, role_id) values(1, 1);
insert into item(status, user_id, category_id, state_id) values('processed', 1, 1, 1);
insert into comments(content, item_id) values('awaiting processing', 1);
insert into attachs(file, item_id) values('log.txt', 1);
insert into category(type) values('privileged');
insert into state(states) values('waiting');