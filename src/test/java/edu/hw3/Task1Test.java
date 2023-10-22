package edu.hw3;

import edu.hw3.task1.AtbashScrambler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("1st sample test")
    void firstSampleTest() {
        // given
        String stringToEncrypt = "Hello world!";

        // when
        AtbashScrambler atbashScrambler = new AtbashScrambler();
        String result = atbashScrambler.atbash(stringToEncrypt);

        // then
        assertThat(result).isEqualTo("Svool dliow!");
    }

    @Test
    @DisplayName("2nd sample test")
    void secondSampleTest() {
        // given
        String stringToEncrypt =
            "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";

        // when
        AtbashScrambler atbashScrambler = new AtbashScrambler();
        String result = atbashScrambler.atbash(stringToEncrypt);

        // then
        assertThat(result).isEqualTo(
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
    }
}
