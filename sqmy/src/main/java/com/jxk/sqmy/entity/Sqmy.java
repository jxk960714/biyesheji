package com.jxk.sqmy.entity;

import java.util.Date;

public class Sqmy {
	private Integer Id;
	private String title;
	private  Category category;
	private Bsdw bsDw;// 报送单位
	private Integer jjcd;// 紧急程度
	private String xxly;// 信息来源
	private String fyr;// 反映人
	private String bz;// 备注
	private String zw;// 正文
	private String fj;// 附件
	private int status;// 状态
	private User user;// 当前用户
	private String tjtime;//提交时间
	private  Integer isFaBu;
	private  String keyWord;
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		this.Id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Bsdw getBsDw() {
		return bsDw;
	}

	public void setBsDw(Bsdw bsDw) {
		this.bsDw = bsDw;
	}

	public Integer getJjcd() {
		return jjcd;
	}

	public void setJjcd(Integer jjcd) {
		this.jjcd = jjcd;
	}

	public String getXxly() {
		return xxly;
	}

	public void setXxly(String xxly) {
		this.xxly = xxly;
	}

	public String getFyr() {
		return fyr;
	}

	public void setFyr(String fyr) {
		this.fyr = fyr;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public String getFj() {
		return fj;
	}

	public void setFj(String fj) {
		this.fj = fj;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTjtime() {
		return tjtime;
	}

	public void setTjtime(String tjtime) {
		this.tjtime = tjtime;
	}

	public Integer getIsFaBu() {
		return isFaBu;
	}

	public void setIsFaBu(Integer isFaBu) {
		this.isFaBu = isFaBu;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
}
