package contactservice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Contact class
 * Tests all requirements:
 * 1. Contact ID validation (max 10 chars, not null, not updatable)
 * 2. First name validation (max 10 chars, not null)
 * 3. Last name validation (max 10 chars, not null)
 * 4. Phone validation (exactly 10 digits, not null)
 * 5. Address validation (max 30 chars, not null)
 */
public class ContactTest {

    @Test
    public void testContactCreationSuccess() {
        Contact contact = new Contact("1234567890", "John", "Doe", "5551234567", "123 Main St");

        assertEquals("1234567890", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("5551234567", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
    }

    // Contact ID Tests
    @Test
    public void testContactIdNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Doe", "5551234567", "123 Main St");
        });
    }

    @Test
    public void testContactIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "5551234567", "123 Main St");
        });
    }

    @Test
    public void testContactIdMaxLength() {
        // Should work with exactly 10 characters
        Contact contact = new Contact("1234567890", "John", "Doe", "5551234567", "123 Main St");
        assertEquals("1234567890", contact.getContactId());
    }

    @Test
    public void testContactIdNotUpdatable() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        // Contact ID should remain the same - no setter method exists
        assertEquals("123", contact.getContactId());
    }

    // First Name Tests
    @Test
    public void testFirstNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", null, "Doe", "5551234567", "123 Main St");
        });
    }

    @Test
    public void testFirstNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "JohnJohnJohn", "Doe", "5551234567", "123 Main St");
        });
    }

    @Test
    public void testFirstNameMaxLength() {
        // Should work with exactly 10 characters
        Contact contact = new Contact("123", "JohnJohnJo", "Doe", "5551234567", "123 Main St");
        assertEquals("JohnJohnJo", contact.getFirstName());
    }

    @Test
    public void testSetFirstNameValid() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        contact.setFirstName("Jane");
        assertEquals("Jane", contact.getFirstName());
    }

    @Test
    public void testSetFirstNameNull() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName(null);
        });
    }

    @Test
    public void testSetFirstNameTooLong() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName("JohnJohnJohn");
        });
    }

    // Last Name Tests
    @Test
    public void testLastNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", null, "5551234567", "123 Main St");
        });
    }

    @Test
    public void testLastNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "DoeDoeDoeDoeDoe", "5551234567", "123 Main St");
        });
    }

    @Test
    public void testLastNameMaxLength() {
        // Should work with exactly 10 characters
        Contact contact = new Contact("123", "John", "DoeDoeDoeD", "5551234567", "123 Main St");
        assertEquals("DoeDoeDoeD", contact.getLastName());
    }

    @Test
    public void testSetLastNameValid() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        contact.setLastName("Smith");
        assertEquals("Smith", contact.getLastName());
    }

    @Test
    public void testSetLastNameNull() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName(null);
        });
    }

    @Test
    public void testSetLastNameTooLong() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName("SmithSmithSmith");
        });
    }

    // Phone Tests
    @Test
    public void testPhoneNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "Doe", null, "123 Main St");
        });
    }

    @Test
    public void testPhoneTooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "Doe", "555123456", "123 Main St");
        });
    }

    @Test
    public void testPhoneTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "Doe", "55512345678", "123 Main St");
        });
    }

    @Test
    public void testPhoneNotDigits() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "Doe", "555-123-456", "123 Main St");
        });
    }

    @Test
    public void testPhoneWithLetters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "Doe", "555ABC4567", "123 Main St");
        });
    }

    @Test
    public void testPhoneValid() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        assertEquals("5551234567", contact.getPhone());
    }

    @Test
    public void testSetPhoneValid() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        contact.setPhone("5559876543");
        assertEquals("5559876543", contact.getPhone());
    }

    @Test
    public void testSetPhoneNull() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone(null);
        });
    }

    @Test
    public void testSetPhoneInvalid() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("555-123-4567");
        });
    }

    // Address Tests
    @Test
    public void testAddressNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "Doe", "5551234567", null);
        });
    }

    @Test
    public void testAddressTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "Doe", "5551234567", "123 Main Street, Apartment 456, Very Long City Name");
        });
    }

    @Test
    public void testAddressMaxLength() {
        // Should work with exactly 30 characters
        String address = "123456789012345678901234567890"; // 30 characters
        Contact contact = new Contact("123", "John", "Doe", "5551234567", address);
        assertEquals(address, contact.getAddress());
    }

    @Test
    public void testSetAddressValid() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        contact.setAddress("456 Oak Ave");
        assertEquals("456 Oak Ave", contact.getAddress());
    }

    @Test
    public void testSetAddressNull() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress(null);
        });
    }

    @Test
    public void testSetAddressTooLong() {
        Contact contact = new Contact("123", "John", "Doe", "5551234567", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress("123 Main Street, Apartment 456, Very Long City Name That Exceeds Limit");
        });
    }
}