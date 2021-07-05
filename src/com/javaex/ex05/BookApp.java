package com.javaex.ex05;

import java.util.List;
import java.util.Scanner;

public class BookApp {
	public static void main(String[] args) {
		
		BookDao bookDao = new BookDao();
		List<BookVo> bookList;
		Scanner sc = new Scanner(System.in);
		
		
		//리스트출력
		//데이터 가져오기
		bookList = bookDao.getBookList();
		//list 불러오는 loadList사용(for문으로 배열을 출력)
		loadList(bookList);
		
		//등록
		System.out.println("Insert");
		BookVo iBookVo = new BookVo("민수의 책", "민수출판", "2021-07-04");
		bookDao.BookInsert(iBookVo);
		loadList(bookList);
		
		
		//수정
		System.out.println("Update");
		BookVo uBookVo = new BookVo(3,"민수책1","민수책방","2000-02-01");
		bookDao.BookUpdate(uBookVo);
		loadList(bookList);
		
		//삭제
		System.out.println("Delete");
		bookDao.BookDelete(3);
		loadList(bookList);
		
		//Scanner
		System.out.print("Input search word: ");
		String searchWord = sc.nextLine();
		loadList(bookDao.getBookList(searchWord));
		
		
		
		
		sc.close();
		
		
	}

	//list 출력용 메소드 printList
	public static void loadList(List<BookVo> bookList) {
				
		for(int i=0;i<bookList.size();i++) {
			
			BookVo bookVo = bookList.get(i);
			System.out.println(bookVo.getBookId()+","+bookVo.getTitle()+","+bookVo.getPubs()+","+bookVo.getPubDate()+","+bookVo.getAuthorName());
			
		}
		
	}
	
	
}
