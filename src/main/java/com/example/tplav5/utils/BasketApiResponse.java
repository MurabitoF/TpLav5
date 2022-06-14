package com.example.tplav5.utils;

import com.example.tplav5.model.BasketBallData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketApiResponse {

    private List<BasketBallData> matches;
}
