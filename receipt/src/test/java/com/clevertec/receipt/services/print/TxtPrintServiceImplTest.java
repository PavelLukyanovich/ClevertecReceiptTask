package com.clevertec.receipt.services.print;

import com.clevertec.receipt.models.dto.BucketDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class TxtPrintServiceImplTest {
    TxtPrintServiceImpl service;
    @Mock
    BucketDto bucketDto;
    @BeforeEach
    public void init() {
        service = new TxtPrintServiceImpl();
    }

    @Test
    void createReceipt_WhenBucketIsNull_shouldReturnNull() {
        String formattedString = service.createReceipt(null);

        assertNull(formattedString);
    }
}