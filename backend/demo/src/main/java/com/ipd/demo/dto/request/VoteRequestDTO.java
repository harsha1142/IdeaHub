package com.ipd.demo.dto.request;


public class VoteRequestDTO {

    private Long ideaId;

    private Long userId;

    public VoteRequestDTO() {}

    public Long getIdeaId()
    {
        return ideaId;
    }

    public void setIdeaId(Long ideaId) {
        this.ideaId = ideaId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
}
