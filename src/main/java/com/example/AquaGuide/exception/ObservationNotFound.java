package com.example.AquaGuide.exception;

import jakarta.persistence.EntityNotFoundException;

public class ObservationNotFound extends EntityNotFoundException {
    public ObservationNotFound(String message) {
        super(message);
    }
}
