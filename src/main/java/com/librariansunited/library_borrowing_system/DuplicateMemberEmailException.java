package com.librariansunited.library_borrowing_system;

public class DuplicateMemberEmailException
        extends RuntimeException {
    public DuplicateMemberEmailException(String email) {
        super("A member with email "
                + email + " already exists.");

    }
}
