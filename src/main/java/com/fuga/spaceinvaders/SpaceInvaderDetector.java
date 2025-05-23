package com.fuga.spaceinvaders;

import com.fuga.spaceinvaders.model.DetectionResult;
import com.fuga.spaceinvaders.model.RadarImage;
import com.fuga.spaceinvaders.model.SpaceInvader;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvaderDetector {
    private final List<SpaceInvader> knownInvaders;
    private final double matchThreshold;

    public SpaceInvaderDetector(List<SpaceInvader> knownInvaders, double matchThreshold) {
        this.knownInvaders = knownInvaders;
        this.matchThreshold = matchThreshold;
    }

    /**
     * Detects space invaders in the given radar image.
     *
     * @param radarImage The radar image to analyze
     * @return List of detected space invaders with their locations
     */
    public List<DetectionResult> detect(RadarImage radarImage) {
        List<DetectionResult> detectionResults = new ArrayList<>();
        boolean[][] image = radarImage.getPattern();
        for (SpaceInvader invader : knownInvaders) {
            boolean isValidRadarImageAndInvader = invader.getHeight() <= radarImage.getHeight() || invader.getWidth() <= radarImage.getWidth();
            if (isValidRadarImageAndInvader) {
                boolean[][] invaderPattern = invader.getPattern();
                // Slide the invader pattern over the image
                for (int startY = 0; startY <= radarImage.getHeight() - invader.getHeight(); startY++) {
                    for (int startX = 0; startX <= radarImage.getWidth() - invader.getWidth(); startX++) {
                        double matchPercentage = calculateMatch(image, invaderPattern, startX, startY, invader.getTotalActivePixels());
                        if (matchPercentage >= matchThreshold) {
                            detectionResults.add(new DetectionResult(startX, startY, invader, matchPercentage));
                        }
                    }
                }
            }
        }

        return detectionResults;
    }

    /**
     * Calculates the match percentage between the pattern and the image at the given position.
     */
    private double calculateMatch(boolean[][] image, boolean[][] invaderPattern, int startX, int startY, int totalActivePixels) {
        int matches = 0;
        for (int y = 0; y < invaderPattern.length; y++) {
            for (int x = 0; x < invaderPattern[0].length; x++) {
                if (invaderPattern[y][x] && image[startY + y][startX + x] == invaderPattern[y][x]) {
                    matches++;
                }
            }
        }
        return (double) matches / totalActivePixels;
    }
} 