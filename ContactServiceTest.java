package contactservice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for ContactService class
 * Tests all requirements:
 * 1. Add contacts with unique ID
 * 2. Delete contacts per contact ID
 * 3. Update contact fields per contact ID (firstName, lastName, phone, address)
 */
public class ContactServiceTest {

    private ContactService contactService;
    private Contact testContact1;
    private Contact testContact2;

    @BeforeEach
    public void setUp() {
        contactService = new ContactService();
        testContact1 = new Contact("1", "John", "Doe", "5551234567", "123 Main St");
        testContact2 = new Contact("2", "Jane", "Smith", "5559876543", "456 Oak Ave");
    }

    // Add Contact Tests
    @Test
    public void testAddContactSuccess() {
        contactService.addContact(testContact1);

        assertEquals(1, contactService.getContactCount());
        assertTrue(contactService.contactExists("1"));
        assertEquals(testContact1, contactService.getContact("1"));
    }

    @Test
    public void testAddMultipleContacts() {
        contactService.addContact(testContact1);
        contactService.addContact(testContact2);

        assertEquals(2, contactService.getContactCount());
        assertTrue(contactService.contactExists("1"));
        assertTrue(contactService.contactExists("2"));
    }

    @Test
    public void testAddContactWithDuplicateId() {
        contactService.addContact(testContact1);

        Contact duplicateContact = new Contact("1", "Bob", "Johnson", "5555555555", "789 Pine St");

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(duplicateContact);
        });

        // Verify original contact is still there
        assertEquals(1, contactService.getContactCount());
        assertEquals("John", contactService.getContact("1").getFirstName());
    }

    @Test
    public void testAddNullContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(null);
        });

        assertEquals(0, contactService.getContactCount());
    }

    // Delete Contact Tests
    @Test
    public void testDeleteContactSuccess() {
        contactService.addContact(testContact1);
        contactService.addContact(testContact2);

        contactService.deleteContact("1");

        assertEquals(1, contactService.getContactCount());
        assertFalse(contactService.contactExists("1"));
        assertTrue(contactService.contactExists("2"));
    }

    @Test
    public void testDeleteNonExistentContact() {
        contactService.addContact(testContact1);

        assertThrows(IllegalArgumentException.class, () -> {
            contactService.deleteContact("999");
        });

        // Verify existing contact is still there
        assertEquals(1, contactService.getContactCount());
        assertTrue(contactService.contactExists("1"));
    }

    @Test
    public void testDeleteAllContacts() {
        contactService.addContact(testContact1);
        contactService.addContact(testContact2);

        contactService.deleteContact("1");
        contactService.deleteContact("2");

        assertEquals(0, contactService.getContactCount());
        assertFalse(contactService.contactExists("1"));
        assertFalse(contactService.contactExists("2"));
    }

    // Update First Name Tests
    @Test
    public void testUpdateFirstNameSuccess() {
        contactService.addContact(testContact1);

        contactService.updateFirstName("1", "Michael");

        assertEquals("Michael", contactService.getContact("1").getFirstName());
    }

    @Test
    public void testUpdateFirstNameNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateFirstName("999", "Michael");
        });
    }

    @Test
    public void testUpdateFirstNameInvalidData() {
        contactService.addContact(testContact1);

        // Test null first name
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateFirstName("1", null);
        });

        // Test too long first name
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateFirstName("1", "VeryLongFirstName");
        });

        // Verify original value is unchanged
        assertEquals("John", contactService.getContact("1").getFirstName());
    }

    // Update Last Name Tests
    @Test
    public void testUpdateLastNameSuccess() {
        contactService.addContact(testContact1);

        contactService.updateLastName("1", "Johnson");

        assertEquals("Johnson", contactService.getContact("1").getLastName());
    }

    @Test
    public void testUpdateLastNameNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateLastName("999", "Johnson");
        });
    }

    @Test
    public void testUpdateLastNameInvalidData() {
        contactService.addContact(testContact1);

        // Test null last name
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateLastName("1", null);
        });

        // Test too long last name
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateLastName("1", "VeryLongLastName");
        });

        // Verify original value is unchanged
        assertEquals("Doe", contactService.getContact("1").getLastName());
    }

    // Update Phone Tests
    @Test
    public void testUpdatePhoneSuccess() {
        contactService.addContact(testContact1);

        contactService.updatePhone("1", "5555551234");

        assertEquals("5555551234", contactService.getContact("1").getPhone());
    }

    @Test
    public void testUpdatePhoneNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updatePhone("999", "5555551234");
        });
    }

    @Test
    public void testUpdatePhoneInvalidData() {
        contactService.addContact(testContact1);

        // Test null phone
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updatePhone("1", null);
        });

        // Test invalid phone format
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updatePhone("1", "555-123-4567");
        });

        // Test wrong length phone
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updatePhone("1", "555123456");
        });

        // Verify original value is unchanged
        assertEquals("5551234567", contactService.getContact("1").getPhone());
    }

    // Update Address Tests
    @Test
    public void testUpdateAddressSuccess() {
        contactService.addContact(testContact1);

        contactService.updateAddress("1", "789 Elm Street");

        assertEquals("789 Elm Street", contactService.getContact("1").getAddress());
    }

    @Test
    public void testUpdateAddressNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateAddress("999", "789 Elm Street");
        });
    }

    @Test
    public void testUpdateAddressInvalidData() {
        contactService.addContact(testContact1);

        // Test null address
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateAddress("1", null);
        });

        // Test too long address
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateAddress("1", "This is a very long address that exceeds the thirty character limit");
        });

        // Verify original value is unchanged
        assertEquals("123 Main St", contactService.getContact("1").getAddress());
    }

    // Get Contact Tests
    @Test
    public void testGetContactSuccess() {
        contactService.addContact(testContact1);

        Contact retrieved = contactService.getContact("1");

        assertEquals(testContact1, retrieved);
        assertEquals("John", retrieved.getFirstName());
        assertEquals("Doe", retrieved.getLastName());
        assertEquals("5551234567", retrieved.getPhone());
        assertEquals("123 Main St", retrieved.getAddress());
    }

    @Test
    public void testGetNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.getContact("999");
        });
    }

    // Contact Exists Tests
    @Test
    public void testContactExistsTrue() {
        contactService.addContact(testContact1);

        assertTrue(contactService.contactExists("1"));
    }

    @Test
    public void testContactExistsFalse() {
        assertFalse(contactService.contactExists("999"));
    }

    // Contact Count Tests
    @Test
    public void testContactCountEmpty() {
        assertEquals(0, contactService.getContactCount());
    }

    @Test
    public void testContactCountAfterAdding() {
        contactService.addContact(testContact1);
        assertEquals(1, contactService.getContactCount());

        contactService.addContact(testContact2);
        assertEquals(2, contactService.getContactCount());
    }

    @Test
    public void testContactCountAfterDeleting() {
        contactService.addContact(testContact1);
        contactService.addContact(testContact2);
        assertEquals(2, contactService.getContactCount());

        contactService.deleteContact("1");
        assertEquals(1, contactService.getContactCount());

        contactService.deleteContact("2");
        assertEquals(0, contactService.getContactCount());
    }

    // Integration Tests
    @Test
    public void testCompleteWorkflow() {
        // Add contacts
        contactService.addContact(testContact1);
        contactService.addContact(testContact2);
        assertEquals(2, contactService.getContactCount());

        // Update contact 1
        contactService.updateFirstName("1", "Johnny");
        contactService.updateLastName("1", "Doeson");
        contactService.updatePhone("1", "5551111111");
        contactService.updateAddress("1", "111 New Street");

        // Verify updates
        Contact updated = contactService.getContact("1");
        assertEquals("Johnny", updated.getFirstName());
        assertEquals("Doeson", updated.getLastName());
        assertEquals("5551111111", updated.getPhone());
        assertEquals("111 New Street", updated.getAddress());

        // Verify contact 2 is unchanged
        Contact unchanged = contactService.getContact("2");
        assertEquals("Jane", unchanged.getFirstName());
        assertEquals("Smith", unchanged.getLastName());
        assertEquals("5559876543", unchanged.getPhone());
        assertEquals("456 Oak Ave", unchanged.getAddress());

        // Delete contact 1
        contactService.deleteContact("1");
        assertEquals(1, contactService.getContactCount());
        assertFalse(contactService.contactExists("1"));
        assertTrue(contactService.contactExists("2"));
    }
}