package com.librariansunited.library_borrowing_system.loansmanagement.infrastructurelayer;

import com.librariansunited.library_borrowing_system.loansmanagement.domainlayer.Loan;
import com.librariansunited.library_borrowing_system.loansmanagement.domainlayer.LoanId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, LoanId> {
}
