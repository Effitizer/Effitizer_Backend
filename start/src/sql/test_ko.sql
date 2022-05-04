use effitizer;

ALTER TABLE user convert to charset utf8;

INSERT INTO User(ID, Name, email, role, is_subscribed)
VALUES(5, '이순신', '2016-02-16', 'admin', false);

select * from user;