package com.clevertec.receipt.services;

import com.clevertec.receipt.models.entities.Card;
import com.clevertec.receipt.models.requests.ReceiptRequest;
import com.clevertec.receipt.services.cards.CardService;
import com.clevertec.receipt.services.factories.CalculationStrategy;
import com.clevertec.receipt.services.products.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceImplTest {
    @Mock
    CalculationStrategy calculationStrategy;
    ReceiptRequest receiptRequest;
    @Mock
    CardService cardService;
    @Mock
    ProductService productService;
    @InjectMocks
    private ReceiptServiceImpl receiptService;

    @Test
    public void getCard_WhenCardHasGettingBiNumber() {
        lenient().when(cardService.getCardByCardNumber(receiptRequest.getCardNumber())).thenReturn(new Card());
    }

    @Test
    public void getAllCardsByIds_WhenAllCardsHasGettingByIds() {
        lenient().when(productService.findAllProductsByIds(anyList())).thenReturn(new ArrayList<>());
    }
}