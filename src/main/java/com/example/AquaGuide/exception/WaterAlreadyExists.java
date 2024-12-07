package com.example.AquaGuide.exception;

import jakarta.persistence.EntityExistsException;

public class WaterAlreadyExists extends EntityExistsException {
    public WaterAlreadyExists(String message) {
        super(message);
    }
}
