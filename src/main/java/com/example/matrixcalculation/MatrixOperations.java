package com.example.matrixcalculation;

import javafx.scene.control.TextArea;

import java.util.List;

public interface MatrixOperations
{
    List<List<Integer>> sumMatrices(List<List<Integer>> matrixA, List<List<Integer>> matrixB);
    List<List<Integer>> diffMatrices(List<List<Integer>> matrixA, List<List<Integer>> matrixB);
    List<List<Integer>> multiplyMatrices(List<List<Integer>> matrixA, List<List<Integer>> matrixB);
    double calculateDeterminant(List<List<Integer>> matrix);
    List<List<Integer>> getSubMatrix(List<List<Integer>> matrix, int rowToRemove, int colToRemove);
    List<List<Integer>> getMatrixFromTextArea(TextArea textArea);
    String matrixToString(List<List<Integer>> matrix);
    boolean equalityMatrices(List<List<Integer>> matrixA, List<List<Integer>> matrixB);
}
