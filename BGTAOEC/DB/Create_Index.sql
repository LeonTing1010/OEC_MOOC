ALTER TABLE `q_question` ADD INDEX index_Job_GroupID ( `Job_GroupID` );
ALTER TABLE `q_question` ADD INDEX index_Course_ID ( `Course_ID` );
ALTER TABLE `q_question` ADD INDEX index_UserID ( `UserID` );

ALTER TABLE `q_answer` ADD INDEX index_Ques_ID ( `Ques_ID` );
ALTER TABLE `q_answer` ADD INDEX index_User_ID ( `User_ID` );

ALTER TABLE `q_attention` ADD INDEX index_User_ID ( `User_ID` );

ALTER TABLE `q_attention_answer` ADD INDEX index_UserID ( `UserID` );
ALTER TABLE `q_attention_answer` ADD INDEX index_QuestionID ( `QuestionID` );

ALTER TABLE `q_question_add` ADD INDEX index_Ques_AnsUserID ( `Ques_AnsUserID` );

ALTER TABLE `q_question_user` ADD INDEX index_Ques_ID ( `Ques_ID` );
ALTER TABLE `q_question_user` ADD INDEX index_Teacher_ID ( `Teacher_ID` );

ALTER TABLE `u_learn_detail` ADD INDEX index_User_ID ( `User_ID` );

ALTER TABLE `u_user` ADD INDEX index_User_Email ( `User_Email` );

ALTER TABLE `u_teacher` ADD INDEX index_User_ID ( `User_ID` );

ALTER TABLE `u_student` ADD INDEX index_UserID ( `UserID` );

ALTER TABLE `c_course` ADD INDEX index_User_ID ( `User_ID` );

ALTER TABLE `c_course_detail` ADD INDEX index_Cour_ID ( `Cour_ID` );
ALTER TABLE `c_course_detail` ADD INDEX index_Sec_PID ( `Sec_PID` );

ALTER TABLE `e_exam` ADD INDEX index_Cour_ID ( `Cour_ID` );
ALTER TABLE `e_exam` ADD INDEX index_Sec_ID ( `Sec_ID` );
ALTER TABLE `e_exam` ADD INDEX index_Exam_Type ( `Exam_Type` );

ALTER TABLE `e_exam_answer_detail` ADD INDEX index_Exam_Stu_ID ( `Exam_Stu_ID` );
ALTER TABLE `e_exam_answer_detail` ADD INDEX index_Exam_Stu_ID ( `exam_Ques_ID` );

ALTER TABLE `e_exam_option` ADD INDEX index_Exam_Ques_ID ( `Exam_Ques_ID` );

ALTER TABLE `e_exam_paper` ADD INDEX index_User_ID ( `User_ID` );
ALTER TABLE `e_exam_paper` ADD INDEX index_Paper_Type ( `Paper_Type` );

ALTER TABLE `e_exam_question` ADD INDEX index_Paper_ID ( `Paper_ID` );
ALTER TABLE `e_exam_question` ADD INDEX index_Exam_Ques_Type ( `Exam_Ques_Type` );

ALTER TABLE `e_exam_student` ADD INDEX index_Exam_ID ( `Exam_ID` );
ALTER TABLE `e_exam_student` ADD INDEX index_Student_ID ( `Student_ID` );


ALTER TABLE `m_my_collect` ADD INDEX index_User_ID ( `User_ID` );

ALTER TABLE `m_my_note` ADD INDEX index_User_ID ( `User_ID` );

ALTER TABLE `p_job` ADD INDEX index_Pro_ID ( `Pro_ID` );
ALTER TABLE `p_job` ADD INDEX index_Job_PID ( `Job_PID` ); 


 
