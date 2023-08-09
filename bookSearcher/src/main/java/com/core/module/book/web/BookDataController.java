package com.core.module.book.web;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.module.book.service.BookDataService;
import com.core.module.book.vo.BookDataVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookDataController {
	private final BookDataService bookDataService;
	
	@RequestMapping("/indexing")
	public <T> void bookData() {
		System.out.println("DataSet Start");
		List<BookDataVo> bookDataList=bookDataService.bookData();
		try {
		    Class<?> bookDataClass = Class.forName("com.core.module.book.vo.BookDataVo");
			System.out.println("DataSet Complete");

			System.out.println("indexing Start");
			bookDataService.bookIndexing("2009",bookDataList, bookDataClass);
			System.out.println("indexing Complete");
			
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
	}
}
