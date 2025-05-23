# Space Invaders Detector

A Java application that detects space invaders in noisy radar images using pattern matching algorithms. The program is designed to identify known space invader patterns in radar images while handling noise and potential distortions.

## Features

- Pattern matching with configurable threshold for noise tolerance
- Support for multiple space invader patterns
- Robust input validation and error handling
- File-based loading of patterns and radar images
- Comprehensive test coverage

## Requirements

- Java 11 or higher
- Maven 3.6 or higher

## Building the Project

```bash
mvn clean install
```

## Running Tests

```bash
mvn test
```

## Input Format

The application accepts two types of inputs:

1. Space Invader Patterns
2. Radar Images

Both inputs should use the following characters:
- `o` - represents an active pixel
- `-` - represents an empty space

### Known Space Invader Patterns

### Invader 1
```
--o-----o--
---o---o---
--ooooooo--
-oo-ooo-oo-
ooooooooooo
o-ooooooo-o
o-o-----o-o
---oo-oo---
```

### Invader 2
```
---oo---
--oooo--
-oooooo-
oo-oo-oo
oooooooo
--o--o--
-o-oo-o-
o-o--o-o
```

### Example Radar Image

*Note: The radar image is very long, so this is just a placeholder. Please refer to the original file for full content.*

## Technical Details

- Uses a sliding window approach for pattern matching
- Implements noise tolerance through configurable match threshold
- Validates input patterns and radar images for consistency
- Provides detailed match information including position and match percentage

## Error Handling

The application includes comprehensive error handling for:
- Invalid input characters
- Inconsistent pattern dimensions
- Null or empty inputs
- Pattern size vs radar image size validation
