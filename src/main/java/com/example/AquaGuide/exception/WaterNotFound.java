package com.example.AquaGuide.exception;

import jakarta.persistence.EntityNotFoundException;

public class WaterNotFound extends EntityNotFoundException {
    public WaterNotFound(String message) {
        super(message);
    }
}
