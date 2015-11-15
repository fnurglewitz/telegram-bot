create table if not exists public.chat (
  id bigint primary key,
  chat_type character varying(50),
  title character varying(256),
  username character varying(256),
  first_name character varying(256),
  last_name character varying(256)
);

create or replace function public.parse_message()
returns trigger
as
$body$
begin

with chats as (
select distinct
  id, chat_type, title, username, first_name, last_name
from
(
select
   (NEW.message->'chat'->>'id')::bigint as id
  ,(NEW.message->'chat'->>'type')::character varying as chat_type
  ,(NEW.message->'chat'->>'title')::character varying as title
  ,(NEW.message->'chat'->>'username')::character varying as username
  ,(NEW.message->'chat'->>'first_name')::character varying as first_name
  ,(NEW.message->'chat'->>'last_name')::character varying as last_name
where
  (NEW.message->'chat'->>'id')::bigint is not null

union all

select
   (NEW.message->'from'->>'id')::bigint as id
  ,'private' as chat_type
  ,null as title
  ,(NEW.message->'from'->>'username')::character varying as username
  ,(NEW.message->'from'->>'first_name')::character varying as first_name
  ,(NEW.message->'from'->>'last_name')::character varying as last_name
where
  (NEW.message->'from'->>'id')::bigint is not null

union all

select
   (NEW.message->'forward_from'->>'id')::bigint as id
  ,'private' as chat_type
  ,null as title
  ,(NEW.message->'forward_from'->>'username')::character varying as username
  ,(NEW.message->'forward_from'->>'first_name')::character varying as first_name
  ,(NEW.message->'forward_from'->>'last_name')::character varying as last_name
where
  (NEW.message->'forward_from'->>'id')::bigint is not null

union all

select
   (NEW.message->'new_chat_participant'->>'id')::bigint as id
  ,'private' as chat_type
  ,null as title
  ,(NEW.message->'new_chat_participant'->>'username')::character varying as username
  ,(NEW.message->'new_chat_participant'->>'first_name')::character varying as first_name
  ,(NEW.message->'new_chat_participant'->>'last_name')::character varying as last_name
where
  (NEW.message->'new_chat_participant'->>'id')::bigint is not null

union all

select
   (NEW.message->'left_chat_participant'->>'id')::bigint as id
  ,'private' as chat_type
  ,null as title
  ,(NEW.message->'left_chat_participant'->>'username')::character varying as username
  ,(NEW.message->'left_chat_participant'->>'first_name')::character varying as first_name
  ,(NEW.message->'left_chat_participant'->>'last_name')::character varying as last_name
where
  (NEW.message->'left_chat_participant'->>'id')::bigint is not null  
) data
),
updates as (
  update public.chat as pc
    set  title = c.title
        ,username = c.username
        ,first_name = c.first_name
        ,last_name = c.last_name
  from
    chats c
  where
    pc.id = c.id
)
insert into public.chat ( id, chat_type, title, username, first_name, last_name )
select
  id, chat_type, title, username, first_name, last_name
from
  chats c
where
  not exists (select 1 from public.chat pc where pc.id = c.id);

return NEW;

end;
$body$
language plpgsql;

create trigger parse_message
after insert on public.message
for each row
execute procedure parse_message();
