package me.itslucas.bookstore.beans;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Result {
    private boolean state;
    private String message;

    public Result(boolean state, String message) {
        this.state = state;
        this.message = message;
    }

    public boolean isState() {
        return state;
    }

    public String getMessage() {
        return message;
    }
}
