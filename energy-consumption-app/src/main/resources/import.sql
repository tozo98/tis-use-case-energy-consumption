insert into profile(id, name) values(1, 'A');

insert into fraction(id, profile_id, month, value) values(1, 1, 1, 0.2);
insert into fraction(id, profile_id, month, value) values(2, 1, 2, 0.1);
insert into fraction(id, profile_id, month, value) values(3, 1, 3, 0.0);
insert into fraction(id, profile_id, month, value) values(4, 1, 4, 0.0);
insert into fraction(id, profile_id, month, value) values(5, 1, 5, 0.0);
insert into fraction(id, profile_id, month, value) values(6, 1, 6, 0.0);
insert into fraction(id, profile_id, month, value) values(7, 1, 7, 0.0);
insert into fraction(id, profile_id, month, value) values(8, 1, 8, 0.0);
insert into fraction(id, profile_id, month, value) values(9, 1, 9, 0.0);
insert into fraction(id, profile_id, month, value) values(10, 1, 10, 0.1);
insert into fraction(id, profile_id, month, value) values(11, 1, 11, 0.3);
insert into fraction(id, profile_id, month, value) values(12, 1, 12, 0.3);

insert into meter(id, profile_id) values(1, 1);

insert into meter_reading(id, month, reading, consumption, meter_id) values(1, 1, 5, 0, 1);
insert into meter_reading(id, month, reading, consumption, meter_id) values(2, 2, 10, 0, 1);
insert into meter_reading(id, month, reading, consumption, meter_id) values(3, 3, 15, 0, 1);
insert into meter_reading(id, month, reading, consumption, meter_id) values(4, 4, 20, 0, 1);
insert into meter_reading(id, month, reading, consumption, meter_id) values(5, 5, 25, 0, 1);
insert into meter_reading(id, month, reading, consumption, meter_id) values(6, 6, 30, 0, 1);
insert into meter_reading(id, month, reading, consumption, meter_id) values(7, 7, 35, 0, 1);
insert into meter_reading(id, month, reading, consumption, meter_id) values(8, 8, 40, 0, 1);
insert into meter_reading(id, month, reading, consumption, meter_id) values(9, 9, 45, 0, 1);
insert into meter_reading(id, month, reading, consumption, meter_id) values(10, 10, 50, 0, 1);
insert into meter_reading(id, month, reading, consumption, meter_id) values(11, 11, 55, 0, 1);
insert into meter_reading(id, month, reading, consumption, meter_id) values(12, 12, 60, 0, 1);
