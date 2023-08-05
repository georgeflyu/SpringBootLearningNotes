package com.learning.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import org.springframework.stereotype.Service;

@Service
public class CalculateService {

    public static final Map<String, BiFunction<Integer, Integer, Integer>> CALCULATE_MAP = new HashMap<>();

    static {
        CALCULATE_MAP.put("plus", Integer::sum);
        CALCULATE_MAP.put("minus", (value1, value2) -> value1 - value2);
        CALCULATE_MAP.put("multiply", (value1, value2) -> value1 * value2);
        CALCULATE_MAP.put("divide", (value1, value2) -> value1 / value2);
    }

    public Integer calValue(String calMethodName, Integer value1, Integer value2) {
        return Optional.ofNullable(CALCULATE_MAP.get(calMethodName)).map(item -> item.apply(value1, value2))
            .orElseThrow(() -> new RuntimeException("no such calculate method in calculateMap"));
    }
}
