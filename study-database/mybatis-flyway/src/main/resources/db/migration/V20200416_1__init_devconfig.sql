DROP TABLE IF EXISTS "public"."active_config";
CREATE TABLE "public"."active_config" (
"config_id"  VARCHAR(60) NOT NULL,
"device_id" VARCHAR(20) NOT NULL,
"create_time" TIMESTAMP NOT NULL,
"config_time" TIMESTAMP NULL,
"config_status" int4 NOT NULL,
"business_type" VARCHAR(20) NOT NULL,
"config_info" VARCHAR(500) NOT NULL,
"config_result" VARCHAR(500) NOT NULL,
PRIMARY KEY ("config_id")
)
;

COMMENT ON COLUMN "public"."active_config"."config_id" IS '配置ID';
COMMENT ON COLUMN "public"."active_config"."device_id" IS '设备识别信息';
COMMENT ON COLUMN "public"."active_config"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."active_config"."config_time" IS '配置下到设备的时间';
COMMENT ON COLUMN "public"."active_config"."config_status" IS '配置下发的状态 1:创建 2:下发成功 3:收到结果 4:超时';
COMMENT ON COLUMN "public"."active_config"."business_type" IS '业务类型区分';
COMMENT ON COLUMN "public"."active_config"."config_info" IS '业务配置信息';
COMMENT ON COLUMN "public"."active_config"."config_result" IS '业务配置的最近状态结果';


DROP TABLE IF EXISTS "public"."history_config";
CREATE TABLE "public"."history_config" (
"config_id"  VARCHAR(60) NOT NULL,
"device_id" VARCHAR(20) NOT NULL,
"create_time" TIMESTAMP NOT NULL,
"config_time" TIMESTAMP NULL,
"config_status" int4 NOT NULL,
"business_type" VARCHAR(20) NOT NULL,
"config_info" VARCHAR(500) NOT NULL,
"config_result" VARCHAR(500) NOT NULL,
PRIMARY KEY ("config_id")
)
;

COMMENT ON COLUMN "public"."history_config"."config_id" IS '配置ID';
COMMENT ON COLUMN "public"."history_config"."device_id" IS '设备识别信息';
COMMENT ON COLUMN "public"."history_config"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."history_config"."config_time" IS '配置下到设备的时间';
COMMENT ON COLUMN "public"."history_config"."config_status" IS '配置下发的状态 1:创建 2:下发成功 3:收到结果 4:超时';
COMMENT ON COLUMN "public"."history_config"."business_type" IS '业务类型区分';
COMMENT ON COLUMN "public"."history_config"."config_info" IS '业务配置信息';
COMMENT ON COLUMN "public"."history_config"."config_result" IS '业务配置的最近状态结果';


DROP TABLE IF EXISTS "public"."device_info";
CREATE TABLE "public"."device_info" (
"mac"  VARCHAR(60) NOT NULL,
"model" VARCHAR(20) NOT NULL,
"version" VARCHAR(20) NOT NULL,
"ip" VARCHAR(20) NULL,
"connected" BOOLEAN NULL,
"create_time" TIMESTAMP NULL,
"update_time" TIMESTAMP NULL,
PRIMARY KEY ("mac")
)
;

COMMENT ON COLUMN "public"."device_info"."mac" IS '设备MAC地址';
COMMENT ON COLUMN "public"."device_info"."model" IS '设备型号';
COMMENT ON COLUMN "public"."device_info"."version" IS '设备版本号';
COMMENT ON COLUMN "public"."device_info"."ip" IS '设备ip';
COMMENT ON COLUMN "public"."device_info"."connected" IS '连接状态';
COMMENT ON COLUMN "public"."device_info"."create_time" IS '记录创建时间';
COMMENT ON COLUMN "public"."device_info"."update_time" IS '最后更新时间';