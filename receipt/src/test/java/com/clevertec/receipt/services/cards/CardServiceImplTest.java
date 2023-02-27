package com.clevertec.receipt.services.cards;

import com.clevertec.receipt.cash.Cache;
import com.clevertec.receipt.cash.LFUCacheImpl;
import com.clevertec.receipt.cash.LRUCacheImpl;
import com.clevertec.receipt.configuration.CacheConfiguration;
import com.clevertec.receipt.models.entities.Card;
import com.clevertec.receipt.repositories.CardRepository;
import com.clevertec.receipt.repositories.ProductRepository;
import com.clevertec.receipt.repositories.ReceiptRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class CardServiceImplTest {
    @MockBean
    private CardRepository cardRepository;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private ReceiptRepository receiptRepository;
    @MockBean
    private LFUCacheImpl lfuCache;
    @MockBean
    private LRUCacheImpl lruCache;
    @MockBean
    private CacheConfiguration cacheConfiguration;
    @Autowired
    private CardService cardService;


    @Test
    public void getCardByCardNumber() {

        Card expectedCard = new Card(125L, "125", 10);
        when(cardRepository.findByCardNumber(expectedCard.getCardNumber())).thenReturn(expectedCard);

        Card actualCard = cardService.getCardByCardNumber(expectedCard.getCardNumber());

        assertEquals("error", expectedCard, actualCard);

    }

}
