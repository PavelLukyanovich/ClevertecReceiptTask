package com.clevertec.receipt.repositories;

import com.clevertec.receipt.models.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

}
