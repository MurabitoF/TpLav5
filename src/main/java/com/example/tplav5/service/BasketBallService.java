package com.example.tplav5.service;


import com.example.tplav5.model.BasketBallData;
import com.fasterxml.jackson.core.JsonProcessingException;


import java.io.IOException;
import java.util.List;

public interface BasketBallService {

    public List<BasketBallData> getLiveMatches() throws IOException, InterruptedException;

    public String getDataFromApiOff() throws IOException, InterruptedException;
}
