package com.example.matrixcalculation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatrixOperationTest
{
    @Test
    void testSumMatrices()
    {
        MatrixOperation matrixOperation = new MatrixOperation();
        List<List<Integer>> matrixA = new ArrayList<>(Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4)
        ));
        List<List<Integer>> matrixB = new ArrayList<>(Arrays.asList(
                Arrays.asList(5, 6),
                Arrays.asList(7, 8)
        ));
        List<List<Integer>> expectedSum = new ArrayList<>(Arrays.asList(
                Arrays.asList(6, 8),
                Arrays.asList(10, 12)
        ));
        List<List<Integer>> result = matrixOperation.sumMatrices(matrixA, matrixB);
        assertEquals(expectedSum, result);
    }

    @Test
    void testDiffMatrices()
    {
        MatrixOperation matrixOperation = new MatrixOperation();
        List<List<Integer>> matrixA = new ArrayList<>(Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4)
        ));
        List<List<Integer>> matrixB = new ArrayList<>(Arrays.asList(
                Arrays.asList(5, 6),
                Arrays.asList(7, 8)
        ));
        List<List<Integer>> expectedDiff = new ArrayList<>(Arrays.asList(
                Arrays.asList(-4, -4),
                Arrays.asList(-4, -4)
        ));
        List<List<Integer>> result = matrixOperation.diffMatrices(matrixA, matrixB);
        assertEquals(expectedDiff, result);
    }

    @Test
    void testMultiplyMatrices()
    {
        MatrixOperation matrixOperation = new MatrixOperation();
        List<List<Integer>> matrixA = new ArrayList<>(Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4)
        ));
        List<List<Integer>> matrixB = new ArrayList<>(Arrays.asList(
                Arrays.asList(5, 6),
                Arrays.asList(7, 8)
        ));
        List<List<Integer>> expectedProduct = new ArrayList<>(Arrays.asList(
                Arrays.asList(19, 22),
                Arrays.asList(43, 50)
        ));
        List<List<Integer>> result = matrixOperation.multiplyMatrices(matrixA, matrixB);
        assertEquals(expectedProduct, result);
    }

    @Test
    void testCalculateDeterminant_withValidMatrix()
    {
        MatrixOperation matrixOperation = new MatrixOperation();
        List<List<Integer>> matrix = new ArrayList<>(Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4)
        ));
        double determinant = matrixOperation.calculateDeterminant(matrix);
        assertEquals(-2, determinant);
    }
}
