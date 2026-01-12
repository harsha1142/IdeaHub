package com.ipd.demo.controller;

import com.ipd.demo.dto.request.IdeaCreateRequestDTO;
import com.ipd.demo.dto.request.IdeaStatusUpdateRequestDTO;
import com.ipd.demo.dto.request.IdeaUpdateRequestDTO;
import com.ipd.demo.dto.response.IdeaResponseDTO;
import com.ipd.demo.dto.response.IdeaSummaryResponseDTO;
import com.ipd.demo.service.IdeaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/ideas")
public class IdeaController {

    private final IdeaService ideaService;

    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }


    @PostMapping
    public ResponseEntity<IdeaResponseDTO> createIdea(
            @RequestBody IdeaCreateRequestDTO request) {
        return ResponseEntity.ok(ideaService.createIdea(request ));
    }
    @GetMapping
    public ResponseEntity<List<IdeaSummaryResponseDTO>> getAllIdeas(){
        return ResponseEntity.ok(ideaService.getAllIdeas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<IdeaResponseDTO> getIdeaById(@PathVariable Long id){
        return ResponseEntity.ok(ideaService.getIdeaById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateIdeas(
            @PathVariable("id") Long id,
            @RequestParam("userId") Long userId,
            @RequestBody IdeaUpdateRequestDTO request){
        ideaService.updateIdea(id, userId, request);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateIdeaStatus(
            @PathVariable Long id,
            @RequestBody IdeaStatusUpdateRequestDTO request){
        ideaService.updateIdeaStatus(id, request);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIdea(
            @PathVariable Long id,
            @RequestParam Long userId) {

        ideaService.deleteIdea(id, userId);
        return ResponseEntity.ok().build();
    }


}
