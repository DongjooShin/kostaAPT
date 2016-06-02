package kosta.apt.domain.vote;

import java.io.Serializable;

public class APTGroup implements Serializable {
	private int apt_APTGNo;
	private int apt_price;
	private int apt_realFamilyNum;
	private String apt_mainImage;
	
	
	public String getApt_mainImage() {
		return apt_mainImage;
	}
	public void setApt_mainImage(String apt_mainImage) {
		this.apt_mainImage = apt_mainImage;
	}
	public int getApt_APTGNo() {
		return apt_APTGNo;
	}
	public void setApt_APTGNo(int apt_APTGNo) {
		this.apt_APTGNo = apt_APTGNo;
	}
	public int getApt_price() {
		return apt_price;
	}
	public void setApt_price(int apt_price) {
		this.apt_price = apt_price;
	}
	public int getApt_realFamilyNum() {
		return apt_realFamilyNum;
	}
	public void setApt_realFamilyNum(int apt_realFamilyNum) {
		this.apt_realFamilyNum = apt_realFamilyNum;
	}

}
