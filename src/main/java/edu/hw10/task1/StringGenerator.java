package edu.hw10.task1;

import lombok.Getter;
import lombok.Setter;
import static edu.hw10.task1.RandomObjectGenerator.RANDOM;

@Setter
@Getter
public class StringGenerator implements MinMaxGenerator<String> {
    private static final int DEFAULT_LENGTH = 10;

    @Override
    public String generate() {
        int length = RANDOM.nextInt(DEFAULT_LENGTH);
        return generateRandomString(length);
    }

    @Override
    public String generate(int min, int max) {
        if (max - min + 1 <= 0) {
            return generateRandomString(DEFAULT_LENGTH);
        }
        int length = RANDOM.nextInt(max - min + 1) + min;
        return generateRandomString(length);
    }

    private String generateRandomString(int length) {
        return RANDOM.ints('a', 'z' + 1)
            .limit(length)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }
}
