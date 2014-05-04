<#macro tab value>
<div class="pager" style="width:540px;">
<#if value??>
    <#if value.list??>
      <a href="<#if value.toPage==1>javascript:void(0)<#else>javascript:_gotoPage(1)</#if>;" class="prev">首页</a>
      <a href="<#if value.toPage==1>javascript:void(0)<#else>javascript:_gotoPage(${value.toPage-1})</#if>;" class="prev">上一页</a>
	  <span>
	    <#if value.totalPage lte 10>
	       <#list 1..value.totalPage as i>
	           <a href="javascript:_gotoPage(${i_index+1});" class="show <#if (i_index+1)==(value.toPage)>hover</#if>">${i_index+1}</a>
	       </#list>
	    <#else>
	       	<#if (value.toPage lte 3)||((value.totalPage-value.toPage) lte 2)>
	       	    <a href="javascript:_gotoPage(1);" class="show <#if 1==(value.toPage)>hover</#if>">1</a>
	       	    <a href="javascript:_gotoPage(2);" class="show <#if 2==(value.toPage)>hover</#if>">2</a>
	       	    <a href="javascript:_gotoPage(3);" class="show <#if 3==(value.toPage)>hover</#if>">3</a>
	       	    <#if value.toPage==3>
	       	    <a href="javascript:_gotoPage(4);" class="show">4</a>
	       	    </#if>
	       	    ....
	       	           	     
	       	    <#if (value.totalPage-value.toPage)==2>
	       	     <a href="javascript:_gotoPage(${value.totalPage-3});" class="show">${value.totalPage-3}</a>
	       	    </#if>
	       	    <a href="javascript:_gotoPage(${value.totalPage-2});" class="show <#if (value.totalPage-2)==(value.toPage)>hover</#if>">${value.totalPage-2}</a>
	       	    <a href="javascript:_gotoPage(${value.totalPage-1});" class="show <#if (value.totalPage-1)==(value.toPage)>hover</#if>">${value.totalPage-1}</a>
	       	    <a href="javascript:_gotoPage(${value.totalPage});"   class="show  <#if (value.totalPage)==(value.toPage)>hover</#if>">${value.totalPage}</a>
	       	</#if>
	       	<#if (value.toPage gt 3)&&((value.totalPage-value.toPage) gt 2)>
		       	 <a href="javascript:_gotoPage(1);" class="show <#if 1==(value.toPage)>hover</#if>">1</a>	       	   
	       	    ....
		       	<a href="javascript:_gotoPage(${value.toPage-1});" class="show <#if (value.toPage-1)==(value.toPage)>hover</#if>">${value.toPage-1}</a>
	       	    <a href="javascript:_gotoPage(${value.toPage});" class="show <#if (value.toPage)==(value.toPage)>hover</#if>">${value.toPage}</a>
	       	    <#if (value.toPage+1)!=(value.totalPage-2)>
	       	    <a href="javascript:_gotoPage(${value.toPage+1});"   class="show  <#if (value.toPage+1)==(value.toPage)>hover</#if>">${value.toPage+1}</a>
	       	      ....	       	    
	       	    <a href="javascript:_gotoPage(${value.totalPage-2});" class="show <#if (value.totalPage-2)==(value.toPage)>hover</#if>">${value.totalPage-2}</a>
	       	    <#else>
	       	     <a href="javascript:_gotoPage(${value.totalPage-2});" class="show <#if (value.totalPage-2)==(value.toPage)>hover</#if>">${value.totalPage-2}</a>
	       	    </#if> 	       	    
	       	    <a href="javascript:_gotoPage(${value.totalPage-1});" class="show <#if (value.totalPage-1)==(value.toPage)>hover</#if>">${value.totalPage-1}</a>
	       	    <a href="javascript:_gotoPage(${value.totalPage});"   class="show  <#if (value.totalPage)==(value.toPage)>hover</#if>">${value.totalPage}</a>
	       	
	       	</#if>
	    </#if>
      </span>
      <a href="<#if (value.toPage+1)<=(value.totalPage)>javascript:_gotoPage(${value.toPage+1}) <#else>javascript:void(0)</#if>;" class="next">下一页</a>
	  <a href="<#if (value.toPage+1)<=(value.totalPage)>javascript:_gotoPage(${value.totalPage}) <#else>javascript:void(0)</#if>;" class="prev">末页</a>
	</#if>
  </#if>
</div>
</#macro>
