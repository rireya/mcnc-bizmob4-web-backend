package com.mcnc.bizmob.web.global.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApiResponse<T> {
	private boolean status;
	private String code;
	private T data;
	private String message;
	
	public ApiResponse(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
    // 데이터가 단일 객체인 경우 설정
    public void setData(T data) {
        this.data = data;
    }
    
	 // 데이터가 리스트인 경우 설정
    public void setDataList(List<T> dataList) {
        this.data = (T) new DataList<>(0L, dataList);
    }
    
    // 데이터가 리스트인 경우 설정
    public void setDataPageList(List<T> dataList, Long totalCount) {
        this.data = (T) new DataList<>(totalCount, dataList);
    }

    // DataList 클래스 정의
    @Getter
    @AllArgsConstructor
    public static class DataList<T> {
    	private Long totalCount;
        private List<T> list;
    }
    
}
