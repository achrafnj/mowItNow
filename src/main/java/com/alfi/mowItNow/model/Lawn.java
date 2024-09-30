package com.alfi.mowItNow.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Lawn {
    private int maxX;
    private int maxY;

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && y >= 0 && x <= maxX && y <= maxY;
    }
}
