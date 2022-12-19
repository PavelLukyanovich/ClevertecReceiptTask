package com.clevertec.receipt.services.print;

import com.clevertec.receipt.models.dto.BucketDto;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Formatter;

@Slf4j
@Service
public class TxtPrintServiceImpl implements TxtPrintService {

    @Override
    public String createReceipt(BucketDto bucketDto) {

        Formatter formatter = new Formatter();
        Date date = new Date();

        String itemFormatHeader = "%25s\n %s\n %s\n %s\n";
        String itemFormatDate = "%28s %tD\n";
        String itemFormatTime = "%28s %tT\n";
        String itemFormatBody = "%-3s %-11s %10s %10s\n";
        String itemFormatPreFooter = "%s\n";
        String itemFormatFooter = "%-30s %-1.2f\n";

        formatter.format(itemFormatHeader, "CASH RECEIPT", "supermarket GREEN", "Parkovaya St. 134/23", "tel: +562981235465");
        formatter.format(itemFormatDate, "DATE:", date);
        formatter.format(itemFormatTime, "TIME:", date.getTime());
        formatter.format(itemFormatPreFooter, "======================================");
        formatter.format(itemFormatBody, "QTY", "DESCRIPTION", "PRICE", "TOTAL");

        bucketDto.getProductDtos()
                .forEach(productDto -> formatter.format(itemFormatBody, productDto.getQuantity(), productDto.getTitle(),
                        productDto.getPrice(), productDto.getTotalPrice()));

        formatter.format(itemFormatPreFooter, "======================================");
        if (bucketDto.getDiscountSum() != null && bucketDto.getDiscountSum() > 0) {
            formatter.format(itemFormatFooter, "DISCOUNT: ", bucketDto.getDiscountSum());
        }
        formatter.format(itemFormatFooter, "TOTAL: ", bucketDto.getReceivedSum());


        return formatter.toString();
    }
}
