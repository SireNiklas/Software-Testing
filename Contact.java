package contactservice;

/**
 * Contact class representing a contact object with validation
 * Requirements:
 * - Unique contact ID (max 10 chars, not null, not updatable)
 * - First name (max 10 chars, not null)
 * - Last name (max 10 chars, not null)
 * - Phone (exactly 10 digits, not null)
 * - Address (max 30 chars, not null)
 */

public class Contact {
    private final String contactId;  // Final to prevent updates
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    /**
     * Constructor for Contact object
     * @param contactId Unique identifier (max 10 chars, not null)
     * @param firstName First name (max 10 chars, not null)
     * @param lastName Last name (max 10 chars, not null)
     * @param phone Phone number (exactly 10 digits, not null)
     * @param address Address (max 30 chars, not null)
     * @throws IllegalArgumentException if any parameter violates requirements
     */
    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        // Validate contactId
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("Contact ID cannot be null and must be 10 characters or less");
        }

        // Validate firstName
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("First name cannot be null and must be 10 characters or less");
        }

        // Validate lastName
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Last name cannot be null and must be 10 characters or less");
        }

        // Validate phone (exactly 10 digits)
        if (phone == null || phone.length() != 10 || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number cannot be null and must be exactly 10 digits");
        }

        // Validate address
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Address cannot be null and must be 30 characters or less");
        }

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    // Getter methods
    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // Setter methods (contactId is not included as it's not updatable)
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("First name cannot be null and must be 10 characters or less");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Last name cannot be null and must be 10 characters or less");
        }
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.length() != 10 || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number cannot be null and must be exactly 10 digits");
        }
        this.phone = phone;
    }

    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Address cannot be null and must be 30 characters or less");
        }
        this.address = address;
    }
}