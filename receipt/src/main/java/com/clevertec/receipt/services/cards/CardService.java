package com.clevertec.receipt.services.cards;

import com.clevertec.receipt.models.entities.Card;

public interface CardService {

    Card getCardByCardNumber(String cardNumber);
}
