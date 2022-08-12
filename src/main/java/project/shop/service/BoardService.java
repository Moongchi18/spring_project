package project.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.shop.repository.BoardDao;

@Component
public class BoardService {
	
	@Autowired
	private BoardDao dao;
	public BoardService(BoardDao dao) {
		this.dao = dao;
	}
	
}
