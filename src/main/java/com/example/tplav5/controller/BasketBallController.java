package com.example.tplav5.controller;

import com.example.tplav5.model.BasketBallData;
import com.example.tplav5.service.BasketBallService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/basket")
public class BasketBallController {

    private final BasketBallService basketBallService;

    @Autowired
    public BasketBallController(BasketBallService basketBallService) {
        this.basketBallService = basketBallService;
    }

    @GetMapping("/live")
    @Operation(summary = "Find matches live")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "Found matches live", content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = BasketBallData.class))
            )),
            @ApiResponse(responseCode = "204", description = "No live matches", content = {@Content()}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content()})
    })
    public ResponseEntity<List<BasketBallData>> getLiveMatches() throws IOException, InterruptedException {
        List<BasketBallData> matches = basketBallService.getLiveMatches();

        if (matches.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(matches);
    }

    @GetMapping("/failed")
//    @CircuitBreaker(name = "service1", fallbackMethod = "service1Fallback")
    @Retry(name = "service1", fallbackMethod = "service1Fallback")
    public String failedRequest() throws IOException, InterruptedException {
        return basketBallService.getDataFromApiOff();
    }

    public String service1Fallback(Exception e) {
        return "El servicio web no esta disponible";
    }
}
