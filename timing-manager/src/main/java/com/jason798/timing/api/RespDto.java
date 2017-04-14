package com.jason798.timing.api;

import java.io.Serializable;

/**
 *
 * @author JasonLiu
 */
public class RespDto <T> implements Serializable {
	private boolean succ = true;
	private T data;
	
	public RespDto(){}
	
	public RespDto(boolean succ, T data) {
		this.succ = succ;
		this.data = data;
	}
	
	public static RespDto buildOk(){
		RespDto respDto = new RespDto();
		
		return respDto;
	}
	
	public static RespDto buildFail(){
		RespDto respDto = new RespDto(false,null);
		return respDto;
	}

	public static RespDto buildFail(RetCode code){
		RespDto respDto = new RespDto(false,code);
		return respDto;
	}

	public static RespDto<Long> buildOkLong(Long val){
		RespDto respDto = new RespDto();
		respDto.setData(val);
		return respDto;
	}
	
	public boolean isSucc() {
		return succ;
	}
	
	public void setSucc(boolean succ) {
		this.succ = succ;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
}
