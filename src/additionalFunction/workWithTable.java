package additionalFunction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;

import static SNRui.MainWindow.SNRVar;

public class workWithTable {

    //обработка таблицы для отображения двух цифр после запятой
    public static void tableTwoDecimals(@NotNull DefaultTableModel tableModel){

        for(int i = 1 ; i<tableModel.getColumnCount(); i++){
            for(int j = 0; j<tableModel.getRowCount();j++){
                if(tableModel.getValueAt(j,i)!=null)
                    tableModel.setValueAt(BigDecimal.valueOf( Double.parseDouble( tableModel.getValueAt(j,i).toString())).
                            setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue(), j ,i);
            }
        }
    }

    public static void tableTwoForEndVectorsDecimals(@NotNull DefaultTableModel tableModel){
        for(int i = 0 ; i<tableModel.getColumnCount(); i++){
            for(int j = 0; j<tableModel.getRowCount();j++){
                if(tableModel.getValueAt(j,i)!=null)
                    tableModel.setValueAt(BigDecimal.valueOf( Double.parseDouble( tableModel.getValueAt(j,i).toString())).
                            setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue(), j ,i);
            }
        }
    }
    //Окрашивание столбцов таблицы по значению SNR
    public static void renderTable(@NotNull JTable table){
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new SNRRenderer());
        }
    }

    public static ArrayList<String> alertWithSumSNR(@NotNull ArrayList<String> SNRList){
        //Переменные в которых хранятся данные
        ArrayList<String> resultList = new ArrayList<>();
        double tempSNR;
        double resultSNR = 0;
        double result = 0;
        //В цикле подсчитываем общую сумму по строке (массиву) и те, которые удовлетворяют условию, > установленный пользователем SNR
        for (int i = 1; i < SNRList.size(); i++) {
            tempSNR = Double.parseDouble(SNRList.get(i));
            result += tempSNR;
            if (tempSNR > SNRVar) {
                resultSNR += tempSNR;
            }
        }
        //Выводим на экран в алерте сумму действующей ОСШ и сумму по строке
        JOptionPane.showMessageDialog(null, "Сумма действующей ОСШ =" +
                BigDecimal.valueOf(resultSNR).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue() +
                "\nСумма по строке = " + BigDecimal.valueOf(result).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
        resultList.add(String.valueOf(resultSNR));
        resultList.add(String.valueOf(result));

        return resultList;
    }

    //Очистка таблицы
    public static void clearTable(@NotNull DefaultTableModel tableModel){
        tableModel.setColumnCount(0);
        tableModel.setNumRows(0);
    }

    //Вывод таблицы на экран
    public static void conclusionTable(@NotNull ArrayList<Object []> matrix, DefaultTableModel tableModel){
        //Добавляем колонки
        for(int i = 0; i < matrix.get(0).length; i++){
            tableModel.addColumn(i);
        }
        //Создаем строки и добавляем его в DefaultTableModel
        for(int i = 0; i< matrix.size(); i++){
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(i+1 + " наблюдение");
            for(int j = 1;j<matrix.get(i).length; j++) {
                arrayList.add(matrix.get(i)[j].toString());
            }
            tableModel.addRow(arrayList.toArray());
        }
    }


}
