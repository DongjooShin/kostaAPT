package kosta.apt.domain.publicmanage;

import java.io.Serializable;

public class Graph implements Serializable {
	String d;
	int 관리비;
	public int get관리비() {
		return 관리비;
	}
	public void set관리비(int 관리비) {
		this.관리비 = 관리비;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}

	
	

}
