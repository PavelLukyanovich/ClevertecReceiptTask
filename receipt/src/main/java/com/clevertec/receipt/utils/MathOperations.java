package com.clevertec.receipt.utils;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor
public class MathOperations {

    public static Double subPercentageFromNumber(Float number, Integer percent) {

        return defaultRound((((number * 100) / 100) * (100 - percent) / 100));
    }

    public static Double defaultRound(Float number) {

        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static Double defaultRound(Double number) {

        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
