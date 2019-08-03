CREATE DATABASE bdAnteproyecto;

drop table if exists USUARIOS_ANTEPROYECTO;
/* table : anteproyecto */
create table ANTEPROYECTO
(
   ID_ANTEPROYECTO      varchar(30) not null,
   TITULO               varchar(50) not null,
   MODALIDAD            varchar(10) not null,
   ESTADO               varchar(30) default 'no aprobado',
   FECHA_REGISTRO        varchar(20) not null,
   FECHAA_PROBACION      varchar(20),
   primary key (ID_ANTEPROYECTO)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   ID_USUARIO           varchar(30) not null,
   ROL                  numeric(8,0) not null,
   NOMBRE_APELLIDO      varchar(50) not null,                
   USER                 varchar(20) not null,
   PASSWORD             varchar(20) not null,
   primary key (ID_USUARIO)
);

/*==============================================================*/
/* Table: USUARIOS_ANTEPROYECTO                                 */
/*==============================================================*/
create table USUARIOS_ANTEPROYECTO
(
   ID_ANTEPROYECTO      varchar(30) not null,
   ID_USUARIO           varchar(30) not null,
   ROL_ANTEPROYECTO     numeric(8,0) not null,
   CONCEPTO             numeric(8,0),
   FECHA_REVISION       varchar(20),
   primary key (ID_ANTEPROYECTO,ID_USUARIO)
);

alter table USUARIOS_ANTEPROYECTO add constraint FK_USUARIOS_USUARIOS__ANTEPROY foreign key (ID_ANTEPROYECTO)
      references ANTEPROYECTO (ID_ANTEPROYECTO) on delete restrict on update restrict;

alter table USUARIOS_ANTEPROYECTO add constraint FK_USUARIOS_USUARIOS__USUARIO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO) on delete restrict on update restrict;

