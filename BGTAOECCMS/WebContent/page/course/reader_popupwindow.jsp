<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/header.jsp"%>
<body>
	<script type="text/javascript"
		src="${ctx}/js/jquery_plugins/swfobject.js"></script>
	<script type="text/javascript">
		var swfVersionStr = "10.0.0";
		var xiSwfUrlStr = "${ctx}/TextReader/expressInstall.swf";
		/**
		 * 阅读器初始化完毕回调
		 */
		function onInitComplete() {
			document.getElementById("promptText").value += "阅读器初始化完毕，调用onInitComplete\n";
		}

		/**
		 * 加载资源完成回调
		 */
		function onLoadSwfComplete(page) {
			if (page == -1) {
				document.getElementById("promptText").value += "加载全部资源完成回调，调用onLoadSwfComplete\n";
			} else {
				document.getElementById("promptText").value += "加载第"
						+ (parseInt(page) + 1)
						+ "页资源完成回调，调用onLoadSwfComplete\n";
			}
		}

		/**
		 * 页码发生改变回调
		 */
		function onPageChange(page) {
			document.getElementById("promptText").value += "页码发生改变回调，调用onPageChange。当前页："
					+ page + "\n";
		}

		/**
		 * 添加元素回调
		 * 返回1表示成功，0失败，不返回默认成功
		 */
		function onElementNew(elementId, elementType, elementData) {
			document.getElementById("promptText").value += "添加元素回调，调用onElementNew"
					+ "\n"
					+ "elementId:"
					+ elementId
					+ "\n"
					+ "elementType:"
					+ elementType + "\n" + "elementData:" + elementData + "\n";
			return 1;
		}

		/**
		 * 元素发生改变回调
		 * 返回1表示成功，0失败，不返回默认成功
		 */
		function onElementChange(elementId, elementType, elementData) {
			document.getElementById("promptText").value += "元素发生改变回调，调用onElementChange"
					+ "\n"
					+ "elementId:"
					+ elementId
					+ "\n"
					+ "elementType:"
					+ elementType + "\n" + "elementData:" + elementData + "\n";
			return 1;
		}

		/**
		 * 页码删除回调
		 * 返回1表示成功，0失败，不返回默认成功
		 */
		function onElementDelete(elementId, elementType, elementData) {
			document.getElementById("promptText").value += "元素删除回调，调用onElementChange"
					+ "\n"
					+ "elementId:"
					+ elementId
					+ "\n"
					+ "elementType:"
					+ elementType + "\n" + "elementData:" + elementData + "\n";
			return 1;
		}

		function getFlashObj(movie) {
			if (window.document[movie]) {
				return window.document[movie];
			}
			if (navigator.appName.indexOf("Microsoft Internet") == -1) {
				if (document.embeds && document.embeds[movie]) {
					return document.embeds[movie];
				}
			} else {
				return document.getElementById(movie);
			}
		}

		function setEditMode(mode) {
			getFlashObj("TextReader").setEditMode(mode);
		}

		function palySource(tag) {
			var $vidoDiv = $("<div>").attr('id', 'flashContent');
			$('.swfCon').html('');
			$vidoDiv.appendTo($('.swfCon'));
			var flashvars = {
				bookId : "abc", //资源id
				userId : "aaa",
				paging : "false", //资源加载方式，0或false为一次性加载，1或true为分页加载
				// sourceUrl: "http://learning.gtadata.com:8085/TextReader/assets/books/starling/", 		//资源地址，不包括swf文件
				// swfFile: "Starling*.swf,110,0", 		//swf文件名称，总页数，以及是否在左边补零：0.不补零，1.补零
				// bookmarkUrl: "http://localhost:18003/ResReader/ResBookMark", 	//书签数据地址
				// commentUrl: "http://localhost:18003/ResReader/ResPostion", 	//获取批注数据的URL，可以有多个批注地址，用逗","隔开	
				bookUrl : '',
				skinStyle : "1", //皮肤样式：1.易资源皮肤样式。2.数字化校园皮肤样式
				skinUrl : "${ctx}/TextReader/yiSkin.swf", //皮肤地址
				toolbarVisible : "1", //是否显示工具栏:0.不显示，1.显示
				backgroundColor : "0xffffff", //背景色
				backgroundAlpha : "1", //背景透明度
				backgroundUrl : "${ctx}/TextReader/assets/images/yiSkin.jpg", //背景图片
				//  toolbarItems: "switchPage,drag,textSelect,addPostil,addBookmark,readerProgress,previousPage,page,nextPage,bookmarkPostil,fullScreen",
				toolbarItems : "switchPage,drag,textSelect,fullScreen",
				onInitComplete : "onInitComplete", //阅读器初始化完成回调函数
				onLoadSwfComplete : "onLoadSwfComplete", //加载文档完成回调函数
				onPageChange : "onPageChange", //页码发生改变回调函数，格式：单页返回"6",双页返回"6-7"
				onElementChange : "onElementChange", //元素改变事件
				onElementNew : "onElementNew", //元素新增事件
				onElementDelete : "onElementDelete" //元素删除事件
			};
			flashvars.bookUrl = cache + $(tag).attr('data-url');
			console.log(flashvars.bookUrl);
			flashvars.bookUrl = flashvars.bookUrl.replace("/TextReader/", "/");
			console.log(flashvars.bookUrl);
			var params = {};
			params.quality = "high";
			params.bgcolor = "#ffffff";
			params.allowscriptaccess = "sameDomain";
			params.allowfullscreen = "true";
			var attributes = {};
			attributes.id = "TextReader";
			attributes.name = "TextReader";
			swfobject.embedSWF("${ctx}/TextReader/TextReader.swf",
					"flashContent", "1024", "700", //设置阅读器宽度、高度
					swfVersionStr, xiSwfUrlStr, flashvars, params, attributes);
			swfobject.createCSS("#flashContent",
					"display:block;text-align:left;");

		}
		$(function($) {
			$('.menu li').eq(0).click();
		})
	</script>
	<div class="wrapper">
		<div class="menu">
			<ul>
				<c:if test="${not empty resourceList}">
					<c:forEach items="${resourceList}" var="resource">
						<li data-url="${resource.resourceURL}"
							onclick="javascript:palySource(this)"><c:choose>
								<c:when test="${fn:length(resource.resourceName) gt 8 }">
									<a href="javascript:void(0)">${fn:substring(resource.resourceName, 0, 7)}...</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)">${resource.resourceName}</a>
								</c:otherwise>
							</c:choose></li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
		<div class="swfCon" style="width: 1024px;height:700px;">
			<div id="flashContent"></div>
		</div>
	</div>
	<style>
.wrapper {
	background: #f2f2f2;
	position: relative;
}

.menu {
	background-color: #eee;
	overflow: visible !important;
	width: 170px;
	overflow: hidden;
	border-right: 1px solid #d7d7d7;
}

.menu ul {
	margin: 10px;
	border: 1px solid #d7d7d7;
	border-bottom: none;
}

.menu ul li {
	height: 40px;
	line-height: 40px;
	background: #fff;
	border-bottom: 1px solid #d7d7d7;
	text-align: center;
}

.menu ul li a {
	display: block;
	color: #555;
}

.menu ul li a:hover {
	background: #69F;
	color: #fff;
}

.swfCon {
	margin: 0 0 0 170px;
	min-height: 500px;
	min-width: 320px;
	overflow: hidden;
	position: relative;
	background: #f2f2f2
}
</style>

</body>
</html>