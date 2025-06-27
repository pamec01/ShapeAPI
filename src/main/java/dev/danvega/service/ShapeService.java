package dev.danvega.service;

import dev.danvega.model.Shape;
import dev.danvega.util.ShapeCalculator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ShapeService {

    private final RestTemplate restTemplate;

    public ShapeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Shape fetchAndCompleteShape() {
        Shape shape = restTemplate.postForObject(
                "https://medoro.cz/task/api/shape",
                null,
                Shape.class
        );

        return ShapeCalculator.calculateMissingAttribute(shape);
    }
}
