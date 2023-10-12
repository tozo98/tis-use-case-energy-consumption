-- TODO:
-- add schema and provide some initial data to demo the correct behavior of the application

CREATE TABLE message (
    id bigint,
    content character varying(100)
);

insert into message (id, content) values(1, 'message');
insert into message (id, content) values(2, 'hello world!');
insert into message (id, content) values(3, 'it is alive');