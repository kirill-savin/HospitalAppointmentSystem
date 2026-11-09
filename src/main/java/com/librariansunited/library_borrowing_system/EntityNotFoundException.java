package com.librariansunited.library_borrowing_system;



public class EntityNotFoundException extends Exception {

    /**
     * Constructs a new EntityNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining why the member was not found
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new EntityNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message explaining why the member was not found
     * @param cause the cause of the exception (typically another exception)
     */
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new EntityNotFoundException for a specific member ID.
     *
     * @param memberId the ID of the member that was not found
     * @return a new EntityNotFoundException with a formatted message
     */
    public static EntityNotFoundException forId(Long memberId) {
        return new EntityNotFoundException("Member with ID " + memberId + " was not found");
    }

    /**
     * Constructs a new EntityNotFoundException for a specific member email.
     *
     * @param email the email of the member that was not found
     * @return a new EntityNotFoundException with a formatted message
     */
    public static EntityNotFoundException forEmail(String email) {
        return new EntityNotFoundException("Member with email " + email + " was not found");
    }
}
