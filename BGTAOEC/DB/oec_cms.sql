/*
Navicat MySQL Data Transfer

Source Server         : 101.102
Source Server Version : 50535
Source Host           : 192.168.101.83:3306
Source Database       : oec

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2014-03-29 16:21:45
*/

/**
 * add and modify table operator
 * */

drop table if exists f_Faq;

/*==============================================================*/
/* Table: FQA_FQA                                               */
/*==============================================================*/
create table f_Faq
(
   FAQ_ID               int not null auto_increment,
   FAQ_Title            char(25),
   FAQ_Content          text,
   FAQ_CTime          datetime,
   FAQ_UTime          datetime,
   FAQ_PID              int,
   primary key (FAQ_ID)
);


drop table if exists a_attachment;

/*==============================================================*/
/* Table: ATT_attachment                                        */
/*==============================================================*/
create table a_attachment
(
   ATT_id               int not null auto_increment,
   ATT_BusinessID       int,
   ATT_Name             char(25),
   ATT_Size             char(20),
   ATT_Type             char(20),
   ATT_Address          char(20),
   ATT_Desc             text,
   ATT_CTime            datetime,
   ATT_UTime            datetime,
   primary key (ATT_id)
);


ALTER TABLE p_profession ADD COLUMN Rro_Image varchar(200);


/*add can.xie  begin===========================*/
-- 会员管理模块 添加创建时间和用户状态
ALTER TABLE u_user ADD  Create_Date datetime;

ALTER TABLE u_user ADD  User_state int(2);

UPDATE u_user u set u.User_state='1' -- 默认所有的用户都有效用户
  
/*=======================end =======================*/

/*问题表增加是否可见字段.用于标识问题是否被屏蔽 by:dongs*/
alter table q_question add column Ques_isVisible tinyint(1) unsigned zerofill  DEFAULT '0' COMMENT '是否公开.0:公开.1:不公开'

/** add by Bill huang [jianhua.huang] */
/*行业表增加是否可见字段.用于标识行业是否被隐藏 */
alter table p_profession add column Pro_isVisible tinyint(1) unsigned zerofill  DEFAULT '0' COMMENT '是否隐藏.0:公开.1:隐藏'

/*岗位和岗位群表增加是否可见字段.用于标识岗位和岗位群是否被隐藏 */
alter table p_job add column Job_isVisible tinyint(1) unsigned zerofill  DEFAULT '0' COMMENT '是否隐藏.0:公开.1:隐藏'
/** add by Bill huang [jianhua.huang] */