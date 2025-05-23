package com.fuga.spaceinvaders.model;

import com.fuga.spaceinvaders.validation.PatternValidator;

/**
 * Abstract base class for handling 2D boolean pattern arrays.
 */
public abstract class AbstractImagePattern {
    private static final char ACTIVE_PIXEL_CHAR = 'o';
    protected final boolean[][] pattern;
    protected final int width;
    protected final int height;

    protected AbstractImagePattern(String[] input) {
        PatternValidator.validate(input);
        this.height = input.length;
        this.width = input[0].length();
        this.pattern = createPattern(input);
    }

    /**
     * Creates a boolean pattern from string array input.
     *
     * @param input Array of strings representing the pattern
     * @return 2D boolean array where true represents 'o' and false represents '-'
     */
    protected boolean[][] createPattern(String[] input) {
        boolean[][] result = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            String row = input[i];
            for (int j = 0; j < width; j++) {
                result[i][j] = row.charAt(j) == ACTIVE_PIXEL_CHAR;
            }
        }
        return result;
    }

    public boolean[][] getPattern() {
        return pattern;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
} 