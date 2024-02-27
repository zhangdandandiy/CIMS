/*
 Navicat Premium Data Transfer

 Source Server         : LocalHost_SQlServer
 Source Server Type    : SQL Server
 Source Server Version : 15002101
 Source Catalog        : ruoyi
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 15002101
 File Encoding         : 65001

 Date: 18/07/2023 14:57:02
*/


-- ----------------------------
-- 1、代码生成业务表 gen_table
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[gen_table]') AND type IN ('U'))
    DROP TABLE [dbo].[gen_table]
GO

CREATE TABLE [dbo].[gen_table] (
                                   [table_id] bigint  IDENTITY(1,1) NOT NULL,
                                   [table_name] varchar(200) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
                                   [table_comment] varchar(500) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
                                   [sub_table_name] varchar(64) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                   [sub_table_fk_name] varchar(64) COLLATE Chinese_PRC_CI_AS  NULL,
                                   [class_name] varchar(100) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
                                   [tpl_category] varchar(200) COLLATE Chinese_PRC_CI_AS DEFAULT ('crud') NULL,
                                   [package_name] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                   [module_name] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                   [business_name] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                   [function_name] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                   [function_author] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                   [gen_type] char(1) COLLATE Chinese_PRC_CI_AS DEFAULT ('0') NULL,
                                   [gen_path] varchar(200) COLLATE Chinese_PRC_CI_AS DEFAULT ('/') NULL,
                                   [options] varchar(1000) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                   [create_by] varchar(64) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
                                   [create_time] datetime DEFAULT NULL NULL,
                                   [update_by] varchar(64) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
                                   [update_time] datetime DEFAULT NULL NULL,
                                   [remark] varchar(500) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL
)
GO

ALTER TABLE [dbo].[gen_table] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
     'MS_Description', N'编号',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'table_id'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'表名称',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'table_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'表描述',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'table_comment'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'关联子表的表名',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'sub_table_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'子表关联的外键名',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'sub_table_fk_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'实体类名称',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'class_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'使用的模板（crud单表操作 tree树表操作）',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'tpl_category'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'生成包路径',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'package_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'生成模块名',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'module_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'生成业务名',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'business_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'生成功能名',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'function_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'生成功能作者',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'function_author'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'生成代码方式（0zip压缩包 1自定义路径）',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'gen_type'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'生成路径（不填默认项目路径）',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'gen_path'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'其它生成选项',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'options'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'创建者',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'create_by'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'创建时间',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'更新者',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'update_by'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'更新时间',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'备注',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table',
     'COLUMN', N'remark'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'代码生成业务表',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table'
GO


-- ----------------------------
-- Records of gen_table
-- ----------------------------
SET IDENTITY_INSERT [dbo].[gen_table] ON
GO

INSERT INTO [dbo].[gen_table] ([table_id], [table_name], [table_comment], [sub_table_name], [sub_table_fk_name], [class_name], [tpl_category], [package_name], [module_name], [business_name], [function_name], [function_author], [gen_type], [gen_path], [options], [create_by], [create_time], [update_by], [update_time], [remark]) VALUES (N'1', N'sys_user_post', N'用户与岗位关联表', NULL, NULL, N'SysUserPost', N'crud', N'com.ruoyi.system', N'system', N'post', N'用户与岗位关联', N'ruoyi', N'0', N'/', NULL, N'admin', N'2023-07-18 14:38:26.820', N'', NULL, NULL)
GO

SET IDENTITY_INSERT [dbo].[gen_table] OFF
GO


-- ----------------------------
-- 2、 代码生成业务表字段 gen_table_column
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[gen_table_column]') AND type IN ('U'))
    DROP TABLE [dbo].[gen_table_column]
GO

CREATE TABLE [dbo].[gen_table_column] (
                                          [column_id] bigint  IDENTITY(1,1) NOT NULL,
                                          [table_id] varchar(64) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [column_name] varchar(200) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [column_comment] varchar(100) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [column_type] varchar(100) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [java_type] varchar(500) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [java_field] varchar(200) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [is_pk] char(1) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [is_increment] char(1) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [is_required] char(1) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [is_insert] char(1) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [is_edit] char(1) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [is_list] char(1) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [is_query] char(1) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [query_type] varchar(200) COLLATE Chinese_PRC_CI_AS DEFAULT ('EQ') NULL,
                                          [html_type] varchar(200) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [dict_type] varchar(200) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
                                          [sort] int DEFAULT NULL NULL,
                                          [create_by] varchar(64) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
                                          [create_time] datetime DEFAULT NULL NULL,
                                          [update_by] varchar(64) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
                                          [update_time] datetime DEFAULT NULL NULL
)
GO

ALTER TABLE [dbo].[gen_table_column] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
     'MS_Description', N'编号',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'column_id'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'归属表编号',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'table_id'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'列名称',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'column_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'列描述',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'column_comment'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'列类型',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'column_type'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'JAVA类型',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'java_type'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'JAVA字段名',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'java_field'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'是否主键（1是）',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'is_pk'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'是否自增（1是）',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'is_increment'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'是否必填（1是）',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'is_required'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'是否为插入字段（1是）',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'is_insert'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'是否编辑字段（1是）',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'is_edit'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'是否列表字段（1是）',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'is_list'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'是否查询字段（1是）',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'is_query'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'查询方式（等于、不等于、大于、小于、范围）',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'query_type'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'html_type'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'字典类型',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'dict_type'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'排序',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'sort'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'创建者',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'create_by'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'创建时间',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'更新者',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'update_by'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'更新时间',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column',
     'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'代码生成业务表字段',
     'SCHEMA', N'dbo',
     'TABLE', N'gen_table_column'
GO


-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
SET IDENTITY_INSERT [dbo].[gen_table_column] ON
GO

INSERT INTO [dbo].[gen_table_column] ([column_id], [table_id], [column_name], [column_comment], [column_type], [java_type], [java_field], [is_pk], [is_increment], [is_required], [is_insert], [is_edit], [is_list], [is_query], [query_type], [html_type], [dict_type], [sort], [create_by], [create_time], [update_by], [update_time]) VALUES (N'1', N'1', N'user_id', N'用户ID', N'bigint', N'Long', N'userId', N'1', NULL, NULL, N'1', NULL, NULL, NULL, N'EQ', N'input', N'', N'1', N'admin', N'2023-07-18 14:38:27.117', N'', NULL)
GO

INSERT INTO [dbo].[gen_table_column] ([column_id], [table_id], [column_name], [column_comment], [column_type], [java_type], [java_field], [is_pk], [is_increment], [is_required], [is_insert], [is_edit], [is_list], [is_query], [query_type], [html_type], [dict_type], [sort], [create_by], [create_time], [update_by], [update_time]) VALUES (N'2', N'1', N'post_id', N'岗位ID', N'bigint', N'Long', N'postId', N'1', NULL, NULL, N'1', NULL, NULL, NULL, N'EQ', N'input', N'', N'2', N'admin', N'2023-07-18 14:38:27.120', N'', NULL)
GO

SET IDENTITY_INSERT [dbo].[gen_table_column] OFF
GO


-- ----------------------------
-- 3、Blob类型的触发器表 qrtz_blob_triggers
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[qrtz_blob_triggers]') AND type IN ('U'))
    DROP TABLE [dbo].[qrtz_blob_triggers]
GO

CREATE TABLE [dbo].[qrtz_blob_triggers] (
                                            [sched_name] varchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                            [trigger_name] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                            [trigger_group] varchar(2200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                            [blob_data] varbinary(max) DEFAULT NULL NULL
)
GO

ALTER TABLE [dbo].[qrtz_blob_triggers] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
     'MS_Description', N'调度名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_blob_triggers',
     'COLUMN', N'sched_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'qrtz_triggers表trigger_name的外键',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_blob_triggers',
     'COLUMN', N'trigger_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'qrtz_triggers表trigger_group的外键',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_blob_triggers',
     'COLUMN', N'trigger_group'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'存放持久化Trigger对象',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_blob_triggers',
     'COLUMN', N'blob_data'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'Blob类型的触发器表',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_blob_triggers'
GO


-- ----------------------------
-- 4、日历信息表 qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[qrtz_calendars]') AND type IN ('U'))
    DROP TABLE [dbo].[qrtz_calendars]
GO

CREATE TABLE [dbo].[qrtz_calendars] (
                                        [sched_name] varchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                        [calendar_name] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                        [calendar] varbinary(max)  NOT NULL
)
GO

ALTER TABLE [dbo].[qrtz_calendars] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
     'MS_Description', N'调度名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_calendars',
     'COLUMN', N'sched_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'日历名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_calendars',
     'COLUMN', N'calendar_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'存放持久化calendar对象',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_calendars',
     'COLUMN', N'calendar'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'日历信息表',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_calendars'
GO


-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- 5、Cron类型的触发器表 qrtz_cron_triggers
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[qrtz_cron_triggers]') AND type IN ('U'))
    DROP TABLE [dbo].[qrtz_cron_triggers]
GO

CREATE TABLE [dbo].[qrtz_cron_triggers] (
                                            [sched_name] varchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                            [trigger_name] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                            [trigger_group] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                            [cron_expression] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                            [time_zone_id] varchar(80) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL
)
GO

ALTER TABLE [dbo].[qrtz_cron_triggers] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
     'MS_Description', N'调度名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_cron_triggers',
     'COLUMN', N'sched_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'qrtz_triggers表trigger_name的外键',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_cron_triggers',
     'COLUMN', N'trigger_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'qrtz_triggers表trigger_group的外键',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_cron_triggers',
     'COLUMN', N'trigger_group'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'cron表达式',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_cron_triggers',
     'COLUMN', N'cron_expression'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'时区',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_cron_triggers',
     'COLUMN', N'time_zone_id'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'Cron类型的触发器表',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_cron_triggers'
GO


-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO [dbo].[qrtz_cron_triggers] ([sched_name], [trigger_name], [trigger_group], [cron_expression], [time_zone_id]) VALUES (N'RuoyiScheduler', N'TASK_CLASS_NAME1', N'DEFAULT', N'0/10 * * * * ?', N'Asia/Shanghai')
GO

INSERT INTO [dbo].[qrtz_cron_triggers] ([sched_name], [trigger_name], [trigger_group], [cron_expression], [time_zone_id]) VALUES (N'RuoyiScheduler', N'TASK_CLASS_NAME2', N'DEFAULT', N'0/15 * * * * ?', N'Asia/Shanghai')
GO

INSERT INTO [dbo].[qrtz_cron_triggers] ([sched_name], [trigger_name], [trigger_group], [cron_expression], [time_zone_id]) VALUES (N'RuoyiScheduler', N'TASK_CLASS_NAME3', N'DEFAULT', N'0/20 * * * * ?', N'Asia/Shanghai')
GO


-- ----------------------------
-- 6、已触发的触发器表 qrtz_fired_triggers
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[qrtz_fired_triggers]') AND type IN ('U'))
    DROP TABLE [dbo].[qrtz_fired_triggers]
GO

CREATE TABLE [dbo].[qrtz_fired_triggers] (
                                             [sched_name] varchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                             [entry_id] varchar(95) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                             [trigger_name] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                             [trigger_group] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                             [instance_name] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                             [fired_time] bigint  NOT NULL,
                                             [sched_time] bigint  NOT NULL,
                                             [priority] int  NOT NULL,
                                             [state] varchar(16) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                             [job_name] varchar(200) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                             [job_group] varchar(200) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                             [is_nonconcurrent] varchar(1) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                             [requests_recovery] varchar(1) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL
)
GO

ALTER TABLE [dbo].[qrtz_fired_triggers] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
     'MS_Description', N'调度名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_fired_triggers',
     'COLUMN', N'sched_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'调度器实例id',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_fired_triggers',
     'COLUMN', N'entry_id'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'qrtz_triggers表trigger_name的外键',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_fired_triggers',
     'COLUMN', N'trigger_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'qrtz_triggers表trigger_group的外键',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_fired_triggers',
     'COLUMN', N'trigger_group'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'调度器实例名',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_fired_triggers',
     'COLUMN', N'instance_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'触发的时间',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_fired_triggers',
     'COLUMN', N'fired_time'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'定时器制定的时间',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_fired_triggers',
     'COLUMN', N'sched_time'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'优先级',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_fired_triggers',
     'COLUMN', N'priority'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'状态',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_fired_triggers',
     'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'任务名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_fired_triggers',
     'COLUMN', N'job_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'任务组名',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_fired_triggers',
     'COLUMN', N'job_group'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'是否并发',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_fired_triggers',
     'COLUMN', N'is_nonconcurrent'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'是否接受恢复执行',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_fired_triggers',
     'COLUMN', N'requests_recovery'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'已触发的触发器表',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_fired_triggers'
GO


-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- 7、任务详细信息表 qrtz_job_details
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[qrtz_job_details]') AND type IN ('U'))
    DROP TABLE [dbo].[qrtz_job_details]
GO

CREATE TABLE [dbo].[qrtz_job_details] (
                                          [sched_name] varchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                          [job_name] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                          [job_group] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                          [description] nvarchar(250) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                          [job_class_name] varchar(250) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                          [is_durable] varchar(1) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                          [is_nonconcurrent] varchar(1) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                          [is_update_data] varchar(1) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                          [requests_recovery] varchar(1) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                          [job_data] varbinary(max)  NULL
)
GO

ALTER TABLE [dbo].[qrtz_job_details] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
     'MS_Description', N'调度名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_job_details',
     'COLUMN', N'sched_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'任务名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_job_details',
     'COLUMN', N'job_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'任务组名',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_job_details',
     'COLUMN', N'job_group'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'相关介绍',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_job_details',
     'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'执行任务类名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_job_details',
     'COLUMN', N'job_class_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'是否持久化',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_job_details',
     'COLUMN', N'is_durable'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'是否并发',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_job_details',
     'COLUMN', N'is_nonconcurrent'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'是否更新数据',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_job_details',
     'COLUMN', N'is_update_data'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'是否接受恢复执行',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_job_details',
     'COLUMN', N'requests_recovery'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'存放持久化job对象',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_job_details',
     'COLUMN', N'job_data'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'任务详细信息表',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_job_details'
GO


-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO [dbo].[qrtz_job_details] ([sched_name], [job_name], [job_group], [description], [job_class_name], [is_durable], [is_nonconcurrent], [is_update_data], [requests_recovery], [job_data]) VALUES (N'RuoyiScheduler', N'TASK_CLASS_NAME1', N'DEFAULT', NULL, N'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', N'0', N'1', N'0', N'0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001891E8161D87870707070707400013174000E302F3130202A202A202A202A203F74001172795461736B2E72794E6F506172616D7374000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC8974000133740001317800)
GO

INSERT INTO [dbo].[qrtz_job_details] ([sched_name], [job_name], [job_group], [description], [job_class_name], [is_durable], [is_nonconcurrent], [is_update_data], [requests_recovery], [job_data]) VALUES (N'RuoyiScheduler', N'TASK_CLASS_NAME2', N'DEFAULT', NULL, N'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', N'0', N'1', N'0', N'0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001891E8165C07870707070707400013174000E302F3135202A202A202A202A203F74001572795461736B2E7279506172616D7328277279272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000002740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC8974000133740001317800)
GO

INSERT INTO [dbo].[qrtz_job_details] ([sched_name], [job_name], [job_group], [description], [job_class_name], [is_durable], [is_nonconcurrent], [is_update_data], [requests_recovery], [job_data]) VALUES (N'RuoyiScheduler', N'TASK_CLASS_NAME3', N'DEFAULT', NULL, N'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', N'0', N'1', N'0', N'0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F504552544945537372001E636F6D2E72756F79692E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720027636F6D2E72756F79692E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001891E8165C07870707070707400013174000E302F3230202A202A202A202A203F74003872795461736B2E72794D756C7469706C65506172616D7328277279272C20747275652C20323030304C2C203331362E3530442C203130302974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000003740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E5A49AE58F82EFBC8974000133740001317800)
GO


-- ----------------------------
-- 8、存储的悲观锁信息表 qrtz_locks
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[qrtz_locks]') AND type IN ('U'))
    DROP TABLE [dbo].[qrtz_locks]
GO

CREATE TABLE [dbo].[qrtz_locks] (
                                    [sched_name] varchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                    [lock_name] varchar(40) COLLATE Chinese_PRC_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[qrtz_locks] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
     'MS_Description', N'调度名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_locks',
     'COLUMN', N'sched_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'悲观锁名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_locks',
     'COLUMN', N'lock_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'存储的悲观锁信息表',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_locks'
GO


-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO [dbo].[qrtz_locks] ([sched_name], [lock_name]) VALUES (N'RuoyiScheduler', N'STATE_ACCESS')
GO

INSERT INTO [dbo].[qrtz_locks] ([sched_name], [lock_name]) VALUES (N'RuoyiScheduler', N'TRIGGER_ACCESS')
GO


-- ----------------------------
-- 9、暂停的触发器表 qrtz_paused_trigger_grps
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[qrtz_paused_trigger_grps]') AND type IN ('U'))
    DROP TABLE [dbo].[qrtz_paused_trigger_grps]
GO

CREATE TABLE [dbo].[qrtz_paused_trigger_grps] (
                                                  [sched_name] varchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                                  [trigger_group] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[qrtz_paused_trigger_grps] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
     'MS_Description', N'调度名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_paused_trigger_grps',
     'COLUMN', N'sched_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'qrtz_triggers表trigger_group的外键',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_paused_trigger_grps',
     'COLUMN', N'trigger_group'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'暂停的触发器表',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_paused_trigger_grps'
GO


-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- 10、调度器状态表 qrtz_scheduler_state
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[qrtz_scheduler_state]') AND type IN ('U'))
    DROP TABLE [dbo].[qrtz_scheduler_state]
GO

CREATE TABLE [dbo].[qrtz_scheduler_state] (
                                              [sched_name] varchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                              [instance_name] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                              [last_checkin_time] bigint  NOT NULL,
                                              [checkin_interval] bigint  NOT NULL
)
GO

ALTER TABLE [dbo].[qrtz_scheduler_state] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
     'MS_Description', N'调度名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_scheduler_state',
     'COLUMN', N'sched_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'实例名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_scheduler_state',
     'COLUMN', N'instance_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'上次检查时间',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_scheduler_state',
     'COLUMN', N'last_checkin_time'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'检查间隔时间',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_scheduler_state',
     'COLUMN', N'checkin_interval'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'调度器状态表',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_scheduler_state'
GO


-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO [dbo].[qrtz_scheduler_state] ([sched_name], [instance_name], [last_checkin_time], [checkin_interval]) VALUES (N'RuoyiScheduler', N'DESKTOP-9R6POT21689660014897', N'1689663424198', N'15000')
GO


-- ----------------------------
-- 11、简单触发器的信息表 qrtz_simple_triggers
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[qrtz_simple_triggers]') AND type IN ('U'))
    DROP TABLE [dbo].[qrtz_simple_triggers]
GO

CREATE TABLE [dbo].[qrtz_simple_triggers] (
                                              [sched_name] varchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                              [trigger_name] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                              [trigger_group] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                              [repeat_count] bigint  NOT NULL,
                                              [repeat_interval] bigint  NOT NULL,
                                              [times_triggered] bigint  NOT NULL
)
GO

ALTER TABLE [dbo].[qrtz_simple_triggers] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
     'MS_Description', N'调度名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simple_triggers',
     'COLUMN', N'sched_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'qrtz_triggers表trigger_name的外键',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simple_triggers',
     'COLUMN', N'trigger_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'qrtz_triggers表trigger_group的外键',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simple_triggers',
     'COLUMN', N'trigger_group'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'重复的次数统计',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simple_triggers',
     'COLUMN', N'repeat_count'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'重复的间隔时间',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simple_triggers',
     'COLUMN', N'repeat_interval'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'已经触发的次数',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simple_triggers',
     'COLUMN', N'times_triggered'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'简单触发器的信息表',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simple_triggers'
GO


-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- 12、同步机制的行锁表 qrtz_simprop_triggers
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[qrtz_simprop_triggers]') AND type IN ('U'))
    DROP TABLE [dbo].[qrtz_simprop_triggers]
GO

CREATE TABLE [dbo].[qrtz_simprop_triggers] (
                                               [sched_name] varchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                               [trigger_name] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                               [trigger_group] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                               [str_prop_1] varchar(512) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                               [str_prop_2] varchar(512) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                               [str_prop_3] varchar(512) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                               [int_prop_1] int DEFAULT NULL NULL,
                                               [int_prop_2] int DEFAULT NULL NULL,
                                               [long_prop_1] bigint  NULL,
                                               [long_prop_2] bigint  NULL,
                                               [dec_prop_1] numeric(13,4) DEFAULT NULL NULL,
                                               [dec_prop_2] numeric(13,4) DEFAULT NULL NULL,
                                               [bool_prop_1] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                               [bool_prop_2] varchar(255) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL
)
GO

ALTER TABLE [dbo].[qrtz_simprop_triggers] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
     'MS_Description', N'调度名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers',
     'COLUMN', N'sched_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'qrtz_triggers表trigger_name的外键',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers',
     'COLUMN', N'trigger_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'qrtz_triggers表trigger_group的外键',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers',
     'COLUMN', N'trigger_group'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'String类型的trigger的第一个参数',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers',
     'COLUMN', N'str_prop_1'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'String类型的trigger的第二个参数',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers',
     'COLUMN', N'str_prop_2'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'String类型的trigger的第三个参数',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers',
     'COLUMN', N'str_prop_3'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'int类型的trigger的第一个参数',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers',
     'COLUMN', N'int_prop_1'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'int类型的trigger的第二个参数',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers',
     'COLUMN', N'int_prop_2'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'long类型的trigger的第一个参数',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers',
     'COLUMN', N'long_prop_1'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'long类型的trigger的第二个参数',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers',
     'COLUMN', N'long_prop_2'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'decimal类型的trigger的第一个参数',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers',
     'COLUMN', N'dec_prop_1'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'decimal类型的trigger的第二个参数',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers',
     'COLUMN', N'dec_prop_2'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'Boolean类型的trigger的第一个参数',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers',
     'COLUMN', N'bool_prop_1'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'Boolean类型的trigger的第二个参数',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers',
     'COLUMN', N'bool_prop_2'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'同步机制的行锁表',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_simprop_triggers'
GO


-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- 13、触发器详细信息表 qrtz_triggers
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[qrtz_triggers]') AND type IN ('U'))
    DROP TABLE [dbo].[qrtz_triggers]
GO

CREATE TABLE [dbo].[qrtz_triggers] (
                                       [sched_name] varchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                       [trigger_name] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                       [trigger_group] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                       [job_name] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                       [job_group] varchar(200) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                       [description] nvarchar(250) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                       [next_fire_time] bigint DEFAULT NULL NULL,
                                       [prev_fire_time] bigint DEFAULT NULL NULL,
                                       [priority] int  NULL,
                                       [trigger_state] varchar(16) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                       [trigger_type] varchar(8) COLLATE Chinese_PRC_CI_AS  NOT NULL,
                                       [start_time] bigint  NOT NULL,
                                       [end_time] bigint DEFAULT NULL NULL,
                                       [calendar_name] varchar(200) COLLATE Chinese_PRC_CI_AS DEFAULT NULL NULL,
                                       [misfire_instr] bigint DEFAULT NULL NULL,
                                       [job_data] varbinary(max)  NULL
)
GO

ALTER TABLE [dbo].[qrtz_triggers] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
     'MS_Description', N'调度名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'sched_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'触发器的名字',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'trigger_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'触发器所属组的名字',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'trigger_group'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'qrtz_job_details表job_name的外键',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'job_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'qrtz_job_details表job_group的外键',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'job_group'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'相关介绍',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'description'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'上一次触发时间（毫秒）',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'next_fire_time'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'下一次触发时间（默认为-1表示不触发）',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'prev_fire_time'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'优先级',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'priority'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'触发器状态',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'trigger_state'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'触发器的类型',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'trigger_type'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'开始时间',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'start_time'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'结束时间',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'end_time'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'日程表名称',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'calendar_name'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'补偿执行的策略',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'misfire_instr'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'存放持久化job对象',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers',
     'COLUMN', N'job_data'
GO

EXEC sp_addextendedproperty
     'MS_Description', N'触发器详细信息表',
     'SCHEMA', N'dbo',
     'TABLE', N'qrtz_triggers'
GO


-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO [dbo].[qrtz_triggers] ([sched_name], [trigger_name], [trigger_group], [job_name], [job_group], [description], [next_fire_time], [prev_fire_time], [priority], [trigger_state], [trigger_type], [start_time], [end_time], [calendar_name], [misfire_instr], [job_data]) VALUES (N'RuoyiScheduler', N'TASK_CLASS_NAME1', N'DEFAULT', N'TASK_CLASS_NAME1', N'DEFAULT', NULL, N'1689660020000', N'-1', N'5', N'PAUSED', N'CRON', N'1689660015000', N'0', NULL, N'2', 0x)
GO

INSERT INTO [dbo].[qrtz_triggers] ([sched_name], [trigger_name], [trigger_group], [job_name], [job_group], [description], [next_fire_time], [prev_fire_time], [priority], [trigger_state], [trigger_type], [start_time], [end_time], [calendar_name], [misfire_instr], [job_data]) VALUES (N'RuoyiScheduler', N'TASK_CLASS_NAME2', N'DEFAULT', N'TASK_CLASS_NAME2', N'DEFAULT', NULL, N'1689660015000', N'-1', N'5', N'PAUSED', N'CRON', N'1689660015000', N'0', NULL, N'2', 0x)
GO

INSERT INTO [dbo].[qrtz_triggers] ([sched_name], [trigger_name], [trigger_group], [job_name], [job_group], [description], [next_fire_time], [prev_fire_time], [priority], [trigger_state], [trigger_type], [start_time], [end_time], [calendar_name], [misfire_instr], [job_data]) VALUES (N'RuoyiScheduler', N'TASK_CLASS_NAME3', N'DEFAULT', N'TASK_CLASS_NAME3', N'DEFAULT', NULL, N'1689660020000', N'-1', N'5', N'PAUSED', N'CRON', N'1689660015000', N'0', NULL, N'2', 0x)
GO
