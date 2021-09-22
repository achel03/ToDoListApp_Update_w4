package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "=============== Create item Section ==============\n");
		System.out.print(">>> 할 일의 이름을 입력해주세요 : ");
		
		title = sc.next();
		sc.nextLine(); // 개행 문자 제거
		if (list.isDuplicate(title)) {
			System.out.printf("ERROR: 이미 존재하는 항목의 이름입니다!!!");
			return;
		}
		
		System.out.print(">>> 할 일의 내용을 입력해주세요 : ");
		desc = sc.nextLine();
		System.out.println("항목이 추가되었습니다 :)");
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		
		System.out.print("\n"
				+ "=============== Delete Item Section ==============\n"
				+ ">>> 삭제할 할 일의 이름을 입력해주세요 : \n"
				+ "\n");
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
		sc.close();
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "=============== Edit Item Section ==============\n"
				+ ">>> 수정하고 싶은 할 일의 이름을 입력해주세요 : \n"
				+ "\n");
		String title = sc.next().trim();
		sc.nextLine(); // 개행 문자 제거
		if (!l.isDuplicate(title)) {
			System.out.println("ERROR: 해당 항목은 존재하지 않습니다!!!");
			return;
		}

		System.out.print(">>> 새로운 할 일의 이름을 입력해주세요 : ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("ERROR: 이미 존재하는 항목의 이름입니다!!!");
			return;
		}
		
		System.out.print(">>> 새로운 할 일의 내용을 입력해주세요 : ");
		String new_description = sc.next().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("항목이 수정되었습니다 :)");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println();
		System.out.println(".====================TODOLIST=====================.");
        System.out.println("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
        System.out.println("|                                                 |");
		for (TodoItem item : l.getList()) {
			System.out.println("|	[ TODO: " + item.getTitle() + "] " + item.getDesc());
		}
		System.out.println("|                                                 |");
		System.out.println("|.................................................|");
        System.out.println(".=================================================.");
        System.out.println();
        System.out.println();
        System.out.println();
	}
}
