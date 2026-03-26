package contact;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/*
 * Brian Roche-Campos
 * CS 320 - Software Test, Automation QA
 * Date: 9/16/2025
 *
 * Tests for ContactService.
 */
public class ContactServiceTest {

    @Test
    void testAddAndGetContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        assertEquals(contact, service.getContact("123"));
    }

    @Test
    void testDeleteContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        service.deleteContact("123");
        assertNull(service.getContact("123"));
    }

    @Test
    void testUpdateContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);

        service.updateFirstName("123", "Jane");
        service.updateLastName("123", "Smith");
        service.updatePhone("123", "0987654321");
        service.updateAddress("123", "456 Elm St");

        Contact updated = service.getContact("123");
        assertEquals("Jane", updated.getFirstName());
        assertEquals("Smith", updated.getLastName());
        assertEquals("0987654321", updated.getPhone());
        assertEquals("456 Elm St", updated.getAddress());
    }

    @Test
    void testAddDuplicateID() {
        ContactService service = new ContactService();
        Contact contact1 = new Contact("123", "John", "Doe", "1234567890", "123 Main St");
        Contact contact2 = new Contact("123", "Jane", "Smith", "0987654321", "456 Elm St");
        service.addContact(contact1);
        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact2));
    }
}
