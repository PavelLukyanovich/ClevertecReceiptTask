package com.clevertec.receipt.services;

import com.clevertec.receipt.exceptions.NoSuchElementsException;
import com.clevertec.receipt.models.entities.Product;
import com.clevertec.receipt.models.requests.ReceiptRequest;
import com.clevertec.receipt.services.cards.CardService;
import com.clevertec.receipt.services.factories.CalculationStrategy;
import com.clevertec.receipt.services.print.TxtPrintService;
import com.clevertec.receipt.services.products.ProductService;
import com.clevertec.receipt.services.receipt.ReceiptStorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceImplTest {
    @Mock
    private TxtPrintService txtPrintService;

    @Mock
    private CalculationStrategy calculationStrategy;

    @Mock
    private CardService cardService;

    @Mock
    private ProductService productService;

    @Mock
    private ReceiptStorageService receiptStorageService;

    @InjectMocks
    private ReceiptServiceImpl receiptService;
    ReceiptRequest receiptRequest;
    List<Product> productList;

    @BeforeEach
    public void init() {

        receiptRequest = new ReceiptRequest();
        receiptRequest.setCardNumber("0001");
        receiptRequest.setItems(Arrays.asList(
                new ReceiptRequest.Item(1L, 2),
                new ReceiptRequest.Item(2L, 4),
                new ReceiptRequest.Item(3L, 1),
                new ReceiptRequest.Item(4L, 3),
                new ReceiptRequest.Item(5L, 5)
        ));


         productList = Arrays.asList(
                new Product(1L, "Milk", 1.2, false),
                new Product(2L, "Soap", 3.6, true),
                new Product(3L, "Rope", 7.2, true)
        );

    }

    @Test
    public void getReceipt_whenReceiptRequestIsNull_shouldReturnNull() {
        String receipt = receiptService.getReceipt(null);
        assertNull(receipt);
    }
    @Test
    public void getReceipt_whenCardNumberIsNull_shouldReturnNull() {
        receiptRequest.setCardNumber(null);
        assertNull(receiptRequest.getCardNumber());
    }

    @Test
    public void validateQuantityItems_WhenItemsNotFound_shouldTrowException() {

        when(productService.findAllProductsByIds(Arrays.asList(1L, 2L, 3L, 4L, 5L))).thenReturn(productList);

        List<Long> notFoundedProductIds
                = assertThrows(NoSuchElementsException.class, () -> receiptService.getReceipt(receiptRequest))
                .getNotFoundedProductIds();
        verify(cardService, times(1)).getCardByCardNumber(receiptRequest.getCardNumber());
        assertArrayEquals(Arrays.asList(4L, 5L).toArray(), notFoundedProductIds.toArray());
    }

}