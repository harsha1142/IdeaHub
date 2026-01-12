package com.ipd.demo.dto.response;

public class IdeaSummaryResponseDTO  {
    private Long id;
    private String title;
    private String status;
    private Long voteCount;
    private Long createdById;
    private String createdByName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public long getCreatedById () {
        return createdById;
    }

    public void setCreatedById (long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName () {
        return createdByName;
    }

    public void setCreatedByName (String createdByName) {
        this.createdByName = createdByName;
    }


}
