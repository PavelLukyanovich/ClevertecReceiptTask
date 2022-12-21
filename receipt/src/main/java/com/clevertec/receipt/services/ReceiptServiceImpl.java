package com.clevertec.receipt.services;

import com.clevertec.receipt.exceptions.NoSuchElementsException;
import com.clevertec.receipt.models.dto.BucketDto;
import com.clevertec.receipt.models.dto.ProductDto;
import com.clevertec.receipt.models.entities.Card;
import com.clevertec.receipt.models.entities.Product;
import com.clevertec.receipt.models.requests.ReceiptRequest;
import com.clevertec.receipt.services.cards.CardService;
import com.clevertec.receipt.services.factories.CalculationStrategy;
import com.clevertec.receipt.services.print.TxtPrintService;
import com.clevertec.receipt.services.products.ProductService;
import com.clevertec.receipt.services.receipt.ReceiptStorageService;
import com.clevertec.receipt.utils.ProductConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final TxtPrintService txtPrintService;

    private final CalculationStrategy calculationStrategy;

    private final CardService cardService;

    private final ProductService productService;

    private final ReceiptStorageService receiptStorageService;

    @Override

    public String getReceipt(ReceiptRequest receiptRequest) {


        Card card = cardService.getCardByCardNumber(receiptRequest.getCardNumber());
        log.info("Fetched card {}", card);
        List<Long> productIds = receiptRequest.getItems().stream()
                .map(ReceiptRequest.Item::getId)
                .toList();
        List<Product> productList = productService.findAllProductsByIds(productIds);

        validateQuantityItems(productIds, productList);

        List<ProductDto> productDtos = productList.stream()
                .map(ProductConverter::convert)
                .map(productDto -> setQuantityToProductDto(receiptRequest, productDto))
                .toList();

        BucketDto bucketDto = createBucketDto(productDtos, card);
        calculationStrategy.process(bucketDto);

        String receipt = txtPrintService.createReceipt(bucketDto);
        receiptStorageService.save(receiptRequest);
        return receipt;
    }

    private static void validateQuantityItems(List<Long> productIds, List<Product> productList) {

        if (productIds.size() != productList.size()) {
            List<Long> notFoundedProductIds = productIds.stream()
                    .filter(id -> productList.stream().noneMatch(product -> product.getId().equals(id)))
                    .toList();
            throw new NoSuchElementsException(notFoundedProductIds);
        }
    }

    private static BucketDto createBucketDto(List<ProductDto> productDtos, Card card) {

        return BucketDto.builder().productDtos(productDtos).card(card).build();
    }

    private static ProductDto setQuantityToProductDto(ReceiptRequest receiptRequest, ProductDto productDto) {

        Integer quantity = receiptRequest.getItems().stream()
                .filter(item -> item.getId().equals(productDto.getId()))
                .findFirst()
                .map(ReceiptRequest.Item::getAmount)
                .orElse(0);
        productDto.setQuantity(quantity);

        return productDto;
    }

}
