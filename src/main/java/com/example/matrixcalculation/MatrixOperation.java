package com.example.matrixcalculation;

import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

// Объявление класса MatrixDeterminantOperation, который реализует интерфейс MatrixOperations.
public class MatrixOperation implements MatrixOperations
{
    /**
     * Метод складывает матрицы A и B.
     *
     * @param matrixA Матрица A
     * @param matrixB Матрица B
     * @return сумму матриц
     */
    public List<List<Integer>> sumMatrices(List<List<Integer>> matrixA, List<List<Integer>> matrixB)
    {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < matrixA.size(); i++)
        {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < matrixA.get(i).size(); j++)
            {
                int sum = matrixA.get(i).get(j) + matrixB.get(i).get(j);
                row.add(sum);
            }
            result.add(row);
        }
        return result;
    }

    /**
     * Метод вычитает матрицу B из матрицы A.
     *
     * @param matrixA Матрица A
     * @param matrixB Матрица B
     * @return разность матриц
     */
    public List<List<Integer>> diffMatrices(List<List<Integer>> matrixA, List<List<Integer>> matrixB)
    {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < matrixA.size(); i++)
        {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < matrixA.get(i).size(); j++)
            {
                int sum = matrixA.get(i).get(j) - matrixB.get(i).get(j);
                row.add(sum);
            }
            result.add(row);
        }
        return result;
    }

    /**
     * Метод умножает матрицу A на матрицу B.
     *
     * @param matrixA Матрица A
     * @param matrixB Матрица B
     * @return результат умножения матриц
     */
    public List<List<Integer>> multiplyMatrices(List<List<Integer>> matrixA, List<List<Integer>> matrixB)
    {
        int rowsA = matrixA.size();
        int colsA = matrixA.get(0).size();
        int colsB = matrixB.get(0).size();

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < rowsA; i++)
        {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < colsB; j++)
            {
                int sum = 0;
                for (int k = 0; k < colsA; k++)
                {
                    sum += matrixA.get(i).get(k) * matrixB.get(k).get(j);
                }
                row.add(sum);
            }
            result.add(row);
        }

        return result;
    }

    /**
     * Метод calculateDeterminant вычисляет определитель квадратной матрицы.
     * Принимает двумерный список целых чисел, представляющих матрицу.
     */
    public double calculateDeterminant(List<List<Integer>> matrix)
    {
        // Проверка на то, что матрица является квадратной (количество строк равно количеству столбцов).
        if (matrix.size() != matrix.get(0).size())
        {
            throw new IllegalArgumentException("Матрица должна быть квадратной для вычисления определителя. Это означает, что в матрице количество строк должно быть  равно количеству столбцов.");
        }

        int n = matrix.size();

        // Если матрица имеет размер 1x1, возвращаем единственный элемент матрицы.
        if (n == 1)
        {
            return matrix.get(0).get(0);
        }
        else
        {
            double det = 0;

            // Итерация по элементам первой строки матрицы для вычисления определителя.
            for (int i = 0; i < n; i++)
            {
                // Получение минора и рекурсивное вычисление определителя.
                List<List<Integer>> subMatrix = getSubMatrix(matrix, 0, i);
                det += Math.pow(-1, i) * matrix.get(0).get(i) * calculateDeterminant(subMatrix);
            }
            return det;
        }
    }

    /**
     * Метод getSubMatrix возвращает подматрицу матрицы matrix без указанной строки и столбца.
     */
    public List<List<Integer>> getSubMatrix(List<List<Integer>> matrix, int rowToRemove, int colToRemove)
    {
        int n = matrix.size();
        List<List<Integer>> subMatrix = new ArrayList<>();

        // Итерация по строкам матрицы.
        for (int i = 0; i < n; i++)
        {
            // Если текущая строка не равна удаляемой строке.
            if (i != rowToRemove)
            {
                List<Integer> row = new ArrayList<>();

                // Итерация по столбцам матрицы.
                for (int j = 0; j < n; j++)
                {
                    // Если текущий столбец не равен удаляемому столбцу, добавляем элемент в строку подматрицы.
                    if (j != colToRemove)
                    {
                        row.add(matrix.get(i).get(j));
                    }
                }
                // Добавление строки подматрицы в список.
                subMatrix.add(row);
            }
        }
        return subMatrix;
    }

    /**
     * Метод получает матрицу из входных данных TextArea.
     *
     * @param textArea textArea Текстовая область, содержащая матричные данные
     * @return матрицу, извлеченную из TextArea
     */
    public List<List<Integer>> getMatrixFromTextArea(TextArea textArea)
    {
        String text = textArea.getText();
        List<List<Integer>> matrix = new ArrayList<>();
        String[] rows = text.split("\n");
        for (String row : rows)
        {
            String[] elements = row.trim().split("\\s+");
            List<Integer> matrixRow = new ArrayList<>();
            for (String element : elements)
            {
                matrixRow.add(Integer.parseInt(element));
            }
            matrix.add(matrixRow);
        }
        return matrix;
    }

    /**
     * Метод преобразовывает матрицу в строку для отображения в текстовой области.
     *
     * @param matrix Матрица для преобразования
     * @return String (строковое представление матрицы)
     */
    public String matrixToString(List<List<Integer>> matrix)
    {
        StringBuilder result = new StringBuilder();
        for (List<Integer> row : matrix)
        {
            for (Integer element : row)
            {
                result.append(element).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Метод проверяет, имеют ли матрицы A и B одинаковые размеры.
     * Это делается для проверки возможности сложения или вычитания матриц.
     *
     * @param matrixA Матрица A
     * @param matrixB матрица B
     * @return True, если размеры совпадают, False в противном случае
     */
    public boolean equalityMatrices(List<List<Integer>> matrixA, List<List<Integer>> matrixB)
    {
        int rowsA = matrixA.size();
        int colsA = matrixA.get(0).size();
        int rowsB = matrixB.size();
        int colsB = matrixB.get(0).size();

        return (rowsA == rowsB) && (colsA == colsB);
    }
}
