create table if exists public.config_parameter(
	id integer not null,
	service_name varchar(100) not null,
	key varchar(100) not null,
	value varchar(100) not null,
	status varchar(50) not null,
	created_at timestamp not null,

	constraint config_parameter_pkey primary key (id)
);

insert into public.config_parameter (
	id, service_name, key, value, status
)
values
(1, 'client-service', 'account.prefix.name', 'value1_as', 'active'),
(2, 'client-service', 'acc.key1', 'value2_as', 'active'),
(3, 'client-service', 'acc.key2', 'value3_as', 'active')
;

select * from public.config_parameter cp;