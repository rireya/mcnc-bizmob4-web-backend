package com.mcnc.bizmob.web.global.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageUtils {
	
	public static Pageable getPageable(int page, int perPage) {
		page = (page == 0) ? 0 : (page - 1);
		return PageRequest.of(page, perPage);
	}
	

	public static Pageable getPageableWithSort(int page, int perPage, Direction sortType, String sortColunm) {
		page = (page == 0) ? 0 : (page - 1);
		Sort sort = Sort.by(sortType, sortColunm); 
		return PageRequest.of(page, perPage, sort);
	}
	
	 public static int[] calculateRowBounds(int pageNo, int size) {
		if(pageNo < 1) pageNo = 1;
        int startRow = (pageNo - 1) * size;
        int endRow = startRow + size; // endRow는 시작 행 + 페이지 사이즈

        return new int[]{startRow, endRow};
    }
}
