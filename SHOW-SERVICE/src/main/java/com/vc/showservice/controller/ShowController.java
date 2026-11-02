package com.vc.showservice.controller;

import com.vc.common.dto.ShowDTO;
import com.vc.showservice.dto.ShowRequest;
import com.vc.showservice.services.IShowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/shows")
@RequiredArgsConstructor
public class ShowController {

    private final IShowService showService;

    @PostMapping
    public ResponseEntity<ShowDTO> createShow(@RequestBody ShowRequest request) {
        log.info("Creating show with request: {}", request);
        return ResponseEntity.status(HttpStatus.CREATED).body(showService.createShow(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowDTO> getShowById(@PathVariable Long id) {
        return ResponseEntity.ok(showService.getShowById(id));
    }

    @GetMapping
    public ResponseEntity<List<ShowDTO>> getAllShows() {

        return ResponseEntity.ok(showService.getAllShows());
    }
}
