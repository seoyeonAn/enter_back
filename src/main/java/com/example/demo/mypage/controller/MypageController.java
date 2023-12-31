package com.example.demo.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mypage.dto.DiaryDTO;
import com.example.demo.mypage.dto.EnterlistDTO;
import com.example.demo.mypage.service.MypageService;
import com.example.demo.users.dto.UsersDTO;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class MypageController {
	//@Autowired 
	private final MypageService mypageService;
	
	//@Autowired
	private final BCryptPasswordEncoder encodePassword;
	
	//public MypageController() {}
	
	@GetMapping("/mypage/{email}") 
	public Map<String, Object> mypageList(@PathVariable("email") String email){
		System.out.println("test:" + email );
		Map<String, Object> map = new HashMap<>();
		
		map.put("userList", mypageService.userList(email));
		map.put("diaryList", mypageService.diaryList(email));
		map.put("enterList", mypageService.enterList(email));
		return map;
	}
	
	@PostMapping("/mypage/updateuser")
	public void updateUser(@RequestBody UsersDTO usersDTO) {
		usersDTO.setPassword(encodePassword.encode(usersDTO.getPassword()));
		
		mypageService.updateUserProcess(usersDTO);		
	}
	
	@PostMapping("/mypage/diarywrite")
	public void diaryWrite( DiaryDTO diaryDTO, String email) {		
		diaryDTO.getUsersDTO().setEmail(email);
		mypageService.diaryWriteProcess(diaryDTO);		
	}
	
	@PutMapping("/mypage/{enterSeq}/{completed}")
	public void updateEnter(@PathVariable("enterSeq") long enterSeq, @PathVariable("completed") long completed){
		EnterlistDTO dto = new EnterlistDTO();
		dto.setEnterSeq(enterSeq);
		dto.setCompleted(completed == 0 ? 1 : 0);
		mypageService.updateEnterList(dto);
	}
}





