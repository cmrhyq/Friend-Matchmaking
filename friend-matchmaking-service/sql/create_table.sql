# 数据库初始化
-- 创建库
# create database if not exists my_db;
#
# -- 切换库
# use my_db;

-- 用户表
create table if not exists user
(
    id            bigint auto_increment comment 'id' primary key,
    username      VARCHAR(255)                           NULL COMMENT '用户昵称',
    user_account  VARCHAR(256)                           NULL COMMENT '账号',
    user_avatar   varchar(1024)                          null comment '用户头像',
    user_password VARCHAR(256)                           NOT NULL COMMENT '密码',
    user_status   TINYINT      DEFAULT 0                 NULL COMMENT '是否有效',
    phone         VARCHAR(20)                            NULL COMMENT '手机号',
    email         VARCHAR(50)                            NULL COMMENT '邮箱',
    union_id      varchar(256)                           null comment '微信开放平台id',
    mp_open_id    varchar(256)                           null comment '公众号openId',
    user_profile  varchar(512)                           null comment '用户简介',
    user_role     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint      default 0                 not null comment '是否删除',
    tags          VARCHAR(1024)                          Null COMMENT '标签列表',
    index idx_unionId (union_id)
) comment '用户' collate = utf8mb4_unicode_ci;

CREATE TABLE tag
(
    id          BIGINT auto_increment PRIMARY KEY,
    tag_name    VARCHAR(255)                       NULL COMMENT '标签名称',
    user_id     VARCHAR(256)                       NULL COMMENT '用户 id',
    parent_id   BIGINT                             NULL COMMENT '父标签',
    is_parent   TINYINT                            NULL COMMENT '0-是父标签, 1-不是父标签',
    create_time datetime DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    update_time datetime DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   TINYINT  DEFAULT 0                 NULL COMMENT '是否删除',
    CONSTRAINT unique_index_tag_name UNIQUE (tag_name)
) COMMENT '标签表';

CREATE INDEX idx_user_id ON tag (user_id);