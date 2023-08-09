package com.core.module.book.dao;

import java.util.List;

import com.core.module.book.vo.BookDataVo;


public interface BookDataDao<T> {

	void bookIndexing(String indexNm, List<T> documents, Class<T> bookDataVo);


}
