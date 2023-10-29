package edu.hw3.task5;

import java.util.Arrays;

public class ContactUtilities {
    private ContactUtilities() {
    }

    public static Contact[] parseContacts(String[] names, String sortingParameter) {
        if (names == null) {
            return new Contact[0];
        }
        Contact[] sortedContacts = new Contact[names.length];
        for (int i = 0; i < names.length; ++i) {
            sortedContacts[i] = new Contact(names[i]);
        }
        if (sortingParameter.equals("DESC")) {
            Arrays.sort(sortedContacts, new Contact.ContactComparator().reversed());
        } else if (sortingParameter.equals("ASC")) {
            Arrays.sort(sortedContacts, new Contact.ContactComparator());
        } else {
            throw new IllegalArgumentException();
        }
        return sortedContacts;
    }
}
