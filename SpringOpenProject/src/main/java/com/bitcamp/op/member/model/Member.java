package com.bitcamp.op.member.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Member {

	private int midx;
	
	private String id;
	private String pw;
	
	// 파일 업로드 처리를 위한 변수
	private MultipartFile photoFile;
	// DB 데이터 입력을 위한 변수
	private String photo;
	
	private String name;
	private Date regdate;
	
	
	
	
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public MultipartFile getPhotoFile() {
		return photoFile;
	}
	public void setPhotoFile(MultipartFile photoFile) {
		this.photoFile = photoFile;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public boolean isMatchPassword(String pw) {

		return this.pw.equals(pw) ? true : false;
	}
	@Override
	public String toString() {
		return "Member [midx=" + midx + ", id=" + id + ", pw=" + pw + ", photoFile=" + photoFile + ", photo=" + photo
				+ ", name=" + name + ", regdate=" + regdate + "]";
	}
	
	
	
	
}
