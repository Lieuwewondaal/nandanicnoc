# --- First database schema

# --- !Ups

create table company (
  id                        bigint not null auto_increment,
  name                      varchar(255),
  constraint pk_company primary key (id)
  ) engine=innodb
;

create table diagnose (
  id                        bigint not null auto_increment,
  name                      varchar(255),
  introduced                datetime,
  discontinued              datetime,
  company_id                bigint,
  constraint pk_diagnose primary key (id)
  ) engine=innodb
;


alter table diagnose add constraint fk_diagnose_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
create index ix_diagnose_company_1 on diagnose (company_id);


# --- !Downs

SET FOREIGN_KEY_CHECKS = 0

drop table if exists company;

drop table if exists diagnose;

SET FOREIGN_KEY_CHECKS = 1

