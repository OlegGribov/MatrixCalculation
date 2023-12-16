package com.example.matrixcalculation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Импортируемые библиотеки для работы с JavaFX элементами управления и событиями.
import javafx.fxml.FXML; // Для использования аннотаций, указывающих на элементы
// управления в файле FXML.
import javafx.scene.control.Button; // Для работы с кнопками в пользовательском интерфейсе.
import javafx.scene.control.CheckBox; // Для работы с флажками в пользовательском интерфейсе.
import javafx.scene.control.TextArea; // Для использования текстовых областей,
// где пользователь может вводить или отображать текст.

// Импорт стандартной библиотеки Java для работы с коллекциями List и
// исключениями ввода/вывода (IOException).
import java.util.List; // Предоставляет интерфейс List для работы с коллекциями
// объектов в Java.

import java.io.IOException; // Исключение, связанное с операциями ввода/вывода (IO).
// IOException выбрасывается при возникновении ошибок ввода/вывода, например, при
// чтении или записи данных из/в файл или другого источника данных.

/**
 * Класс контроллер для выполнения различных операций для элементов графического интерфейса.
 */
public class MainController
{
    private static final Logger logger = LogManager.getLogger(MainController.class);

    // Список графических элементов интерфейса и их идентификаторы (id), с которыми
    // в будущем планируется взаимодействовать.
    // Используя id, мы обращаемся к конкретному элементу в пользовательском интерфейсе,
    // определяя и присваивая ему нужный функционал или поведение в коде.
    @FXML
    private Button addMatrixAButton_id;

    @FXML
    private Button addMatrixBButton_id;

    @FXML
    private Button diffMatrix_id;

    @FXML
    private TextArea matrixA_id;

    @FXML
    private TextArea matrixB_id;

    @FXML
    private Button matrixDeterminant_id;

    @FXML
    private Button mulMatrix_id;

    @FXML
    private TextArea result_id;

    @FXML
    private Button sumMatrix_id;

    @FXML
    private CheckBox useMatrixA_id;

    @FXML
    private CheckBox useMatrixB_id;

    /**
     * Метод calculateDeterminant вычисляет определитель одной из двух матриц A или B.
     */
    @FXML
    void calculateDeterminant()
    {
        logger.info("The 'Determinant' button is pressed.");
        logger.info("The calculateDeterminant method has been called for the 'Determinant' button.");

        MatrixOperations matrixDeterminant = new MatrixOperation();
        List<List<Integer>> matrixToCalculate = null;

        // Если выбрана матрица A.
        if (useMatrixA_id.isSelected())
        {
            logger.info("Matrix A is selected.");
            // Считываем данные из текстового окна матрицы A в двумерный список
            // matrixToCalculate для последующей обработки.
            matrixToCalculate = matrixDeterminant.getMatrixFromTextArea(matrixA_id);
        }
        // Если выбрана матрица B.
        else if (useMatrixB_id.isSelected())
        {
            logger.info("Matrix B is selected.");
            // Считываем данные из текстового окна матрицы B в двумерный список
            // matrixToCalculate для последующей обработки.
            matrixToCalculate = matrixDeterminant.getMatrixFromTextArea(matrixB_id);
        }

        // Если двумерный список matrixToCalculate имеет значения.
        if (matrixToCalculate != null && !matrixToCalculate.isEmpty())
        {
            try
            {
                // Рассчитываем определитель матрицы matrixToCalculate с помощью
                // метода calculateDeterminant интерфейса MatrixOperations и
                // сохраняем в переменную determinant.
                double determinant = matrixDeterminant.calculateDeterminant(matrixToCalculate);
                // Выводим значение переменной determinant в текстовое окно с (id) result_id.
                logger.info("The value of the determinant is displayed in a text area.");
                result_id.setText(String.valueOf(determinant));
            }
            catch (IllegalArgumentException e)
            {
                // Обрабатываем ошибку для не квадратной матрицы
                result_id.setText(e.getMessage());
            }
        }
        // Запускаем метод, который проверяет, что активирована одна из двух матриц, и активирует
        // кнопку "Определитель" для вычисления определителя активированной матрицы. В противном
        // случае кнопка "Определитель" отключена (disable).
        updateButtonAvailability();
    }

    /**
     * Метод calculateMatrixSum вычисляет сумму матриц A и B.
     */
    @FXML
    void calculateMatrixSum()
    {
        logger.info("The 'A + B' button is pressed.");
        logger.info("The calculateMatrixSum method has been called for the 'A + B' button.");
        MatrixOperations matrixSum = new MatrixOperation();

        // Получение матриц A и B из текстовых областей ввода
        List<List<Integer>> matrixA = matrixSum.getMatrixFromTextArea(matrixA_id);
        List<List<Integer>> matrixB = matrixSum.getMatrixFromTextArea(matrixB_id);

        // Проверка размеров матриц для выполнения операции сложения
        if (matrixSum.equalityMatrices(matrixA, matrixB))
        {
            logger.info("The sum value is displayed in the text area.");
            List<List<Integer>> resultMatrix = matrixSum.sumMatrices(matrixA, matrixB);

            // Отображаем результат сложения в текстовой области результата
            result_id.setText(matrixSum.matrixToString(resultMatrix));
        }
        else
        {
            result_id.setText("Невозможно выполнить операцию. Размеры матриц не совпадают.");
        }
    }

    /**
     * Метод calculateMatrixDiff вычисляет разность матриц A и B.
     */
    @FXML
    void calculateMatrixDiff()
    {
        logger.info("The 'A - B' button is pressed.");
        logger.info("The calculateMatrixDiff method has been called for the 'A - B' button.");
        MatrixOperations matrixDiff = new MatrixOperation();

        // Получение матриц A и B из текстовых областей ввода
        List<List<Integer>> matrixA = matrixDiff.getMatrixFromTextArea(matrixA_id);
        List<List<Integer>> matrixB = matrixDiff.getMatrixFromTextArea(matrixB_id);

        // Проверка размеров матриц для выполнения операции сложения
        if (matrixDiff.equalityMatrices(matrixA, matrixB))
        {
            logger.info("The difference value is displayed in the text area.");
            List<List<Integer>> resultMatrix = matrixDiff.diffMatrices(matrixA, matrixB);

            // Отображаем результат сложения в текстовой области результата
            result_id.setText(matrixDiff.matrixToString(resultMatrix));
        }
        else
        {
            result_id.setText("Невозможно выполнить операцию. Размеры матриц не совпадают.");
        }
    }

    /**
     * Метод calculateMatrixMul вычисляет произведение матриц A и B.
     */
    @FXML
    void calculateMatrixMul()
    {
        logger.info("The 'A * B' button is pressed.");
        logger.info("The calculateMatrixMul method has been called for the 'A * B' button.");
        MatrixOperations matrixMul = new MatrixOperation();

        // Получение матриц A и B из текстовых областей ввода
        List<List<Integer>> matrixA = matrixMul.getMatrixFromTextArea(matrixA_id);
        List<List<Integer>> matrixB = matrixMul.getMatrixFromTextArea(matrixB_id);

        // Проверка возможности умножения матриц
        if (matrixA.get(0).size() != matrixB.size())
        {
            result_id.setText("Невозможно выполнить операцию. Неправильные размеры матриц.");
        }
        else
        {
            logger.info("The multiplication value is displayed in the text area.");
            List<List<Integer>> resultMatrix = matrixMul.multiplyMatrices(matrixA, matrixB);

            // Отображаем результат умножения в текстовой области результата
            result_id.setText(matrixMul.matrixToString(resultMatrix));
        }
    }

    /**
     * Метод обновляет доступность кнопок на основании того, что матрица A активирована.
     */
    @FXML
    void matrixAActivated()
    {
        logger.info("The 'Matrix A' checkbox is pressed.");
        updateButtonAvailability();
    }

    /**
     * Метод обновляет доступность кнопок на основании того, что матрица B активирована.
     */
    @FXML
    void matrixBActivated()
    {
        logger.info("The 'Matrix B' checkbox is pressed.");
        updateButtonAvailability();
    }

    /**
     * Открывает и отображает в текстовом окне матрицу A из файла.
     */
    @FXML
    void openMatrixAFile()
    {
        MatrixOperations fileConverter = new MatrixOperation();
        MatrixFileReadable fileReader = new MatrixFileReader();

        try
        {
            logger.info("The file is open.");
            List<List<Integer>> matrixA = fileReader.readMatrixFromFile();
            logger.info("The values from the file are displayed in the text area.");
            matrixA_id.setText(fileConverter.matrixToString(matrixA)); // Показать матрицу в TextArea
            updateButtonAvailability();
        }
        catch (IOException e)
        {
            // Обработка ошибок чтения файла
            e.printStackTrace();
        }
    }

    /**
     * Открывает и отображает в текстовом окне матрицу B из файла.
     */
    @FXML
    void openMatrixBFile()
    {
        MatrixOperations fileConverter = new MatrixOperation();
        MatrixFileReadable fileReader = new MatrixFileReader();

        try
        {
            logger.info("The file is open.");
            List<List<Integer>> matrixB = fileReader.readMatrixFromFile();
            logger.info("The values from the file are displayed in the text area.");
            matrixB_id.setText(fileConverter.matrixToString(matrixB)); // Показать матрицу в TextArea
            updateButtonAvailability();
        }
        catch (IOException e)
        {
            // Обработка ошибок чтения файла
            e.printStackTrace();
        }
    }

    @FXML
    void initialize()
    {
        // Пустой
    }

    /**
     * Метод отключает кнопки для операций сложения, вычитания и умножения матриц, если обе
     * матрицы не выбраны с помощью флажков. Также, если одна из матриц
     * выбрана, кнопка для вычисления определителя становится активной.
     */
    private void updateButtonAvailability()
    {
        boolean isMatrixASelected = useMatrixA_id.isSelected();
        boolean isMatrixBSelected = useMatrixB_id.isSelected();

        addMatrixAButton_id.setDisable(!isMatrixASelected);
        addMatrixBButton_id.setDisable(!isMatrixBSelected);

        boolean isSumDisabled = !isMatrixASelected || !isMatrixBSelected;
        boolean isDiffDisabled = !isMatrixASelected || !isMatrixBSelected;
        boolean isMulDisabled = !isMatrixASelected || !isMatrixBSelected;
        boolean isDeterminantDisabled = (isMatrixASelected && !isMatrixBSelected) || (!isMatrixASelected && isMatrixBSelected);

        sumMatrix_id.setDisable(isSumDisabled);
        diffMatrix_id.setDisable(isDiffDisabled);
        mulMatrix_id.setDisable(isMulDisabled);
        matrixDeterminant_id.setDisable(!isDeterminantDisabled);
    }
}
