package edu.hw3.task5;

import java.util.Comparator;

public record Contact(String firstName, String lastName) {

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Contact(String name) {
        this(name.split(" ")[0], (name.split(" ").length > 1 ? name.split(" ")[1] : null));
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
