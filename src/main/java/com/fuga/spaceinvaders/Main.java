package com.fuga.spaceinvaders;

import com.fuga.spaceinvaders.model.DetectionResult;
import com.fuga.spaceinvaders.model.RadarImage;
import com.fuga.spaceinvaders.model.SpaceInvader;
import com.fuga.spaceinvaders.util.FileReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final String KNOWN_INVADERS_FILE_PATH = "src/main/resources/known_invaders.txt";
    private static final String RADAR_IMAGE_FILE_PATH = "src/main/resources/radar_image.txt";
    private static final double MATCH_THRESHOLD = 0.8;

    public static void main(String[] args) {
        try {
            List<SpaceInvader> knownInvaders = FileReader.readPatterns(KNOWN_INVADERS_FILE_PATH)
                    .stream()
                    .map(SpaceInvader::new)
                    .filter(SpaceInvader::hasActivePixels)
                    .collect(Collectors.toList());

            SpaceInvaderDetector detector = new SpaceInvaderDetector(knownInvaders, MATCH_THRESHOLD);

            RadarImage radarImage = new RadarImage(FileReader.readRadarImage(RADAR_IMAGE_FILE_PATH));

            List<DetectionResult> detectionResults = detector.detect(radarImage);

            System.out.println("Found " + detectionResults.size() + " potential space invaders:");
            for (DetectionResult detectionResult : detectionResults) {
                System.out.printf("Space invader detected at position (%d, %d) with %.2f%% match%n",
                        detectionResult.startX(),
                        detectionResult.startY(),
                        detectionResult.matchPercentage() * 100);
            }
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
            System.exit(1);
        }
    }
} 