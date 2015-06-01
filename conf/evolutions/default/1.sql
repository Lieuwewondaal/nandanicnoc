# --- First database schema

# --- !Ups

create table diagnose (
  diagnose_id                        bigint not null auto_increment,

  constraint pk_diagnose primary key (diagnose_id)
  ) engine=innodb
;

create table specialisme (
  specialisme_id                        bigint not null auto_increment,

  constraint pk_specialisme primary key (specialisme_id)
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
  diagnosedomein_code   bigint,
  diagnosedomein_omschrijving text,

  constraint pk_diagnosedomein primary key (diagnosedomein_id),
  constraint uc_domein UNIQUE (diagnosedomein_code)
  ) engine=innodb
;

create table diagnoseklasse (
  diagnoseklasse_id		bigint not null auto_increment,
  diagnosedomein_id     bigint,
  diagnoseklasse_code bigint not null,
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

  constraint pk_diagnoseversiereleasestatus primary key (diagnoseversie_id)
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

create table bepalendkenmerk_diagnose (
  bepalendkenmerk_id     								bigint,
  diagnose_id 											bigint,
  diagnose_bepalendkenmerk_releasestatus_datum 			datetime,
  diagnose_bepalendkenmerk_releasestatus_omschrijving	text,

  constraint pk_bepalendkenmerk_diagnose primary key (bepalendkenmerk_id, diagnose_id, diagnose_bepalendkenmerk_releasestatus_datum)
  ) engine=innodb
;

create table risicofactor_diagnose (
  risicofactor_id     									bigint,
  diagnose_id 											bigint,
  diagnose_risicofactor_releasestatus_datum 			datetime,
  diagnose_risicofactor_releasestatus_omschrijving		text,

  constraint pk_risicofactor_diagnose primary key (risicofactor_id, diagnose_id, diagnose_risicofactor_releasestatus_datum)
  ) engine=innodb
;

create table samenhangendefactor_diagnose (
  samenhangendefactor_id     								bigint,
  diagnose_id 												bigint,
  diagnose_samenhangendefactor_releasestatus_datum 			datetime,
  diagnose_samenhangendefactor_releasestatus_omschrijving	text,

  constraint pk_samenhangendefactor_diagnose primary key (samenhangendefactor_id, diagnose_id, diagnose_samenhangendefactor_releasestatus_datum)
  ) engine=innodb
;

create table diagnose_specialisme (
  diagnose_id     bigint,
  specialisme_id bigint,
  diagnose_specialisme_releasestatus_datum datetime,
  diagnose_specialisme_releasestatus_omschrijving text,

  constraint pk_diagnoseversie_samenhangendefactor primary key (diagnose_id, specialisme_id, diagnose_specialisme_releasestatus_datum)
  ) engine=innodb
;

create table noc (
  noc_id                        bigint not null auto_increment,

  constraint pk_noc primary key (noc_id)
  ) engine=innodb
;

create table nocoverzicht (
  noc_id        		       		bigint not null,
  nocversie_id						bigint not null, 
  nocklasse_id  	            	bigint,
  nocoverzicht_code					bigint,
  nocoverzicht_omschrijving 		text,
  nocoverzicht_definitie 			text,
  
  constraint pk_nocoverzicht_noc_fk primary key (noc_id, nocversie_id)
  ) engine=innodb
;

create table waarde (
  waarde_id     bigint not null auto_increment,
  waarde_omschrijving text,

  constraint pk_waarde primary key (waarde_id)
  ) engine=innodb
;

create table noc_waarde (
  noc_id     bigint not null auto_increment,
  waarde_id   bigint,
  noc_waarde_getalwaarde text,

  constraint pk_nocwaarde primary key (noc_id, waarde_id)
  ) engine=innodb
;

create table nocdomein (
  nocdomein_id     bigint not null auto_increment,
  nocdomein_code   bigint,
  nocdomein_omschrijving text,

  constraint pk_nocdomein primary key (nocdomein_id)
  ) engine=innodb
;

create table nocklasse (
  nocklasse_id		bigint not null auto_increment,
  nocdomein_id     bigint,
  nocklasse_code bigint not null,
  nocklasse_omschrijving text,

  constraint pk_nocdomein_fk primary key (nocklasse_id)
  ) engine=innodb
;

create table indicator (
  indicator_id     bigint not null auto_increment,
  indicator_omschrijving text,

  constraint pk_indicator primary key (indicator_id)
  ) engine=innodb
;

create table indicator_waarde (
  indicator_id     bigint not null,
  waarde_id		   bigint not null,
  indicator_waarde_getalwaarde text,
  indicator_waarde_omschrijving text,

  constraint pk_indicator primary key (indicator_id, waarde_id)
  ) engine=innodb
;

create table noc_indicator (
  noc_id		   bigint not null,
  indicator_id     bigint not null,
  noc_indicator_releasestatus_datum datetime,
  noc_indicator_releasestatus_omschrijving text,

  constraint pk_indicator primary key (noc_id, indicator_id)
  ) engine=innodb
;

create table nocversie (
  nocversie_id     bigint not null auto_increment,
  nocversie_begindatum datetime,
  nocversie_einddatum datetime,

  constraint pk_nocversie primary key (nocversie_id)
  ) engine=innodb
;

create table nocversie_releasestatus (
  nocversie_id     bigint not null,
  nocversie_releasestatus_datum datetime,
  nocversie_releasestatus_omschrijving text,

  constraint pk_nocversiereleasestatus primary key (nocversie_id)
  ) engine=innodb
;

create table nocversie_indicator (
  nocversie_id  		  bigint not null,
  indicator_id 	  		  bigint not null,

  constraint pk_nocversieindicator primary key (nocversie_id, indicator_id)
  ) engine=innodb
;

create table noc_indicator_diagnose (
  noc_id		   bigint not null,
  indicator_id     bigint not null,
  diagnose_id	   bigint not null,
  noc_indicator_diagnose_releasestatus_datum datetime,
  noc_indicator_diagnose_releasestatus_omschrijving text,

  constraint pk_indicator primary key (noc_id, indicator_id, diagnose_id)
  ) engine=innodb
;

create table nic (
  nic_id                        bigint not null auto_increment,

  constraint pk_nic primary key (nic_id)
  ) engine=innodb
;

create table nicoverzicht (
  nic_id        		       		bigint not null,
  nicversie_id						bigint not null, 
  nicklasse_id  	            	bigint,
  nicoverzicht_code					bigint,
  nicoverzicht_omschrijving 		text,
  nicoverzicht_definitie 			text,
  
  constraint pk_nicoverzicht_nic_fk primary key (nic_id, nicversie_id)
  ) engine=innodb
;

create table nicdomein (
  nicdomein_id     bigint not null auto_increment,
  nicdomein_code   bigint,
  nicdomein_omschrijving text,

  constraint pk_nicdomein primary key (nicdomein_id)
  ) engine=innodb
;

create table nicklasse (
  nicklasse_id		bigint not null auto_increment,
  nicdomein_id     bigint,
  nicklasse_code bigint not null,
  nicklasse_omschrijving text,

  constraint pk_nicdomein_fk primary key (nicklasse_id)
  ) engine=innodb
;

create table nicversie (
  nicversie_id     bigint not null auto_increment,
  nicversie_begindatum datetime,
  nicversie_einddatum datetime,

  constraint pk_nicversie primary key (nicversie_id)
  ) engine=innodb
;

create table nicversie_releasestatus (
  nicversie_id     bigint not null,
  nicversie_releasestatus_datum datetime,
  nicversie_releasestatus_omschrijving text,

  constraint pk_nicversiereleasestatus primary key (nicversie_id)
  ) engine=innodb
;

create table nicactiviteit (
  nicactiviteit_id     bigint not null,
  nicactiviteit_omschrijving text,

  constraint pk_nicactiviteit primary key (nicactiviteit_id)
  ) engine=innodb
;

create table nicversie_nicactiviteit (
  nicactiviteit_id     bigint,
  nicversie_id bigint,

  constraint pk_nicversie_nicactiviteit primary key (nicactiviteit_id, nicversie_id)
  ) engine=innodb
;

create table nic_nicactiviteit (
  nic_id     bigint,
  nicactiviteit_id bigint,
  nic_nicactiviteit_releasestatus_datum datetime,
  nic_nicactiviteit_releasestatus_omschrijving text,
  
  constraint pk_nic_nicactiviteit primary key (nicactiviteit_id, nic_id)
  ) engine=innodb
;

create table nic_diagnose (
  nic_id     bigint,
  nicactiviteit_id bigint,
  diagnose_id bigint,
  nic_diagnose_releasestatus_datum datetime,
  nic_diagnose_releasestatus_omschrijving text,
  
  constraint pk_nic_nicactiviteit primary key (nicactiviteit_id, nic_id, diagnose_id)
  ) engine=innodb
;

create table casus (
  casus_id                        bigint not null auto_increment,
  casus_omschrijving	          text,
  casus_definitie				  text,
  casus_begindatum				  datetime,
  casus_einddatum				  datetime,

  constraint pk_casus primary key (casus_id)
  ) engine=innodb
;

create table casus_diagnose (
  casus_diagnose_id				  bigint not null auto_increment,
  casus_id                        bigint not null,
  diagnose_id                     bigint not null,
  user_id						  bigint,

  constraint pk_casus_diagnose primary key (casus_diagnose_id)
  ) engine=innodb
;

create table casus_bepalendkenmerk (
  casus_diagnose_id				  bigint not null,
  bepalendkenmerk_id              bigint not null,

  constraint pk_casus_bepalendkenmerk primary key (casus_diagnose_id, bepalendkenmerk_id)
  ) engine=innodb
;

create table casus_risicofactor (
  casus_diagnose_id				 bigint not null,
  risicofactor_id                bigint not null,

  constraint pk_casus_risicofactor primary key (casus_diagnose_id, risicofactor_id)
  ) engine=innodb
;

create table casus_samenhangendefactor (
  casus_diagnose_id				  bigint not null,
  samenhangendefactor_id          bigint not null,

  constraint pk_casus_samenhangendefactor primary key (casus_diagnose_id, samenhangendefactor_id)
  ) engine=innodb
;

create table casus_nic (
  casus_diagnose_id				  bigint not null,
  nic_id				          bigint not null,
  nicactiviteit_id				  bigint not null,
  
  constraint pk_casus_nic primary key (casus_diagnose_id, nic_id, nicactiviteit_id)
  ) engine=innodb
;

create table casus_noc (
  casus_diagnose_id				  bigint not null,
  noc_id				          bigint not null,
  indicator_id				      bigint not null,

  constraint pk_casus_noc primary key (casus_diagnose_id, noc_id, indicator_id)
  ) engine=innodb
;

create table casusopmerkingen (
  casus_diagnose_id				  bigint not null,
  casusopmerking         		  text,
  casusopmerkingdatum			  datetime,

  constraint pk_casus_samenhangendefactor primary key (casus_diagnose_id)
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
alter table bepalendkenmerk_diagnose add constraint fk_bepalendkenmerk_diagnose_1 foreign key (bepalendkenmerk_id) references bepalendkenmerk (bepalendkenmerk_id) on delete restrict on update restrict;
alter table bepalendkenmerk_diagnose add constraint fk_bepalendkenmerk_diagnose_2 foreign key (diagnose_id) references diagnose (diagnose_id) on delete restrict on update restrict;
alter table risicofactor_diagnose add constraint fk_risicofactor_diagnose_1 foreign key (risicofactor_id) references risicofactor (risicofactor_id) on delete restrict on update restrict;
alter table risicofactor_diagnose add constraint fk_risicofactor_diagnose_2 foreign key (diagnose_id) references diagnose (diagnose_id) on delete restrict on update restrict;
alter table samenhangendefactor_diagnose add constraint fk_samenhangendefactor_diagnose_1 foreign key (samenhangendefactor_id) references samenhangendefactor (samenhangendefactor_id) on delete restrict on update restrict;
alter table samenhangendefactor_diagnose add constraint fk_samenhangendefactor_diagnose_2 foreign key (diagnose_id) references diagnose (diagnose_id) on delete restrict on update restrict;
alter table diagnose_specialisme add constraint fk_diagnose_specialisme_1 foreign key (diagnose_id) references diagnose (diagnose_id) on delete restrict on update restrict;
alter table diagnose_specialisme add constraint fk_diagnose_specialisme_2 foreign key (specialisme_id) references specialisme (specialisme_id) on delete restrict on update restrict;
alter table nocversie_releasestatus add constraint fk_nocversie_releasestatus_1 foreign key (nocversie_id) references nocversie (nocversie_id) on delete restrict on update restrict;
alter table nocversie_indicator add constraint fk_nocversie_indicator_1 foreign key (nocversie_id) references nocversie (nocversie_id) on delete restrict on update restrict;
alter table nocversie_indicator add constraint fk_nocversie_indicator_2 foreign key (indicator_id) references indicator (indicator_id) on delete restrict on update restrict;
alter table nocklasse add constraint fk_nocklasse_nocdomein_1 foreign key (nocdomein_id) references nocdomein (nocdomein_id) on delete restrict on update restrict;
alter table nocoverzicht add constraint fk_nocoverzicht_noc_1 foreign key (noc_id) references noc (noc_id) on delete restrict on update restrict;
alter table nocoverzicht add constraint fk_nocoverzicht_nocversie_1 foreign key (nocversie_id) references nocversie (nocversie_id) on delete restrict on update restrict;
alter table nocoverzicht add constraint fk_nocoverzicht_nocklasse_1 foreign key (nocklasse_id) references nocklasse (nocklasse_id) on delete restrict on update restrict;
alter table noc_waarde add constraint fk_noc_waarde_1 foreign key (noc_id) references noc (noc_id) on delete restrict on update restrict;
alter table noc_waarde add constraint fk_noc_waarde_2 foreign key (waarde_id) references waarde (waarde_id) on delete restrict on update restrict;
alter table noc_indicator add constraint fk_noc_indicator_1 foreign key (noc_id) references noc (noc_id) on delete restrict on update restrict;
alter table noc_indicator add constraint fk_noc_indicator_2 foreign key (indicator_id) references indicator (indicator_id) on delete restrict on update restrict;
alter table indicator_waarde add constraint fk_indicator_waarde_1 foreign key (indicator_id) references indicator (indicator_id) on delete restrict on update restrict;
alter table indicator_waarde add constraint fk_indicator_waarde_2 foreign key (waarde_id) references waarde (waarde_id) on delete restrict on update restrict;
alter table noc_indicator_diagnose add constraint fk_noc_indicator_diagnose_1 foreign key (noc_id) references noc_indicator (noc_id) on delete restrict on update restrict;
alter table noc_indicator_diagnose add constraint fk_noc_indicator_diagnose_2 foreign key (indicator_id) references noc_indicator (indicator_id) on delete restrict on update restrict;
alter table noc_indicator_diagnose add constraint fk_noc_indicator_diagnose_3 foreign key (diagnose_id) references diagnose (diagnose_id) on delete restrict on update restrict;
alter table nicversie_releasestatus add constraint fk_nicversie_releasestatus_1 foreign key (nicversie_id) references nicversie (nicversie_id) on delete restrict on update restrict;
alter table nicversie_nicactiviteit add constraint fk_nicversie_nicactiviteit_1 foreign key (nicversie_id) references nicversie (nicversie_id) on delete restrict on update restrict;
alter table nicversie_nicactiviteit add constraint fk_nicversie_nicactiviteit_2 foreign key (nicactiviteit_id) references nicactiviteit (nicactiviteit_id) on delete restrict on update restrict;
alter table nicklasse add constraint fk_nicklasse_nicdomein_1 foreign key (nicdomein_id) references nicdomein (nicdomein_id) on delete restrict on update restrict;
alter table nicoverzicht add constraint fk_nicoverzicht_nic_1 foreign key (nic_id) references nic (nic_id) on delete restrict on update restrict;
alter table nicoverzicht add constraint fk_nicoverzicht_nicversie_1 foreign key (nicversie_id) references nicversie (nicversie_id) on delete restrict on update restrict;
alter table nicoverzicht add constraint fk_nicoverzicht_nicklasse_1 foreign key (nicklasse_id) references nicklasse (nicklasse_id) on delete restrict on update restrict;
alter table nic_nicactiviteit add constraint fk_nic_nicactiviteit_1 foreign key (nic_id) references nic (nic_id) on delete restrict on update restrict;
alter table nic_nicactiviteit add constraint fk_nic_nicactiviteit_2 foreign key (nicactiviteit_id) references nicactiviteit (nicactiviteit_id) on delete restrict on update restrict;
alter table nic_diagnose add constraint fk_nic_nic_diagnose_1 foreign key (nic_id) references nic_nicactiviteit (nic_id) on delete restrict on update restrict;
alter table nic_diagnose add constraint fk_nic_nic_diagnose_2 foreign key (nicactiviteit_id) references nic_nicactiviteit (nicactiviteit_id) on delete restrict on update restrict;
alter table nic_diagnose add constraint fk_nic_nic_diagnose_3 foreign key (diagnose_id) references diagnose (diagnose_id) on delete restrict on update restrict;
alter table casus_diagnose add constraint fk_casus_diagnose_1 foreign key (casus_id) references casus (casus_id) on delete restrict on update restrict;
alter table casus_diagnose add constraint fk_casus_diagnose_2 foreign key (diagnose_id) references diagnose (diagnose_id) on delete restrict on update restrict;
alter table casus_bepalendkenmerk add constraint fk_casus_bepalendkenmerk_1 foreign key (casus_diagnose_id) references casus_diagnose (casus_diagnose_id) on delete restrict on update restrict;
alter table casus_bepalendkenmerk add constraint fk_casus_bepalendkenmerk_2 foreign key (bepalendkenmerk_id) references bepalendkenmerk (bepalendkenmerk_id) on delete restrict on update restrict;
alter table casus_risicofactor add constraint fk_casus_risicofactor_1 foreign key (casus_diagnose_id) references casus_diagnose (casus_diagnose_id) on delete restrict on update restrict;
alter table casus_risicofactor add constraint fk_casus_risicofactor_2 foreign key (risicofactor_id) references risicofactor (risicofactor_id) on delete restrict on update restrict;
alter table casus_samenhangendefactor add constraint fk_casus_samenhangendefactor_1 foreign key (casus_diagnose_id) references casus_diagnose (casus_diagnose_id) on delete restrict on update restrict;
alter table casus_samenhangendefactor add constraint fk_casus_samenhangendefactor_2 foreign key (samenhangendefactor_id) references samenhangendefactor (samenhangendefactor_id) on delete restrict on update restrict;
alter table casus_nic add constraint fk_casus_nic_1 foreign key (casus_diagnose_id) references casus_diagnose (casus_diagnose_id) on delete restrict on update restrict;
alter table casus_nic add constraint fk_casus_nic_2 foreign key (nic_id) references nic (nic_id) on delete restrict on update restrict;
alter table casus_nic add constraint fk_casus_nic_3 foreign key (nicactiviteit_id) references nicactiviteit (nicactiviteit_id) on delete restrict on update restrict;
alter table casus_noc add constraint fk_casus_noc_1 foreign key (casus_diagnose_id) references casus_diagnose (casus_diagnose_id) on delete restrict on update restrict;
alter table casus_noc add constraint fk_casus_noc_2 foreign key (noc_id) references noc (noc_id) on delete restrict on update restrict;
alter table casus_noc add constraint fk_casus_noc_3 foreign key (indicator_id) references indicator (indicator_id) on delete restrict on update restrict;
alter table casusopmerkingen add constraint fk_casus_opmerkingen_1 foreign key (casus_diagnose_id) references casus_diagnose (casus_diagnose_id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS = 0;

drop table if exists specialisme;

drop table if exists diagnoseoverzicht;

drop table if exists diagnose;

drop table if exists diagnose_specialisme;

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

drop table if exists bepalendkenmerk_diagnose;

drop table if exists risicofactor_diagnose;

drop table if exists samenhangendefactor_diagnose;

drop table if exists noc;

drop table if exists nocoverzicht;

drop table if exists waarde;

drop table if exists noc_waarde;

drop table if exists nocdomein;

drop table if exists nocklasse;

drop table if exists indicator;

drop table if exists indicator_waarde;

drop table if exists noc_indicator;

drop table if exists nocversie;

drop table if exists nocversie_releasestatus;

drop table if exists nocversie_indicator;

drop table if exists noc_indicator_diagnose;

drop table if exists nic;

drop table if exists nicactiviteit;

drop table if exists nicdomein;

drop table if exists nicklasse;

drop table if exists nicoverzicht;

drop table if exists nicversie;

drop table if exists nicversie_nicactiviteit;

drop table if exists nicversie_releasestatus;

drop table if exists nic_nicactiviteit;

drop table if exists nic_diagnose;

drop table if exists casus;

drop table if exists casus_diagnose;

drop table if exists casus_bepalendkenmerk;

drop table if exists casus_risicofactor;

drop table if exists casus_samenhangendefactor;

drop table if exists casus_nic;

drop table if exists casus_noc;

drop table if exists casusopmerkingen;

SET FOREIGN_KEY_CHECKS = 1;

