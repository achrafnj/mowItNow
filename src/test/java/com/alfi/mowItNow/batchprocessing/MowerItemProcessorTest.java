package com.alfi.mowItNow.batchprocessing;

import com.alfi.mowItNow.model.Mower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MowerItemProcessorTest {

    private MowerItemProcessor mowerItemProcessor;

    @BeforeEach
    void setUp() {
        mowerItemProcessor = new MowerItemProcessor();
    }

    @Test
    void testProcessSingleMower() {
        String inputLine = "5 5 1 2 N GAGAGAGAA";
        List<Mower> mowers = mowerItemProcessor.process(inputLine);
        assertNotNull(mowers);
        assertEquals(1, mowers.size());

        Mower mower = mowers.get(0);
        assertEquals(1, mower.getX());
        assertEquals(3, mower.getY());
        assertEquals('N', mower.getOrientation());
    }

    @Test
    void testProcessMultipleMowers() {
        String inputLine = "5 5 1 2 N GAGAGAGAA 3 3 E AADAADADDA";
        List<Mower> mowers = mowerItemProcessor.process(inputLine);
        assertNotNull(mowers);
        assertEquals(2, mowers.size());

        Mower firstMower = mowers.get(0);
        assertEquals(1, firstMower.getX());
        assertEquals(3, firstMower.getY());
        assertEquals('N', firstMower.getOrientation());

        Mower secondMower = mowers.get(1);
        assertEquals(5, secondMower.getX());
        assertEquals(1, secondMower.getY());
        assertEquals('E', secondMower.getOrientation());
    }

    @Test
    void testMowerOutOfBounds() {
        String inputLine = "5 5 1 2 N GAGAGAGAA 3 3 E AADAADADDA 0 0 S AAAAA";
        List<Mower> mowers = mowerItemProcessor.process(inputLine);
        assertNotNull(mowers);
        assertEquals(3, mowers.size());

        Mower thirdMower = mowers.get(2);
        assertEquals(0, thirdMower.getX());
        assertEquals(0, thirdMower.getY());
        assertEquals('S', thirdMower.getOrientation());
    }
}
