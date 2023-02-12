package pfe.models;

import pfe.annotations.Rest;

@Rest( dataSource = "dtSrcTodos")
public class Todo {
    private int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    @Override
    public String toString() {
        return id + " | " + content;
    }
}
