package com.example.matrixcalculation;

import java.io.IOException;
import java.util.List;

public interface MatrixFileReadable
{
    List<List<Integer>> readMatrixFromFile() throws IOException;
}
