package com.clevertec.receipt.services.cards;

import com.clevertec.receipt.models.entities.Card;
import com.clevertec.receipt.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    @Override
    public Card getCardByCardNumber(String cardNumber) {

        log.info("Get card by card number {}", cardNumber);
        return Optional.ofNullable(cardNumber)
                .map(cardRepository::findByCardNumber)
                .orElse(null);
    }
}
