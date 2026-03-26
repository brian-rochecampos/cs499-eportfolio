package contact;

/*
 * Contact class with ID, first name, last name, phone, and address.
 * This class enforces validation rules to ensure all contact data is valid.
 */
public class Contact {

    // Unique identifier for the contact (cannot be changed after creation)
    private final String contactID;

    // Contact's first name (modifiable)
    private String firstName;

    // Contact's last name (modifiable)
    private String lastName;

    // Contact's phone number (must be exactly 10 digits)
    private String phone;

    // Contact's address (max 30 characters)
    private String address;

    // Constructor: initializes a contact and validates all fields
    public Contact(String contactID, String firstName, String lastName, String phone, String address) {

        // Validate contact ID (not null, max length 10)
        if (contactID == null || contactID.length() > 10)
            throw new IllegalArgumentException("Contact ID is invalid");

        // Validate first name (not null, max length 10)
        if (firstName == null || firstName.length() > 10)
            throw new IllegalArgumentException("First name is invalid");

        // Validate last name (not null, max length 10)
        if (lastName == null || lastName.length() > 10)
            throw new IllegalArgumentException("Last name is invalid");

        // Validate phone (must be exactly 10 digits, numeric only)
        if (phone == null || phone.length() != 10 || !phone.matches("\\d+"))
            throw new IllegalArgumentException("Phone is invalid");

        // Validate address (not null, max length 30)
        if (address == null || address.length() > 30)
            throw new IllegalArgumentException("Address is invalid");

        // Assign validated values
        this.contactID = contactID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    // Getter for contact ID
    public String getContactID() { return contactID; }

    // Getter for first name
    public String getFirstName() { return firstName; }

    // Getter for last name
    public String getLastName() { return lastName; }

    // Getter for phone number
    public String getPhone() { return phone; }

    // Getter for address
    public String getAddress() { return address; }

    // Updates first name with validation
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10)
            throw new IllegalArgumentException("First name is invalid");
        this.firstName = firstName;
    }

    // Updates last name with validation
    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10)
            throw new IllegalArgumentException("Last name is invalid");
        this.lastName = lastName;
    }

    // Updates phone number with validation (must remain 10 digits)
    public void setPhone(String phone) {
        if (phone == null || phone.length() != 10 || !phone.matches("\\d+"))
            throw new IllegalArgumentException("Phone is invalid");
        this.phone = phone;
    }

    // Updates address with validation
    public void setAddress(String address) {
        if (address == null || address.length() > 30)
            throw new IllegalArgumentException("Address is invalid");
        this.address = address;
    }
}