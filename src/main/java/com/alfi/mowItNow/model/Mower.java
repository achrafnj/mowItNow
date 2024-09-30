package com.alfi.mowItNow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Mower {
    private int x;
    private int y;
    private char orientation;

    @Override
    public String toString() {
        return String.format("%d %d %c", x, y, orientation);
    }

}

