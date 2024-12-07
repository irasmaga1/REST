package com.example.AquaGuide.exception;

import jakarta.persistence.EntityExistsException;

public class RegionAlreadyExists extends EntityExistsException {
    public RegionAlreadyExists(String message) {
        super(message);
    }
}
