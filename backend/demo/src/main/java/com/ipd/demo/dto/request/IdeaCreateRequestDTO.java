package com.ipd.demo.dto.request;


public class IdeaCreateRequestDTO {
    private String title;

    private String description;

    private Long userId;

    public IdeaCreateRequestDTO() {}

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserid(Long userId){
        this.userId = userId;
    }
}
