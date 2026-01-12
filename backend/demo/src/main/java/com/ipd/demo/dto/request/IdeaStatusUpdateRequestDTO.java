package com.ipd.demo.dto.request;

import com.ipd.demo.enums.IdeaStatus;

public class IdeaStatusUpdateRequestDTO {

    private IdeaStatus status;

    public IdeaStatus getStatus(){
        return status;
    }

    public void setStatus(IdeaStatus status) {
        this.status = status;
    }
}
