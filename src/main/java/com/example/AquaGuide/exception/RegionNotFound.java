package com.example.AquaGuide.exception;

import jakarta.persistence.EntityNotFoundException;

public class RegionNotFound extends EntityNotFoundException {
    public RegionNotFound(String message) {
        super(message);
    }
}
