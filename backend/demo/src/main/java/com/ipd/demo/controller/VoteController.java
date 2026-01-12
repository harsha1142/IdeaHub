package com.ipd.demo.controller;
import com.ipd.demo.dto.request.VoteRequestDTO;
import com.ipd.demo.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")

public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }
    @PostMapping
    public ResponseEntity<Void> Vote(@RequestBody VoteRequestDTO request){
        voteService.vote(request);
        return ResponseEntity.ok().build();
    }
}
