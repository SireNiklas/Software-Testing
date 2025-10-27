package contactservice;

import java.util.HashMap;
import java.util.Map;

/**
 * ContactService class for managing Contact objects
 * Requirements:
 * - Add contacts with unique ID
 * - Delete contacts per contact ID
 * - Update contact fields per contact ID (firstName, lastName, phone, address)
 */
public class ContactService {
    private Map<String, Contact> contacts;

    /**
     * Constructor initializes the contact storage
     */
    public ContactService() {
        this.contacts = new HashMap<>();
    }

    /**
     * Adds a new contact to the service
     * @param contact Contact object to add
     * @throws IllegalArgumentException if contact ID already exists or contact is null
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }

        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("Contact ID already exists: " + contact.getContactId());
        }

        contacts.put(contact.getContactId(), contact);
    }

    /**
     * Deletes a contact by contact ID
     * @param contactId The ID of the contact to delete
     * @throws IllegalArgumentException if contact ID doesn't exist
     */
    public void deleteContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID not found: " + contactId);
        }

        contacts.remove(contactId);
    }

    /**
     * Updates the first name of a contact
     * @param contactId The ID of the contact to update
     * @param firstName The new first name
     * @throws IllegalArgumentException if contact ID doesn't exist
     */
    public void updateFirstName(String contactId, String firstName) {
        Contact contact = getContact(contactId);
        contact.setFirstName(firstName);
    }

    /**
     * Updates the last name of a contact
     * @param contactId The ID of the contact to update
     * @param lastName The new last name
     * @throws IllegalArgumentException if contact ID doesn't exist
     */
    public void updateLastName(String contactId, String lastName) {
        Contact contact = getContact(contactId);
        contact.setLastName(lastName);
    }

    /**
     * Updates the phone number of a contact
     * @param contactId The ID of the contact to update
     * @param phone The new phone number
     * @throws IllegalArgumentException if contact ID doesn't exist
     */
    public void updatePhone(String contactId, String phone) {
        Contact contact = getContact(contactId);
        contact.setPhone(phone);
    }

    /**
     * Updates the address of a contact
     * @param contactId The ID of the contact to update
     * @param address The new address
     * @throws IllegalArgumentException if contact ID doesn't exist
     */
    public void updateAddress(String contactId, String address) {
        Contact contact = getContact(contactId);
        contact.setAddress(address);
    }

    /**
     * Retrieves a contact by contact ID
     * @param contactId The ID of the contact to retrieve
     * @return The Contact object
     * @throws IllegalArgumentException if contact ID doesn't exist
     */
    public Contact getContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID not found: " + contactId);
        }

        return contacts.get(contactId);
    }

    /**
     * Returns the number of contacts in the service
     * @return Number of contacts
     */
    public int getContactCount() {
        return contacts.size();
    }

    /**
     * Checks if a contact ID exists
     * @param contactId The ID to check
     * @return true if contact exists, false otherwise
     */
    public boolean contactExists(String contactId) {
        return contacts.containsKey(contactId);
    }
}