package com.example.demo.info.service;

import java.util.List;
import com.example.demo.info.dto.InfoDTO;
import com.example.demo.info.dto.PageDTO;

public interface InfoService {
	public long countProcess(String searchKey, String searchWord);
	public List<InfoDTO> listProcess(PageDTO pv);
	
	public List<InfoDTO> museumList(PageDTO pv);
	
	public InfoDTO contentProcess(long infoSeq);
}
