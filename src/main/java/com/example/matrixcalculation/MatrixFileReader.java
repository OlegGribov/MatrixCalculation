package com.example.matrixcalculation;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatrixFileReader implements MatrixFileReadable
{
    /**
     * Метод для чтения матрицы из выбранного файла.
     *
     * @return Матрица в виде списка списков целых чисел.
     * @throws IOException если возникают ошибки при чтении файла.
     */
    public List<List<Integer>> readMatrixFromFile() throws IOException
    {
        // Создаем экземпляр класса FileChooser для выбора файла
        FileChooser fileChooser = new FileChooser();

        // Открываем диалоговое окно для выбора файла
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null)
        {
            // Создаем список списков целочисленного типа (матрицу)
            List<List<Integer>> matrix = new ArrayList<>();

            // Читаем данные из выбранного файла
            BufferedReader reader = new BufferedReader(new FileReader(selectedFile));

            String line;
            // Читаем каждую строку из файла
            // Пока есть строки в файле и текущая строка не пуста
            // присваиваем значение текущей строки переменной line и проверяем, не достигнут ли конец файла.
            // Если строка не пуста, выполняем блок кода внутри цикла.
            // В противном случае цикл завершается
            while ((line = reader.readLine()) != null)
            {
                // Разделяем строку на элементы по пробелу
                String[] elements = line.split(" ");

                List<Integer> row = new ArrayList<>();

                for (String element : elements)
                {
                    // Преобразуем каждый элемент строки в целое число и добавляем в список
                    row.add(Integer.parseInt(element));
                }
                matrix.add(row); // Добавляем строку в матрицу
            }

            reader.close();

            return matrix; // Возвращаем прочитанную матрицу
        }
        else
        {
            return null; // Возвращаем null, если файл не был выбран
        }
    }
}
