package com.clevertec.receipt.repositories;

import com.clevertec.receipt.models.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByCardNumber(String card);
}
