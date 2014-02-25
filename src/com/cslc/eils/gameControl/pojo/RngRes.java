package com.cslc.eils.gameControl.pojo;
import java.io.Serializable;
import java.util.List;

public class RngRes  implements Serializable{
	
	// 信息代码
	private int code;
	// 信息内容
	private String message;
	//随机数
	private List<Integer> rngs;
	//getter and setter

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Integer> getRngs() {
		return rngs;
	}

	public void setRngs(List<Integer> rngs) {
		this.rngs = rngs;
	}

}
