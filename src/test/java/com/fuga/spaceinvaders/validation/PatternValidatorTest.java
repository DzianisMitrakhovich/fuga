package com.fuga.spaceinvaders.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.fuga.spaceinvaders.validation.PatternValidator.ALLOWED_CHARACTERS;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PatternValidatorTest {

    @Test
    void shouldValidateCorrectPattern() {
        String[] validPattern = {
            "--o--",
            "-ooo-",
            "--o--"
        };

        assertThatCode(() -> PatternValidator.validate(validPattern))
            .doesNotThrowAnyException();
    }

    @Test
    void shouldThrowOnNullInput() {
        assertThatThrownBy(() -> PatternValidator.validate(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input array cannot be null");
    }

    @Test
    void shouldThrowOnEmptyInput() {
        assertThatThrownBy(() -> PatternValidator.validate(new String[0]))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input array cannot be empty");
    }

    @Test
    void shouldThrowOnNullFirstRow() {
        String[] pattern = new String[]{null, "-o-"};

        assertThatThrownBy(() -> PatternValidator.validate(pattern))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("First row cannot be null or empty");
    }

    @Test
    void shouldThrowOnEmptyFirstRow() {
        String[] pattern = new String[]{"", "-o-"};

        assertThatThrownBy(() -> PatternValidator.validate(pattern))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("First row cannot be null or empty");
    }

    @Test
    void shouldThrowOnNullRow() {
        String[] pattern = {
            "--o--",
            null,
            "--o--"
        };

        assertThatThrownBy(() -> PatternValidator.validate(pattern))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Row 1 is null");
    }

    @Test
    void shouldThrowOnInconsistentRowLength() {
        String[] pattern = {
            "--o--",
            "-o-",
            "--o--"
        };

        assertThatThrownBy(() -> PatternValidator.validate(pattern))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Inconsistent row length at row 1. Expected 5 but got 3");
    }

    @ParameterizedTest
    @ValueSource(strings = {"x", "*", " ", "A"})
    void shouldThrowOnInvalidCharacters(String invalidChar) {
        String[] pattern = {
            "--o--",
            "-" + invalidChar + "o-",
            "--o--"
        };

        assertThatThrownBy(() -> PatternValidator.validate(pattern))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Invalid character '" + invalidChar + "'")
            .hasMessageContaining("Allowed characters: " + ALLOWED_CHARACTERS);
    }

    @Test
    void shouldValidateMinimalPattern() {
        String[] pattern = {"o"};

        assertThatCode(() -> PatternValidator.validate(pattern))
            .doesNotThrowAnyException();
    }
} 