package com.fuga.spaceinvaders.model;

/**
 * Represents a detected space invader in the radar image.
 */
public record DetectionResult(int startX, int startY, SpaceInvader invader, double matchPercentage) {
}