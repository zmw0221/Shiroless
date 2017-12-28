package cn.et.less01.entity;

public class Menu {
	private Integer menuid;
	private String menuname;
	private String menuurl;
	private String menufitter;
	private Integer ismenu;
	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getMenuurl() {
		return menuurl;
	}
	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}
	public String getMenufitter() {
		return menufitter;
	}
	public void setMenufitter(String menufitter) {
		this.menufitter = menufitter;
	}
	public Integer getIsmenu() {
		return ismenu;
	}
	public void setIsmenu(Integer ismenu) {
		this.ismenu = ismenu;
	}
	@Override
	public String toString() {
		return "Menu [menuid=" + menuid + ", menuname=" + menuname + ", menuurl=" + menuurl + ", menufitter="
				+ menufitter + ", ismenu=" + ismenu + "]";
	}
}
