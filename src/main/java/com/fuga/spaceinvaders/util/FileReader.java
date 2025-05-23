package com.fuga.spaceinvaders.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading space invader patterns and radar images from files.
 */
public class FileReader {
    
    /**
     * Reads a pattern from a file, handling multiple patterns separated by empty lines.
     * @param filePath path to the file containing patterns
     * @return List of string arrays, each representing a pattern
     * @throws IOException if a file cannot be read
     */
    public static List<String[]> readPatterns(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));
        List<String[]> patterns = new ArrayList<>();
        List<String> currentPattern = new ArrayList<>();

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                if (!currentPattern.isEmpty()) {
                    patterns.add(currentPattern.toArray(new String[0]));
                    currentPattern.clear();
                }
            } else {
                currentPattern.add(line);
            }
        }

        if (!currentPattern.isEmpty()) {
            patterns.add(currentPattern.toArray(new String[0]));
        }

        return patterns;
    }

    /**
     * Reads a radar image from a file.
     * @param filePath path to the file containing the radar image
     * @return Array of strings representing the radar image
     * @throws IOException if a file cannot be read
     */
    public static String[] readRadarImage(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath)).toArray(new String[0]);
    }
} 