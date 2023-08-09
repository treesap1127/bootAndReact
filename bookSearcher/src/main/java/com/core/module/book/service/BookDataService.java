package com.core.module.book.service;

import java.util.List;


import com.core.module.book.vo.BookDataVo;

public interface BookDataService<T> {

	List<BookDataVo> bookData();

    void bookIndexing(String indexName, List<T> documents, Class<T> bookDataVo);

   
}
