package com.fuga.spaceinvaders.validation;

import java.util.Set;

/**
 * Validator for pattern inputs in space invader detection.
 */
public class PatternValidator {
    public static final Set<Character> ALLOWED_CHARACTERS = Set.of('o', '-');

    /**
     * Validates the input pattern array and its dimensions.
     * @param input Array of strings representing the pattern
     * @throws IllegalArgumentException if the input is invalid
     */
    public static void validate(String[] input) {
        validateInput(input);
        validateCharacters(input);
        int width = input[0].length();
        validateDimensions(input, width);
    }

    private static void validateInput(String[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        if (input.length == 0) {
            throw new IllegalArgumentException("Input array cannot be empty");
        }
        if (input[0] == null || input[0].isEmpty()) {
            throw new IllegalArgumentException("First row cannot be null or empty");
        }
    }

    private static void validateCharacters(String[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == null) {
                throw new IllegalArgumentException("Row " + i + " is null");
            }
            for (int j = 0; j < input[i].length(); j++) {
                char c = input[i].charAt(j);
                if (!ALLOWED_CHARACTERS.contains(c)) {
                    throw new IllegalArgumentException(
                        "Invalid character '" + c + "' at position " + j +
                        " in row " + i + ". Allowed characters: " + ALLOWED_CHARACTERS
                    );
                }
            }
        }
    }

    private static void validateDimensions(String[] input, int expectedWidth) {
        for (int i = 0; i < input.length; i++) {
            if (input[i].length() != expectedWidth) {
                throw new IllegalArgumentException(
                    "Inconsistent row length at row " + i +
                    ". Expected " + expectedWidth + " but got " + input[i].length()
                );
            }
        }
    }
} 