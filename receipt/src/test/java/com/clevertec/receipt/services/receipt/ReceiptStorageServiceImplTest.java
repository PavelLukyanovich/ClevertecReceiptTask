package com.clevertec.receipt.services.receipt;

import com.clevertec.receipt.models.entities.Receipt;
import com.clevertec.receipt.models.requests.ReceiptRequest;
import com.clevertec.receipt.repositories.ReceiptRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class ReceiptStorageServiceImplTest {
    @Captor
    private ArgumentCaptor<Receipt> argumentCaptor;
    private ReceiptStorageServiceImpl receiptStorageService;
    private ObjectMapper objectMapper;

    private ReceiptRepository receiptRepository;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        receiptRepository = Mockito.spy(ReceiptRepository.class);
        receiptStorageService = new ReceiptStorageServiceImpl(receiptRepository, objectMapper);

    }

    @Test
    public void getReceipt_WhenReceiptSaved() throws JsonProcessingException {

        argumentCaptor = ArgumentCaptor.forClass(Receipt.class);
        ReceiptRequest receiptRequest = new ReceiptRequest();
        receiptRequest.setItems(Collections.singletonList(new ReceiptRequest.Item(1L, 2)));
        receiptRequest.setCardNumber("0001");
        String expectedJson = objectMapper.writeValueAsString(receiptRequest);

        when(receiptRepository.save(isA(Receipt.class))).thenReturn(new Receipt());

        receiptStorageService.save(receiptRequest);
        receiptStorageService.save(receiptRequest);
        Mockito.verify(receiptRepository, times(2)).save(argumentCaptor.capture());
        Receipt actualReceipt = argumentCaptor.getValue();
        assertNotNull("", actualReceipt);
        assertNotNull("", actualReceipt.getCreationDateTime());
        assertEquals("123", expectedJson, actualReceipt.getJson());

    }

    @Test
    public void whenExceptionThrown_AssertionSucceeds() throws JsonProcessingException {
        ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);
        when(objectMapper.writeValueAsString(any()))
                .thenThrow(new JsonProcessingException("Error parsing") {
                    private static final long serialVersionUID = 1L;
                });
    }

}
