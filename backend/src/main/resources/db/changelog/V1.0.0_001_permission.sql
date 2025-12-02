--liquibase formatted sql
--changeset hackyo:V1.0.0_001_permission
CREATE TABLE permission
(
    id                 VARCHAR(100) NOT NULL,
    parent_id          VARCHAR(26)  NOT NULL,
    name               VARCHAR(100) NOT NULL,
    description        VARCHAR(500) DEFAULT NULL,
    basics             BOOLEAN      NOT NULL,
    created_by         VARCHAR(26)  NOT NULL,
    created_date       TIMESTAMP    NOT NULL,
    last_modified_by   VARCHAR(26)  DEFAULT NULL,
    last_modified_date TIMESTAMP    DEFAULT NULL,
    PRIMARY KEY (id)
);

INSERT INTO permission
VALUES ('user', 'root', 'User', 'User', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('user:read', 'user', 'User read', 'Read user', true,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('user:edit', 'user', 'User edit', 'Edit user', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('user:change_password', 'user', 'User change password', 'Change user password', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);

INSERT INTO permission
VALUES ('license', 'root', 'License', 'License', true,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('license:read', 'license', 'License read', 'Read license', true,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);

INSERT INTO permission
VALUES ('org', 'root', 'Organization', 'Organization', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('org:read', 'org', 'Organization read', 'Read organization', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('org:edit', 'org', 'Organization edit', 'Edit organization', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('org:add', 'org', 'Organization add', 'Add organization', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('org:delete', 'org', 'Organization delete', 'Delete organization', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);

INSERT INTO permission
VALUES ('user_management', 'root', 'User management', 'User management', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('user_management:read', 'user_management', 'User read', 'Read user', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('user_management:edit', 'user_management', 'User edit', 'Edit user', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('user_management:add', 'user_management', 'User add', 'Add user', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('user_management:delete', 'user_management', 'User delete', 'Delete user', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);

INSERT INTO permission
VALUES ('role', 'root', 'Role', 'Role', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('role:read', 'role', 'Role read', 'Read role', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('role:edit', 'role', 'Role edit', 'Edit role', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('role:add', 'role', 'Role add', 'Add role', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('role:delete', 'role', 'Role delete', 'Delete role', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);

INSERT INTO permission
VALUES ('sys_config', 'root', 'Sys config', 'Sys config', false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('sys_config:read', 'sys_config', 'Sys config read', 'Read sys config',
        false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
INSERT INTO permission
VALUES ('sys_config:edit', 'sys_config', 'Sys config edit', 'Edit sys config',
        false,
        '01JJK6FQS0K3N6K4JAEAP5ZC7P', '2022-04-01 00:00:00', null, null);
