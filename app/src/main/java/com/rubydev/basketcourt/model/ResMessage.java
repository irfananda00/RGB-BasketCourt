package com.rubydev.basketcourt.model;

import java.util.List;

/**
 * Created by irfanandarafifsatrio on 11/1/17.
 */

public class ResMessage {

    /**
     * code : 200
     * message : success
     * content : null
     */

    private String code;
    private String message;
    private List<Score> content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Score> getContent() {
        return content;
    }

    public void setContent(List<Score> content) {
        this.content = content;
    }
}
