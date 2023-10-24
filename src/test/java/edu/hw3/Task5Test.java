package edu.hw3;

import edu.hw3.task5.Contact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @Test
    @DisplayName("ASC test")
    void ascendingTest() {
        // given
        String[] names = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};

        // when
        var result = Contact.parseContacts(names, "ASC");

        // then
        assertThat(result[0].getLastName()).isEqualTo("Aquinas");
        assertThat(result[1].getLastName()).isEqualTo("Descartes");
        assertThat(result[2].getLastName()).isEqualTo("Hume");
        assertThat(result[3].getLastName()).isEqualTo("Locke");
    }

    @Test
    @DisplayName("DESC test")
    void descendingTest() {
        // given
        String[] names = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};

        // when
        var result = Contact.parseContacts(names, "DESC");

        // then
        assertThat(result[0].getLastName()).isEqualTo("Gauss");
        assertThat(result[1].getLastName()).isEqualTo("Erdos");
        assertThat(result[2].getLastName()).isEqualTo("Euler");
    }

    @Test
    @DisplayName("Empty array")
    void emptyArrayTest() {
        // given
        String[] names = new String[0];

        // when
        var result = Contact.parseContacts(names, "DESC");

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("NULL array")
    void nullArrayTest() {
        // given
        String[] names = null;

        // when
        var result = Contact.parseContacts(names, "DESC");

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Only name")
    void onlyNameTest() {
        // given
        String[] names = new String[] {"Paul", "Leonhard", "Carl Gauss"};

        // when
        var result = Contact.parseContacts(names, "DESC");

        // then
        assertThat(result).isEmpty();
    }
}
