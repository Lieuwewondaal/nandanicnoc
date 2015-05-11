# --- First database schema

# --- !Ups

create table diagnose (
  diagnose_id                        bigint not null auto_increment,

  constraint pk_diagnose primary key (diagnose_id)
  ) engine=innodb
;

create table diagnoseoverzicht (
  diagnose_id               		bigint not null,
  diagnoseversie_id					bigint not null, 
  diagnose_code                     bigint,
  gezondheidspatroon_id             bigint,
  diagnoseklasse_id              	bigint,
  diagnoseoverzicht_omschrijving 	text,
  diagnoseoverzicht_definitie 		text,
  
  constraint pk_diagnoseoverzicht_diagnose_fk primary key (diagnose_id, diagnoseversie_id)
  ) engine=innodb
;

create table gezondheidspatroon (
  gezondheidspatroon_id     bigint not null auto_increment,
  gezondheidspatroon_code   bigint,
  gezondheidspatroon_omschrijving text,

  constraint pk_gezondheidspatroon primary key (gezondheidspatroon_id)
  ) engine=innodb
;

create table diagnosedomein (
  diagnosedomein_id     bigint not null auto_increment,
  diagnosedomein_domein   bigint,
  diagnosedomein_omschrijving text,

  constraint pk_diagnosedomein primary key (diagnosedomein_id),
  constraint uc_domein UNIQUE (diagnosedomein_domein)
  ) engine=innodb
;

create table diagnoseklasse (
  diagnoseklasse_id		bigint not null auto_increment,
  diagnosedomein_id     bigint,
  diagnoseklasse_klasse bigint not null,
  diagnoseklasse_omschrijving text,

  constraint pk_diagnosedomein_fk primary key (diagnoseklasse_id)
  ) engine=innodb
;

create table diagnoseversie (
  diagnoseversie_id     bigint not null auto_increment,
  diagnoseversie_begindatum datetime,
  diagnoseversie_einddatum datetime,

  constraint pk_diagnoseversie primary key (diagnoseversie_id)
  ) engine=innodb
;

create table diagnoseversie_releasestatus (
  diagnoseversie_id     bigint not null,
  diagnoseversie_releasestatus_datum datetime,
  diagnoseversie_releasestatus_omschrijving text,

  constraint pk_diagnoseversie primary key (diagnoseversie_id)
  ) engine=innodb
;

create table risicofactor (
  risicofactor_id     bigint not null,
  risicofactor_omschrijving	text,

  constraint pk_risicofactor primary key (risicofactor_id)
  ) engine=innodb
;

create table bepalendkenmerk (
  bepalendkenmerk_id     bigint not null,
  bepalendkenmerk_omschrijving text,

  constraint pk_bepalendkenmerk primary key (bepalendkenmerk_id)
  ) engine=innodb
;

create table samenhangendefactor (
  samenhangendefactor_id     bigint not null,
  samenhangendefactor_omschrijving text,

  constraint pk_samenhangendefactor primary key (samenhangendefactor_id)
  ) engine=innodb
;

create table diagnoseversie_bepalendkenmerk (
  bepalendkenmerk_id     bigint,
  diagnoseversie_id bigint,

  constraint pk_diagnoseversie_bepalendkenmerk primary key (bepalendkenmerk_id, diagnoseversie_id)
  ) engine=innodb
;

create table diagnoseversie_risicofactor (
  risicofactor_id     bigint,
  diagnoseversie_id bigint,

  constraint pk_diagnoseversie_risicofactor primary key (risicofactor_id, diagnoseversie_id)
  ) engine=innodb
;

create table diagnoseversie_samenhangendefactor (
  samenhangendefactor_id     bigint,
  diagnoseversie_id bigint,

  constraint pk_diagnoseversie_samenhangendefactor primary key (samenhangendefactor_id, diagnoseversie_id)
  ) engine=innodb
;

alter table diagnoseoverzicht add constraint fk_diagnoseoverzicht_diagnose_1 foreign key (diagnose_id) references diagnose (diagnose_id) on delete restrict on update restrict;
alter table diagnoseoverzicht add constraint fk_diagnoseoverzicht_diagnoseversie_1 foreign key (diagnoseversie_id) references diagnoseversie (diagnoseversie_id) on delete restrict on update restrict;
alter table diagnoseoverzicht add constraint fk_diagnoseoverzicht_gezondheidspatroon_1 foreign key (gezondheidspatroon_id) references gezondheidspatroon (gezondheidspatroon_id) on delete restrict on update restrict;
alter table diagnoseoverzicht add constraint fk_diagnoseoverzicht_diagnoseklasse_1 foreign key (diagnoseklasse_id) references diagnoseklasse (diagnoseklasse_id) on delete restrict on update restrict;
alter table diagnoseversie_releasestatus add constraint fk_diagnoseversie_releasestatus_diagnoseversie_1 foreign key (diagnoseversie_id) references diagnoseversie (diagnoseversie_id) on delete restrict on update restrict;
alter table diagnoseklasse add constraint fk_diagnoseklasse_diagnosedomein_1 foreign key (diagnosedomein_id) references diagnosedomein (diagnosedomein_id) on delete restrict on update restrict;
alter table diagnoseversie_bepalendkenmerk add constraint fk_diagnoseversie_bepalendkenmerk_1 foreign key (bepalendkenmerk_id) references bepalendkenmerk (bepalendkenmerk_id) on delete restrict on update restrict;
alter table diagnoseversie_bepalendkenmerk add constraint fk_diagnoseversie_bepalendkenmerk_2 foreign key (diagnoseversie_id) references diagnoseversie (diagnoseversie_id) on delete restrict on update restrict;
alter table diagnoseversie_risicofactor add constraint fk_diagnoseversie_risicofactor_1 foreign key (risicofactor_id) references risicofactor (risicofactor_id) on delete restrict on update restrict;
alter table diagnoseversie_risicofactor add constraint fk_diagnoseversie_risicofactor_2 foreign key (diagnoseversie_id) references diagnoseversie (diagnoseversie_id) on delete restrict on update restrict;
alter table diagnoseversie_samenhangendefactor add constraint fk_diagnoseversie_samenhangendefactor_1 foreign key (samenhangendefactor_id) references samenhangendefactor (samenhangendefactor_id) on delete restrict on update restrict;
alter table diagnoseversie_samenhangendefactor add constraint fk_diagnoseversie_samenhangendefactor_2 foreign key (diagnoseversie_id) references diagnoseversie (diagnoseversie_id) on delete restrict on update restrict;


# --- !Downs

SET FOREIGN_KEY_CHECKS = 0

drop table if exists diagnoseoverzicht;

drop table if exists diagnose;

drop table if exists company;

drop table if exists gezondheidspatroon;

drop table if exists diagnosedomein;

drop table if exists diagnoseklasse;

drop table if exists diagnoseversie_releasestatus;

drop table if exists diagnoseversie;

drop table if exists risicofactor;

drop table if exists bepalendkenmerk;

drop table if exists samenhangendefactor;

drop table if exists diagnoseversie_bepalendkenmerk;

drop table if exists diagnoseversie_risicofactor;

drop table if exists diagnoseversie_samenhangendefactor;

SET FOREIGN_KEY_CHECKS = 1

