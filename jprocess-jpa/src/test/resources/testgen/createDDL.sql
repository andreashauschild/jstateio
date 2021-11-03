
    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;

    create table PROCESS_INSTANCES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        RESERVATION_ID varchar(255),
        RESERVATION_TIME timestamp,
        processTemplate_id uuid,
        primary key (id)
    );

    create table PROCESS_TEMPLATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        TEMPLATE text not null,
        primary key (id)
    );

    create table STATE_PROPERTIES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        PROP_KEY varchar(255) not null,
        SHA256 varchar(255),
        PROP_VALUE text not null,
        PROP_VALUE_INDEXED varchar(512),
        state_id uuid,
        primary key (id)
    );

    create table STATES (
       id uuid not null,
        CREATED timestamp not null,
        CREATED_BY varchar(255) not null,
        LAST_MODIFIED timestamp not null,
        MODIFIED_BY varchar(255) not null,
        STATE_BEGIN timestamp,
        STATE_END timestamp,
        STATE_NAME varchar(255),
        processInstance_id uuid,
        states_ORDER int4,
        primary key (id)
    );

    alter table if exists PROCESS_INSTANCES 
       add constraint FK5t0f8hb4pr02gc308fg8jxq59 
       foreign key (processTemplate_id) 
       references PROCESS_TEMPLATES;

    alter table if exists STATE_PROPERTIES 
       add constraint FKpseqdqttvoj5qgveryk0nw0j4 
       foreign key (state_id) 
       references STATES;

    alter table if exists STATES 
       add constraint FK7s48whh1risbk8spv7klo25re 
       foreign key (processInstance_id) 
       references PROCESS_INSTANCES;
