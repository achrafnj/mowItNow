package com.alfi.mowItNow.batchprocessing;

import com.alfi.mowItNow.model.Lawn;
import com.alfi.mowItNow.model.Mower;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

public class MowerItemProcessor implements ItemProcessor<String, List<Mower>> {
    private Lawn lawn;

    @Override
    public List<Mower> process(String line) {
        String[] parts = line.split(" ");

        int lawnMaxX = Integer.parseInt(parts[0]);
        int lawnMaxY = Integer.parseInt(parts[1]);
        this.lawn = new Lawn(lawnMaxX, lawnMaxY);

        List<Mower> mowers = new ArrayList<>();

        for (int i = 2; i < parts.length; i += 4) {
            int x = Integer.parseInt(parts[i]);
            int y = Integer.parseInt(parts[i + 1]);
            char orientation = parts[i + 2].charAt(0);
            String instructions = parts[i + 3];

            Mower mower = new Mower(x, y, orientation);

            executeInstructions(mower, instructions);

            mowers.add(mower);
        }

        return mowers;
    }

    private void executeInstructions(Mower mower, String instructions) {
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'G':
                    mower.setOrientation(turnLeft(mower.getOrientation()));
                    break;
                case 'D':
                    mower.setOrientation(turnRight(mower.getOrientation()));
                    break;
                case 'A':
                    moveForward(mower);
                    break;
            }
        }
    }

    private char turnLeft(char orientation) {
        switch (orientation) {
            case 'N': return 'W';
            case 'W': return 'S';
            case 'S': return 'E';
            case 'E': return 'N';
            default: return orientation;
        }
    }

    private char turnRight(char orientation) {
        switch (orientation) {
            case 'N': return 'E';
            case 'E': return 'S';
            case 'S': return 'W';
            case 'W': return 'N';
            default: return orientation;
        }
    }

    private void moveForward(Mower mower) {
        int x = mower.getX();
        int y = mower.getY();

        switch (mower.getOrientation()) {
            case 'N': y++; break;
            case 'S': y--; break;
            case 'E': x++; break;
            case 'W': x--; break;
        }

        if (lawn.isWithinBounds(x, y)) {
            mower.setX(x);
            mower.setY(y);
        } else {
            System.out.println("The lawn mower attempted to go out of bounds. Current position retained.");
        }
    }
}

