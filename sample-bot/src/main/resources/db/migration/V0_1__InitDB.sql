create table public.message
(
  id bigserial not null,
  ts timestamp without time zone,
  message json,
  constraint message_pk primary key (id)
);
