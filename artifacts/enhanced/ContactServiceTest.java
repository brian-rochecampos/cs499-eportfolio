package contact;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    @Test
    void testAddAndGetContact() {
        // Arrange: create service and a valid contact
        ContactService service = new ContactService();
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main St");

        // Act: add contact to service
        service.addContact(contact);

        // Assert: verify the contact can be retrieved correctly
        assertEquals(contact, service.getContact("123"));
    }

    @Test
    void testDeleteContact() {
        // Arrange: create service and add a contact
        ContactService service = new ContactService();
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);

        // Act: delete the contact
        service.deleteContact("123");

        // Assert: contact should no longer exist
        assertNull(service.getContact("123"));
    }

    @Test
    void testUpdateContact() {
        // Arrange: create service and add a contact
        ContactService service = new ContactService();
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);

        // Act: update all mutable fields
        service.updateFirstName("123", "Jane");
        service.updateLastName("123", "Smith");
        service.updatePhone("123", "0987654321");
        service.updateAddress("123", "456 Elm St");

        // Retrieve updated contact
        Contact updated = service.getContact("123");

        // Assert: verify all updates were applied
        assertEquals("Jane", updated.getFirstName());
        assertEquals("Smith", updated.getLastName());
        assertEquals("0987654321", updated.getPhone());
        assertEquals("456 Elm St", updated.getAddress());
    }

    @Test
    void testAddDuplicateID() {
        // Arrange: create two contacts with the same ID
        ContactService service = new ContactService();
        Contact contact1 = new Contact("123", "John", "Doe", "1234567890", "123 Main St");
        Contact contact2 = new Contact("123", "Jane", "Smith", "0987654321", "456 Elm St");

        // Act: add the first contact
        service.addContact(contact1);

        // Assert: adding a duplicate ID should throw exception
        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact2));
    }
}