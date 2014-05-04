ALTER TABLE q_question  MODIFY COLUMN Ques_Description text(0);
ALTER TABLE q_answer  MODIFY COLUMN Answ_Content text(0);
ALTER TABLE u_teacher MODIFY COLUMN Introduce varchar(500);

drop table if exists C_Course_Comment;
/*==============================================================*/
/* Table: C_Course_Comment                                      */
/*==============================================================*/
create table C_Course_Comment
(
   Com_ID               int not null auto_increment,
   Com_User_ID          int,
   Com_Course_ID        int,
   Com_Cri_Time         datetime,
   Com_Content          text,  
   Com_Source           varchar(20), 
   Com_Del              tinyint,
   primary key (Com_ID)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

alter table C_Course_Comment add constraint FK_Reference_85 foreign key (Com_User_ID)
      references U_User (User_ID) on delete restrict on update restrict;

alter table C_Course_Comment add constraint FK_Reference_86 foreign key (Com_Course_ID)

      references C_Course (Cour_ID) on delete restrict on update restrict;
      
/*-------------------------------------------*/
/*   Table:f_feedback                        */
      
DROP TABLE IF EXISTS f_feedback;
CREATE TABLE f_feedback (
  Feedback_ID int(11) NOT NULL AUTO_INCREMENT COMMENT '意见反馈ID',
  Feedback_Title varchar(50) NOT NULL,
  Feedback_Content text NOT NULL,
  Feedback_Email varchar(100) NOT NULL,
  Feedback_Ctime datetime NOT NULL,
  Feedback_Source  varchar(50) NOT NULL,
  PRIMARY KEY (Feedback_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*-------------------------------------------*/
      
/*============================课程表增加被浏览次数字段==================================*/
ALTER TABLE c_course
ADD COLUMN Course_Course_Attention  bigint NOT NULL AFTER School_ID;

