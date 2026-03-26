package contact;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/*
 *
 * Tests for Contact class.
 * These tests validate constructor input validation and correct field assignment.
 */
public class ContactTest {

    @Test
    void testValidContact() {
        // Arrange & Act: create a valid contact
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main St");

        // Assert: verify all fields are correctly stored
        assertEquals("123", contact.getContactID());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    void testInvalidContactID() {
        // Assert: null contact ID should throw exception
        assertThrows(IllegalArgumentException.class, () -> 
            new Contact(null, "John", "Doe", "1234567890", "123 Main St"));

        // Assert: contact ID longer than 10 characters should throw exception
        assertThrows(IllegalArgumentException.class, () -> 
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St"));
    }

    @Test
    void testInvalidPhone() {
        // Assert: phone number must be exactly 10 digits
        assertThrows(IllegalArgumentException.class, () -> 
            new Contact("123", "John", "Doe", "123", "123 Main St"));
    }

    @Test
    void testInvalidFirstName() {
        // Assert: null first name should throw exception
        assertThrows(IllegalArgumentException.class, () -> 
            new Contact("123", null, "Doe", "1234567890", "123 Main St"));

        // Assert: first name longer than 10 characters should throw exception
        assertThrows(IllegalArgumentException.class, () -> 
            new Contact("123", "ThisNameIsTooLong", "Doe", "1234567890", "123 Main St"));
    }

    @Test
    void testInvalidAddress() {
        // Assert: null address should throw exception
        assertThrows(IllegalArgumentException.class, () -> 
            new Contact("123", "John", "Doe", "1234567890", null));

        // Assert: address longer than 30 characters should throw exception
        assertThrows(IllegalArgumentException.class, () -> 
            new Contact("123", "John", "Doe", "1234567890", 
            "This address is way too long to be valid"));
    }
}