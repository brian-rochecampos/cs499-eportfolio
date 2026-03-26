package contact;

import java.util.HashMap;
import java.util.Map;

/*
 * Brian Roche-Campos
 * CS 320 - Software Test, Automation QA
 * Date: 9/16/2025
 *
 * ContactService for adding, deleting, and updating contacts.
 */
public class ContactService {
    private Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getContactID()))
            throw new IllegalArgumentException("Contact ID already exists");
        contacts.put(contact.getContactID(), contact);
    }

    public void deleteContact(String contactID) {
        contacts.remove(contactID);
    }

    public void updateFirstName(String contactID, String newFirstName) {
        Contact c = contacts.get(contactID);
        if (c != null) c.setFirstName(newFirstName);
    }

    public void updateLastName(String contactID, String newLastName) {
        Contact c = contacts.get(contactID);
        if (c != null) c.setLastName(newLastName);
    }

    public void updatePhone(String contactID, String newPhone) {
        Contact c = contacts.get(contactID);
        if (c != null) c.setPhone(newPhone);
    }

    public void updateAddress(String contactID, String newAddress) {
        Contact c = contacts.get(contactID);
        if (c != null) c.setAddress(newAddress);
    }

    public Contact getContact(String contactID) {
        return contacts.get(contactID);
    }
}
