package com.mcnc.bizmob.web.domain.menu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcnc.bizmob.web.domain.menu.dto.request.DeleteMenuRequest;
import com.mcnc.bizmob.web.domain.menu.dto.request.GetMenuDetailRequest;
import com.mcnc.bizmob.web.domain.menu.dto.request.RegisterMenuRequest;
import com.mcnc.bizmob.web.domain.menu.dto.request.UpdateMenuRequest;
import com.mcnc.bizmob.web.domain.menu.dto.response.DeleteMenuResponse;
import com.mcnc.bizmob.web.domain.menu.dto.response.GetMenuDetailResponse;
import com.mcnc.bizmob.web.domain.menu.dto.response.GetMenuListResponse;
import com.mcnc.bizmob.web.domain.menu.dto.response.RegisterMenuResponse;
import com.mcnc.bizmob.web.domain.menu.dto.response.UpdateMenuResponse;
import com.mcnc.bizmob.web.domain.menu.service.MenuService;
import com.mcnc.bizmob.web.global.dto.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/menu")
@Tag(name = "[SMAD3xxx] 메뉴 관련 Controller", description = "메뉴와 관련된 기능을 수행합니다.")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@PostMapping("/list")
    @Operation(summary = "[SMAD3001] 메뉴 관리 목록 조회", description = "메뉴 관리 목록을 조회합니다.")
    public ResponseEntity<ApiResponse<GetMenuListResponse>> getMenuList() {
		ApiResponse<GetMenuListResponse> response = new ApiResponse<>(true, "메뉴 관리 목록을 조회 성공"); 
		
		response.setData(menuService.getMenuList());
		
		return ResponseEntity.ok(response);
    }
	
	@PostMapping("/detail")
    @Operation(summary = "[SMAD3002] 메뉴 상세 정보 조회", description = "메뉴 상세 정보를 조회합니다.")
    public ResponseEntity<ApiResponse<GetMenuDetailResponse>> getMenuDetail(@Valid @RequestBody GetMenuDetailRequest request) {
		ApiResponse<GetMenuDetailResponse> response = new ApiResponse<>(true, "메뉴 상세 정보 조회 성공"); 
		
		response.setData(menuService.getMenuDetail(request));
		
		return ResponseEntity.ok(response);
    }
	
	@PostMapping("/register")
    @Operation(summary = "[SMAD3003] 메뉴 등록", description = "메뉴를 등록합니다.")
    public ResponseEntity<ApiResponse<RegisterMenuResponse>> registerMenu(@Valid @RequestBody RegisterMenuRequest request) {
		ApiResponse<RegisterMenuResponse> response = new ApiResponse<>(true, "메뉴 등록 성공"); 
		
		response.setData(menuService.registerMenu(request));
		
		return ResponseEntity.ok(response);
    }
	
	@PostMapping("/update")
    @Operation(summary = "[SMAD3004] 메뉴 수정", description = "메뉴를 수정합니다.")
    public ResponseEntity<ApiResponse<UpdateMenuResponse>> updateMenu(@Valid @RequestBody UpdateMenuRequest request) {
		ApiResponse<UpdateMenuResponse> response = new ApiResponse<>(true, "메뉴 수정 성공"); 
		
		response.setData(menuService.updateMenu(request));
		
		return ResponseEntity.ok(response);
    }
	

	@PostMapping("/delete")
    @Operation(summary = "[SMAD3005] 메뉴 삭제", description = "메뉴를 삭제합니다. (물리적 삭제)")
    public ResponseEntity<ApiResponse<DeleteMenuResponse>> deleteMenu(@Valid @RequestBody DeleteMenuRequest request) {
		ApiResponse<DeleteMenuResponse> response = new ApiResponse<>(true, "메뉴 삭제 성공"); 
		
		response.setData(menuService.deleteMenu(request));
		
		return ResponseEntity.ok(response);
    }
	
	@PostMapping("/side/list")
    @Operation(summary = "[SMAD3005] 사이드 바 메뉴 관리 목록 조회", description = "사이드 바 메뉴 관리 목록을 조회합니다.")
    public ResponseEntity<ApiResponse<GetMenuListResponse>> getSideMenuList() {
		ApiResponse<GetMenuListResponse> response = new ApiResponse<>(true, "사이드 바 메뉴 관리 목록을 조회 성공"); 
		
		response.setData(menuService.getSideMenuList());
		
		return ResponseEntity.ok(response);
    }
}
