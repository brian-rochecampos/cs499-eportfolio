package contact;

import java.util.HashMap;
import java.util.Map;

/*
 * ContactService for adding, deleting, and updating contacts.
 * This class manages Contact objects in memory using a HashMap.
 */
public class ContactService {

    // Stores contacts using contactID as the key for quick lookup
    private Map<String, Contact> contacts = new HashMap<>();

    // Adds a new contact to the system
    public void addContact(Contact contact) {

        // Ensure contact ID is unique
        if (contacts.containsKey(contact.getContactID()))
            throw new IllegalArgumentException("Contact ID already exists");

        // Store the contact in the map
        contacts.put(contact.getContactID(), contact);
    }

    // Deletes a contact by ID
    public void deleteContact(String contactID) {

        // Removes the contact if it exists (no exception if not found)
        contacts.remove(contactID);
    }

    // Updates the first name of a contact
    public void updateFirstName(String contactID, String newFirstName) {

        // Retrieve contact by ID
        Contact c = contacts.get(contactID);

        // If contact exists, update first name
        if (c != null) c.setFirstName(newFirstName);
    }

    // Updates the last name of a contact
    public void updateLastName(String contactID, String newLastName) {

        // Retrieve contact by ID
        Contact c = contacts.get(contactID);

        // If contact exists, update last name
        if (c != null) c.setLastName(newLastName);
    }

    // Updates the phone number of a contact
    public void updatePhone(String contactID, String newPhone) {

        // Retrieve contact by ID
        Contact c = contacts.get(contactID);

        // If contact exists, update phone number
        if (c != null) c.setPhone(newPhone);
    }

    // Updates the address of a contact
    public void updateAddress(String contactID, String newAddress) {

        // Retrieve contact by ID
        Contact c = contacts.get(contactID);

        // If contact exists, update address
        if (c != null) c.setAddress(newAddress);
    }

    // Retrieves a contact by ID
    public Contact getContact(String contactID) {

        // Returns the contact if found, otherwise null
        return contacts.get(contactID);
    }
}