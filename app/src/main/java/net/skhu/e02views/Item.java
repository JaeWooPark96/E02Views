package net.skhu.e02views;

import java.io.Serializable;

public class Item implements Serializable {//Serializable마킹 인터페이스, 이게 있어야, Activity끼리 객체를 전달할 수 있습니다.
    String title;
    String content;

    public Item(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent(){
        return content;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content = content;
    }
}
