--liquibase formatted sql
--changeset hackyo:V20220401_006_sys_config
CREATE TABLE sys_config
(
    id                 VARCHAR(26)  NOT NULL,
    config_key         VARCHAR(100) NOT NULL,
    config_value       VARCHAR(500) NOT NULL,
    description        VARCHAR(500) DEFAULT NULL,
    created_by         VARCHAR(26)  NOT NULL,
    created_date       TIMESTAMP    NOT NULL,
    last_modified_by   VARCHAR(26)  DEFAULT NULL,
    last_modified_date TIMESTAMP    DEFAULT NULL,
    PRIMARY KEY (id)
);
