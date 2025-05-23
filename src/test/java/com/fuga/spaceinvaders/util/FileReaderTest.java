package com.fuga.spaceinvaders.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileReaderTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldReadMultiplePatternsFromFile() throws IOException {
        // Given
        Path patternsFile = tempDir.resolve("patterns.txt");
        Files.writeString(patternsFile, """
            --o--
            -ooo-
            --o--

            -o-o-
            -ooo-
            -o-o-
            """);

        // When
        List<String[]> patterns = FileReader.readPatterns(patternsFile.toString());

        // Then
        assertThat(patterns).hasSize(2);
        assertThat(patterns.get(0)).containsExactly(
            "--o--",
            "-ooo-",
            "--o--"
        );
        assertThat(patterns.get(1)).containsExactly(
            "-o-o-",
            "-ooo-",
            "-o-o-"
        );
    }

    @Test
    void shouldReadRadarImageFromFile() throws IOException {
        // Given
        Path radarFile = tempDir.resolve("radar.txt");
        Files.writeString(radarFile, """
            --o--o--
            -ooooooo
            --o--o--
            """);

        // When
        String[] radarImage = FileReader.readRadarImage(radarFile.toString());

        // Then
        assertThat(radarImage).containsExactly(
            "--o--o--",
            "-ooooooo",
            "--o--o--"
        );
    }
} 