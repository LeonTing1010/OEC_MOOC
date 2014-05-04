<h1>User List Page : ${listMessage}</h1>
<table border="1px;">
<tr>
	<td>userId</td>
	<td>roleID</td>
	<td>userName</td>
	<td>userMobile</td>
	<td>userEmail</td>
	<td>password</td>
	<td>userType</td>
	<td>userHeadPic</td>
	<td>userLoginInfo</td>
	<td>userResetPwdCode</td>
</tr>
<#list users as user>
<tr>
	<td>
		<#if user.userId??>${user.userId}</#if>
	</td>
	<td>
		<#if user.roleID??>${user.roleID}</#if>	
	</td>
	<td>
		<#if user.userName??>${user.userName}</#if>
	</td>
	<td>
		<#if user.userMobile??>${user.userMobile}</#if>
	</td>
	<td>
		<#if user.userEmail??>${user.userEmail}</#if>
	</td>
	<td>
		<#if user.password??>${user.password}</#if>
	</td>
	<td>
		<#if user.userType??>${user.userType}</#if>
	</td>
	<td>
		<#if user.userHeadPic??>${user.userHeadPic}</#if>
	</td>
	<td>
		<#if user.userLoginInfo??>${user.userLoginInfo}</#if> 
	</td>
	<td>
		<#if user.userResetPwdCode??>${user.userResetPwdCode}</#if>
	</td>
</tr> 
</#list>
</table>