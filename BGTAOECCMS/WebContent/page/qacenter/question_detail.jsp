<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- common header include js file -->
<%@ include file="/page/common/header.jsp"%>
<!-- common header include js file -->
<body>
	<style type="text/css">
.wordBreak {
	word-break: break-all;
	word-wrap: break-word;
}
</style>
	<div class="wrapper">
		<div class="container">
			<div class="content clearfix pt10 wordBreak">
				<div class="grid grid-12 questionBox clearfix">
					<!--questionCon start-->
					<c:if test="${not empty question}">

						<div class="questionCon clearfix">
							<div class="questionTabCon">
								<div class="detailedQuestions">
									<h2>
										<a href="#">${question.questionContent}</a>
									</h2>
									<div class="editQuestion-title hide">
										<input type="text" value="">
									</div>
									<p class="question-con">${question.questionDescription}</p>
									<!--  <div class="editQuestion-con hide" contenteditable="true"></div>
							<div class="detailedQuestions-btn clearfix">
								<a href="#" class="detailedQuestions-btn-a">关注问题</a> <a href="#"
									class="detailedQuestions-btn-b hide">添加说明</a>
								当该问题没有问题说明时才显示该按钮
								<a href="#" class="a-b radius" id="myInvite">邀请回答</a> <a
									href="#" class="a-a radius" id="myAnswer">我来回答</a>
							</div>
							<div class="answer hide">
								我来回答
								<div class="answerInput" contenteditable="true">
									插入编辑器后去掉改div的contenteditable属性
								</div>
								<button class="btn btn-small btn-darkBlue mr10">提交</button>
								<button class="btn btn-small btn-gary" id="btn-answer">取消</button>
							</div>
							<div class="invite mt20 hide">
								邀请回答
								<div class="recommendTeacher ">
									<div class="recommendTeacher-hd">
										<span class="fr">换一批</span>推荐老师
									</div>
									<ul class="recommendTeacher-bd clearfix">
										<li><img src="" width="100" height="100"> <span
											title="老师名称1" id="1">老师名称1</span></li>
										<li><img src="" width="100" height="100"> <span>老师名称2</span>
										</li>
										<li><img src="" width="100" height="100"> <span>
												老师名称3</span></li>
										<li><img src="" width="100" height="100"> <span>
												老师名称4</span></li>
										<li><img src="" width="100" height="100"> <span>
												老师名称</span></li>
									</ul>
								</div>
								<div class="selected gray ">
									已选择： <span class="selected-item">老师名称1<i>X</i></span><span
										class="selected-item">老师名称2<i>X</i></span><span
										class="selected-item">老师名称3<i>X</i></span><span
										class="selected-item">老师名称4<i>X</i></span>
								</div>
								<div class="searchInput mt10 ">
									<label class="fl gray">没有你想要的老师？搜索一下：</label> <input class="fl"
										id="searchTeacher-input" value="" type="text"
										placeholder="请输入教师/讲师名称">
									<div class="search-list hide">
										<ul>
											<li><img src="" width="30" height="30"> <span>老师名称</span>
												<button class="btn btn-mini btn-darkBlue " value="老师名称"
													id="1">添 加</button></li>
											<li><img src="" width="30" height="30"> <span>老师名称</span>
												<button class="btn btn-mini btn-darkBlue">添 加</button></li>
											<li><img src="" width="30" height="30"> <span>老师名称</span>
												<button class="btn btn-mini btn-darkBlue">添 加</button></li>
											<li><img src="" width="30" height="30"> <span>老师名称</span>
												<button class="btn btn-mini btn-darkBlue">添 加</button></li>
											<li><img src="" width="30" height="30"> <span>老师名称</span>
												<button class="btn btn-mini btn-darkBlue">添 加</button></li>
										</ul>
									</div>
								</div>
								<button class="btn btn-small btn-darkBlue mt10 mr10">提交</button>
								<button class="btn btn-small btn-gary" id="btn-invite">取消</button>
							</div> -->
								</div>
								<c:if test="${not empty question.answerList}">
									<div class="hotQuestionCon mt20 mb20">
										<h3>
											历史回答 ( <span>${fn:length(question.answerList)}</span> 条回答 )
										</h3>
										<ul class="hotQuestionCon-ul">
											<c:forEach items="${question.answerList}" var="answer">
												<li class="clearfix">
												<c:if test="${not empty answer.teacherUserOfAnswer.userHeadPic}">
												<img src="${ctx}${answer.teacherUserOfAnswer.userHeadPic}" />
												</c:if>
												<c:if test="${empty answer.teacherUserOfAnswer.userHeadPic}">
												<img src="${ctx}/images/base/default_pic_temp_user.jpg" />
												</c:if>
												
													<div class="hotQuestionCon-info">
														<p class="hotQuestionCon-info-a">
															<span>${answer.teacherUserOfAnswer.userName}</span>
															/${answer.teacherUserOfAnswer.levelOfTeacher}
														</p>
														<p class="hotQuestionCon-info-b">${answer.answerContent}</p>
														<c:if test="${ not empty answer.questionAddList}">
															<div class="addQuestion">
																<c:forEach items="${answer.questionAddList}"
																	var="questionAdd">
																	<dl>
																		<dt class="answered">
																			<div class="addQuestion-tit">
																				<b>追加：</b>${questionAdd.questionContent}
																			</div>
																		</dt>
																		<c:if
																			test="${not empty questionAdd.answerListOfQuestionAdd}">
																			<c:forEach
																				items="${questionAdd.answerListOfQuestionAdd}"
																				var="answerOfQuestionAdd">
																				<dd>${answerOfQuestionAdd.answerContent}</dd>
																			</c:forEach>
																		</c:if>
																	</dl>
																</c:forEach>
															</div>
														</c:if>
														<!-- <a href="#" class="hotQuestionCon-info-c">赞12</a> <span
															class="hotQuestionCon-info-d">追加问题</span>
														<div class="addQuestionInput mt10 hide">
															<div class="con"></div>
															<button class="btn btn-small btn-darkBlue mt10 mr10">提交</button>
															<button class="btn btn-small btn-gary mt10"
																id="btn-addQuestionInput">取消</button>
														</div> -->
													</div></li>
											</c:forEach>
										</ul>
									</div>
								</c:if>
							</div>
						</div>
					</c:if>
					<!--questionCon end-->
				</div>
			</div>
		</div>
	</div>
</body>
</html>