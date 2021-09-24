package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
		
		System.out.print("\n"
				+ "=============== Delete Item Section ==============\n"
				+ ">>> 삭제할 할 일의 이름을 입력해주세요 : \n"
				+ "\n");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
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
	
	public static void fileWriter(TodoList l) {
		try {
			Writer w = new FileWriter("todolist.txt");
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			System.out.println("항목들이 저장되었습니다 :)");
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void BuffReader(TodoList list) {
		try {
			BufferedReader r = new BufferedReader(new FileReader("todolist.txt"));
			String oneline;
			int count = 0;
			while((oneline=r.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(oneline,"##"); 
				String tit = st.nextToken();
				String des = st.nextToken();
				String cur_d = st.nextToken();
				TodoItem t = new TodoItem(tit, des);
				t.setCurrent_date(cur_d);
				list.addItem(t);
				count++;
			}
			System.out.println(count+"개의 항목들을 가져왔습니다 :)");
			r.close();
		}catch(FileNotFoundException e) {
			System.out.println("todolist.txt 파일이 없습니다.");
		}catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
