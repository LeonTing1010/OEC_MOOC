
package com.gta.oec.vo.school;

import com.gta.oec.vo.BaseVO;

/**
 * 
 * 功能描述：学校实体类
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class SchoolVo extends BaseVO{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5662788919785452367L;
	/**学校id**/
	private int schId;
	/**学校名字**/
	private String schName;
	/**学校简介**/
	private String schDescription;
	/**学校地址**/
	private String schAddress;
	/**学校网址**/
	private String schWww;
	/**学校logo**/
	private String schLogo;
	/**生成时间**/
	private String schCtime;
	public int getSchId() {
		return schId;
	}
	public void setSchId(int schId) {
		this.schId = schId;
	}
	public String getSchName() {
		return schName;
	}
	public void setSchName(String schName) {
		this.schName = schName;
	}
	public String getSchDescription() {
		return schDescription;
	}
	public void setSchDescription(String schDescription) {
		this.schDescription = schDescription;
	}
	public String getSchWww() {
		return schWww;
	}
	public void setSchWww(String schWww) {
		this.schWww = schWww;
	}
	public String getSchLogo() {
		return schLogo;
	}
	public void setSchLogo(String schLogo) {
		this.schLogo = schLogo;
	}
	public String getSchCtime() {
		return schCtime;
	}
	public void setSchCtime(String schCtime) {
		this.schCtime = schCtime;
	}
	public String getSchAddress() {
		return schAddress;
	}
	public void setSchAddress(String schAddress) {
		this.schAddress = schAddress;
	}
}
