package com.fuga.spaceinvaders;

import com.fuga.spaceinvaders.model.DetectionResult;
import com.fuga.spaceinvaders.model.RadarImage;
import com.fuga.spaceinvaders.model.SpaceInvader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SpaceInvaderDetectorTest {
    private SpaceInvaderDetector detector;
    private SpaceInvader invader1;

    @BeforeEach
    void setUp() {
        invader1 = new SpaceInvader(new String[]{
            "--o--",
            "-ooo-",
            "--o--"
        });
        detector = new SpaceInvaderDetector(List.of(invader1), 0.8);
    }

    @Test
    void shouldDetectExactMatch() {
        // Given
        RadarImage radar = new RadarImage(new String[]{
            "--o--",
            "-ooo-",
            "--o--"
        });

        // When
        List<DetectionResult> detectionResults = detector.detect(radar);

        // Then
        assertThat(detectionResults).hasSize(1);
        DetectionResult detectionResult = detectionResults.getFirst();
        assertThat(detectionResult.startX()).isEqualTo(0);
        assertThat(detectionResult.startY()).isEqualTo(0);
        assertThat(detectionResult.matchPercentage()).isEqualTo(1.0);
    }

    @Test
    void shouldDetectPartialMatch() {
        // Given
        RadarImage radar = new RadarImage(new String[]{
                "--o--",
                "-ooo-",
                "-----"
        });

        // When
        List<DetectionResult> detectionResults = detector.detect(radar);

        // Then
        assertThat(detectionResults).hasSize(1);
        DetectionResult detectionResult = detectionResults.getFirst();
        assertThat(detectionResult.matchPercentage()).isLessThan(1.0);
    }

    @Test
    void shouldNotDetectBelowThreshold() {
        // Given
        RadarImage radar = new RadarImage(new String[]{
            "--o--",
            "-----",
            "-----"
        });

        // When
        List<DetectionResult> detectionResults = detector.detect(radar);

        // Then
        assertThat(detectionResults).isEmpty();
    }

    @Test
    void shouldDetectMultipleInvaders() {
        // Given
        RadarImage radar = new RadarImage(new String[]{
            "--o----o--",
            "-ooo--ooo-",
            "--o----o--"
        });

        // When
        List<DetectionResult> detectionResults = detector.detect(radar);

        // Then
        assertThat(detectionResults).hasSize(2);
        assertThat(detectionResults.get(0).startX()).isEqualTo(0);
        assertThat(detectionResults.get(1).startX()).isEqualTo(5);
    }

    @Test
    void shouldSkipInvaderLargerThanRadarImage() {
        // Given
        RadarImage radar = new RadarImage(new String[]{
            "--o-",
            "-oo-"
        });

        SpaceInvaderDetector detector = new SpaceInvaderDetector(List.of(invader1), 0.8);

        // When
        List<DetectionResult> detections = detector.detect(radar);

        // Then
        assertThat(detections).isEmpty();
    }

    @Test
    void shouldHandleEmptyInvaderPattern() {
        // Given
        SpaceInvader emptyInvader = new SpaceInvader(new String[]{
            "----",
            "----"
        });

        SpaceInvaderDetector detector = new SpaceInvaderDetector(List.of(emptyInvader), 0.8);

        RadarImage radar = new RadarImage(new String[]{
            "--o--",
            "-ooo-"
        });

        // When
        List<DetectionResult> detections = detector.detect(radar);

        // Then
        assertThat(detections).isEmpty();
    }
} 