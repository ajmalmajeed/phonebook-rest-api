# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table contact (
  id                            bigint auto_increment not null,
  con_name                      varchar(255) not null,
  con_address                   varchar(255),
  con_email                     varchar(255),
  mobile_number                 varchar(255),
  home_number                   varchar(255),
  office_number                 varchar(255),
  user_id                       bigint,
  constraint pk_contact primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  first_name                    varchar(255) not null,
  last_name                     varchar(255) not null,
  email                         varchar(255) not null,
  password                      varchar(255) not null,
  constraint pk_user primary key (id)
);

alter table contact add constraint fk_contact_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_contact_user_id on contact (user_id);


# --- !Downs

alter table contact drop foreign key fk_contact_user_id;
drop index ix_contact_user_id on contact;

drop table if exists contact;

drop table if exists user;

