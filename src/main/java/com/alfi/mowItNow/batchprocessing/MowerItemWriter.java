package com.alfi.mowItNow.batchprocessing;

import com.alfi.mowItNow.model.Mower;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class MowerItemWriter implements ItemWriter<List<Mower>> {

    // docker output.txt file !!!
    /*@Override
    public void write(Chunk<? extends List<Mower>> chunk) {
        String resourcesPath = Paths.get("src/main/resources").toAbsolutePath().toString();
        File outputFile = new File(resourcesPath, "output.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false))) {
            for (List<Mower> mowers : chunk.getItems()) {
                for (Mower mower : mowers) {
                    writer.write(mower.toString() + " ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to output.txt file", e);
        }
    }*/

    @Override
    public void write(Chunk<? extends List<Mower>> chunk) {
        int number = 0;
        for (List<Mower> mowers : chunk.getItems()) {
            System.out.println("=================");
            System.out.println("final position");
            for (Mower mower : mowers) {
                System.out.println("Mower "+number+": "+mower);
                number ++;
            }
            System.out.println("=================");
        }
    }
}

