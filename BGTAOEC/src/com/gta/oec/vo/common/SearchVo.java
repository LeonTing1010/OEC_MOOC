
package com.gta.oec.vo.common;

import com.gta.oec.vo.BaseVO;

/**
 * 搜索实体类
 * @author 刘祚家
 *
 */
public class SearchVo extends BaseVO{
	private static final long serialVersionUID = 7920865612340125141L;
	
	/**搜索关键字内容**/
	private String searchtext;
	

	public String getSearchtext() {
		return searchtext;
	}

	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}
	
}
