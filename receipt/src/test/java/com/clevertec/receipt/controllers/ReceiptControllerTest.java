package com.clevertec.receipt.controllers;

import com.clevertec.receipt.models.requests.ReceiptRequest;
import com.clevertec.receipt.services.ReceiptService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ReceiptControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReceiptService receiptService;

    @Test
    public void getReceipt_WhenCorrectRequest_ShouldReturnValidAnswer() throws Exception {

        ReceiptRequest receiptRequest = new ReceiptRequest();
        receiptRequest.setCardNumber("1234");
        receiptRequest.setItems(Collections.singletonList(new ReceiptRequest.Item(1L, 2)));

        when(receiptService.getReceipt(receiptRequest)).thenReturn("Good answer");

        String requestAsString = objectMapper.writeValueAsString(receiptRequest);
        MvcResult mvcResult = mockMvc.perform(post("/receipt")
                        .content(requestAsString).contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        assertEquals("Good answer", contentAsString);
    }
}