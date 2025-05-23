package com.fuga.spaceinvaders.model;

/**
 * Represents a space invader pattern that can be detected in radar images.
 */
public class SpaceInvader extends AbstractImagePattern {
    private final int totalActivePixels;

    public SpaceInvader(String[] pattern) {
        super(pattern);
        this.totalActivePixels = calcTotalActivePixels();
    }

    public int getTotalActivePixels() {
        return totalActivePixels;
    }

    public boolean hasActivePixels() {
        return totalActivePixels != 0;
    }

    private int calcTotalActivePixels() {
        int activePixels = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (pattern[i][j]) {
                    activePixels++;
                }
            }
        }
        return activePixels;
    }
}