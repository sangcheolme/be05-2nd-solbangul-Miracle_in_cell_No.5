package com.solbangul.user.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.solbangul.room.domain.dto.RoomListResponseDto;
import com.solbangul.room.service.RoomService;
import com.solbangul.user.domain.dto.AuthenticatedUserDto;
import com.solbangul.user.domain.dto.CustomUserDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

	private final RoomService roomService;

	@GetMapping("/")
	public String mainRoomList(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		AuthenticatedUserDto authenticatedUserDto = customUserDetails.getAuthenticatedUser();
		model.addAttribute("user", authenticatedUserDto);

		// 모든 room 출력
		List<RoomListResponseDto> list = roomService.findAll();
		model.addAttribute("roomList", list);
		for (RoomListResponseDto response : list) {
			log.info("debug >>> room : {}", response);
		}
		return "main";
	}

	@GetMapping("/admin")
	public String test() {
		return "test";
	}

}
