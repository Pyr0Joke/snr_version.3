package SNRui;

import additionalFunction.*;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class MainWindow extends JFrame {
    private JPanel panel; // панель
    private JButton button1; // кнопка загрузки файла
    private JTable table1; // таблица с данными
    private JButton calculateButton; //кнопка для вычисления
    private JButton downloadExampleFileButton; //кнопка для загрузки файла примера
    private JTextField textField1; //текстовое поле для ввода значения SNR
    private JButton clearTableButton; // кнопка очистки таблицы
    private JButton changeSignAndReloadButton; // кнопка по изменению знака и перерисовке таблицы
    private JButton saveTableButton; //кнопка сохранения таблицы
    private JTextField a1TextField; //поле для ввода номера колонки для изменения знака
    private JButton saveSNRInMemory; //кнопка для добавления последнего высчитанного значения в SNRDataList
    private JButton clearSNRDataInButton; //кнопка для очистка списка SNRDataList
    private JButton downloadVectorOwnNumbersButton;
    private JButton multVectorAndSNRButton;
    private JButton saveSNRResultButton;
    private JButton saveDataVectorAndButton;
    private JButton calculateMaxSNRForButton;
    private JButton downloadDataBookButton;
    private JTextField a1TextField1;
    private JButton calculateForSheetButton;
    private JButton saveResultsInFileButton;
    private DefaultTableModel tableModel; // модель таблицы
    private ArrayList<Object[]> matrix = new ArrayList<>(); // матрица
    private int columnCount = 0; // количество колонок
    private ArrayList<String> meanWhileList = new ArrayList<>(); //список мат ожиданий
    private ArrayList<String> varianceList = new ArrayList<>(); // список дисперсии
    private ArrayList<String> SNRList = new ArrayList<>(); // список значений SNR
    private ArrayList<String> ABSmeanWhile = new ArrayList<>(); // список модуля мат ожиданий
    private ArrayList<String> SNRDataList = new ArrayList<>(); // список с вычисленными значениями суммы SNR
    private ArrayList<String> SNRDataMaxList = new ArrayList<>(); // список SNR значений по одному из вариантов вычислений
    private ArrayList<String> SNRDataWithNullsMaxList = new ArrayList<>(); // список SNR значений по одному из вариантов вычислений с нулями
    private ArrayList<String> ownNumbersList = new ArrayList<>(); // Вектор с собственными числами
    private ArrayList<String> SNRListWithTempResultList = new ArrayList<>(); // Вектор с значением SNR и суммой по строке
    private ArrayList<String> SNRResultsWithVectorAndResult = new ArrayList<>(); // Вектор с значением SNR и суммой по строке полный
    private ArrayList<ArrayList<String>> SNRResultsArrayForBookResult = new ArrayList<>(); // Вектор с значением SNR и суммой по строке полный
    private ArrayList<ArrayList<Object[]>> book = new ArrayList<>(); // книга
    public static double SNRVar; // значение SNR из текстового поля
    public static double SNRTempData; // временное значение для SNRDataList


    public MainWindow() {
        MainWindow.getFrames()[0].setTitle("SNR");
        //добавляем панель
        this.getContentPane().add(panel);
        String col[] = {};
        //создаем DefaultTableModel для нашей таблицы
        tableModel = new DefaultTableModel(col, 0);
        //устанавливаем DefaultTableModel в нашу таблицу
        table1.setModel(tableModel);

        //лисенер для загрузки файла
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                columnCount = workWithFiles.downloadFile(tableModel, matrix);
            }
        });

        //лисенер для простых вычислений
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Заносим в переменную значение из поля
                SNRVar = Double.parseDouble(textField1.getText());
                //Вызываем функцию для простого подсчета, которая возвращает список snr значений для данных вычислений
                SNRDataMaxList = commonWork.commonCalculateSNR(tableModel, meanWhileList, ABSmeanWhile, varianceList, SNRList, columnCount, matrix, table1);
                //подсчитываем и выводим сумму действующей ОСШ и сумму по строке
                SNRListWithTempResultList = workWithTable.alertWithSumSNR(SNRList);
                //меняем в списке snr все значения, который не удовлетворяют требованию на 0
                SNRDataWithNullsMaxList = workWithArray.changeNotCorrectNumbersToNull(SNRDataMaxList, SNRVar);
                System.out.println("sdfsdfsdf");
            }
        });

        //лисенер для загрузки файла
        downloadExampleFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //возвращает количество колонок и загружает файл
//                columnCount = workWithFiles.downloadExampleFile(tableModel, matrix);
            }
        });
        //Очистка таблицы
        clearTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workWithTable.clearTable(tableModel);
            }
        });
        //изменение знака и перерисовка таблицы
        changeSignAndReloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int columnIndex = Integer.parseInt(a1TextField.getText());
                //Очистка таблицы
                workWithTable.clearTable(tableModel);
                //Смена знака у колонки
                workWithArray.changeSingColumn(matrix, columnIndex);
                //Вывод таблицы на экран
                workWithTable.conclusionTable(matrix, tableModel);
            }
        });
        saveTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    workWithFiles.saveFile(table1);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
        saveSNRInMemory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SNRDataList.add(String.valueOf(SNRTempData));
            }
        });
        clearSNRDataInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SNRDataList.clear();
            }
        });
        downloadVectorOwnNumbersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workWithFiles.downloadVector(ownNumbersList);
            }
        });
        saveSNRResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        multVectorAndSNRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workWithMath.multiplicationVectorAndSNR(SNRDataMaxList, ownNumbersList);
            }
        });
        saveDataVectorAndButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workWithFiles.saveSNRVectorAndResultsInFile(SNRDataMaxList, SNRListWithTempResultList);
            }
        });
        calculateMaxSNRForButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SNRVar = Double.parseDouble(textField1.getText());
                SNRResultsWithVectorAndResult = commonWork.calculateSNRForAllDirectionVectors(tableModel, meanWhileList, ABSmeanWhile, varianceList, SNRList, columnCount, matrix, table1);
                workWithFiles.saveSNRVectorAndResultsInFileInOneVector(SNRResultsWithVectorAndResult);
            }
        });
        downloadDataBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                book = workWithFiles.downloadBook(tableModel);
            }
        });
        calculateForSheetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SNRVar = Double.parseDouble(textField1.getText());
                ArrayList<String> result = commonWork.calculateForBook(tableModel, meanWhileList, ABSmeanWhile, varianceList, SNRList, book.get(Integer.parseInt(a1TextField1.getText())-1).get(0).length, book.get(Integer.parseInt(a1TextField1.getText())-1), table1);
//                workWithFiles.saveSNRVectorAndResultsInFileInOneVector(result);
                SNRResultsArrayForBookResult.add(result);

            }
        });
        saveResultsInFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workWithFiles.saveInFileResultOfBookCalcilated(SNRResultsArrayForBookResult);
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {

        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(10, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.setPreferredSize(new Dimension(30, 30));
        button1 = new JButton();
        button1.setText("Download file");
        panel.add(button1, new GridConstraints(0, 0, 0, 0, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50,50), new Dimension(50,50), new Dimension(50,50), 0, false));
        calculateButton = new JButton();
        calculateButton.setText("Calculate");
        panel.add(calculateButton, new GridConstraints(1, 0, 0, 0,  GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50,50), new Dimension(50,50), new Dimension(50,50), 0, false));
        textField1 = new JTextField();
        textField1.setText("1");
        panel.add(textField1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        a1TextField = new JTextField();
        a1TextField.setText("1");
        panel.add(a1TextField, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        downloadExampleFileButton = new JButton();
        downloadExampleFileButton.setText("Download example file");
        panel.add(downloadExampleFileButton, new GridConstraints(3, 0, 0, 0,  GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        table1 = new JTable();
        panel.add(table1, new GridConstraints(0, 1, 22, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(500, 50), null, 0, false));
        clearTableButton = new JButton();
        clearTableButton.setText("Clear table");
        panel.add(clearTableButton, new GridConstraints(3, 0, 0, 0, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        changeSignAndReloadButton = new JButton();
        changeSignAndReloadButton.setText("Change sign and reload table");
        panel.add(changeSignAndReloadButton, new GridConstraints(4, 0, 0, 0, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveTableButton = new JButton();
        saveTableButton.setText("Save Table");
        panel.add(saveTableButton, new GridConstraints(6, 0, 0, 0, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}


