package com.clevertec.receipt.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MathOperationsTest {
    @Mock
    MathOperations mathOperations;

    @ParameterizedTest
    @ValueSource(floats = {8.5545454F, 12.489F, 0.0003F})
    void defaultRoundShouldReturnCorrectValue(float argument) {

        Double roundedArgument = MathOperations.defaultRound(argument);
        String[] arrayFromArgument = String.valueOf(roundedArgument).split("\\.");
        int afterComma = arrayFromArgument[1].length();

        assertTrue(2 >= afterComma);
    }

    @ParameterizedTest
    @NullSource
    void testDefaultRoundWhenArgumentIsNullShouldReturnNull(Float argument) {
        assertNull(argument);
    }

    @ParameterizedTest
    @NullSource
    void testDefaultRoundWhenArgumentIsNullShouldReturnNull(Double argument) {
        assertNull(argument);
    }
}