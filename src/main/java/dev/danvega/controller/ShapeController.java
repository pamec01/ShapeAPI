package dev.danvega.controller;

import dev.danvega.model.Shape;
import dev.danvega.service.ShapeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shape")
public class ShapeController {

    private final ShapeService shapeService;

    public ShapeController(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    @PostMapping("/full")
    public ResponseEntity<Shape> getCompletedShape() {
        Shape completedShape = shapeService.fetchAndCompleteShape();
        return ResponseEntity.ok(completedShape);
    }
}