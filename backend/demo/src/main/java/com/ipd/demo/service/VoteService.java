package com.ipd.demo.service;



import com.ipd.demo.dto.request.VoteRequestDTO;

import java.util.List;

public interface VoteService {

    void vote(VoteRequestDTO request);
}