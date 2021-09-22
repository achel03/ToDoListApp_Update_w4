package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
    private String title;
    private String desc;
    private Date current_date; // static 했을 때 저장 문제
    private SimpleDateFormat curD;

    public TodoItem(String title, String desc){
    	this.title=title;
    	this.desc=desc;
    	this.current_date=new Date();
    	this.curD = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    }
    
    public SimpleDateFormat getCurD() {
		return curD;
	}

	public void setCurD(SimpleDateFormat curD) {
		this.curD = curD;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
    	this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
    	this.desc = desc;
    }

    public Date getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(Date current_date) {
    	this.current_date = current_date;
    }
    public String toSaveString() {
    	return title+"##"+desc+"##"+curD.format(current_date)+"\n";
    }
}
