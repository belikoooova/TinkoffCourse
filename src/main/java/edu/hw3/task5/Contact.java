package edu.hw3.task5;

import java.util.Arrays;
import java.util.Comparator;

public class Contact {
    private final String firstName;

    private final String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Contact(String name) {
        String[] splittedName = name.split(" ");
        firstName = splittedName[0];
        lastName = splittedName.length > 1 ? splittedName[1] : null;
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
            Arrays.sort(sortedContacts, new ContactComparator().reversed());
        } else {
            Arrays.sort(sortedContacts, new ContactComparator());
        }
        return sortedContacts;
    }

    static class ContactComparator implements Comparator<Contact> {
        @Override
        public int compare(Contact o1, Contact o2) {
            String comparingParam1 = o1.lastName == null ? o1.firstName : o1.lastName;
            String comparingParam2 = o2.lastName == null ? o2.firstName : o2.lastName;
            return comparingParam1.compareTo(comparingParam2);
        }
    }
}
