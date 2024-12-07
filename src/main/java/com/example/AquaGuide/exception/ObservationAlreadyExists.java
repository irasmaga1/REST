package com.example.AquaGuide.exception;

import jakarta.persistence.EntityExistsException;

public class ObservationAlreadyExists extends EntityExistsException {
    public ObservationAlreadyExists(String message) {
        super(message);
    }
}
