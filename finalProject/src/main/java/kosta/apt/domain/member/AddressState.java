package kosta.apt.domain.member;

import java.io.Serializable;

public class AddressState implements Serializable {
	private String addr_state;
	private int a_stateNum;
	
	
	
	public String getAddr_state() {
		return addr_state;
	}
	public void setAddr_state(String addr_state) {
		this.addr_state = addr_state;
	}
	public int getA_stateNum() {
		return a_stateNum;
	}
	public void setA_stateNum(int a_stateNum) {
		this.a_stateNum = a_stateNum;
	}
}
