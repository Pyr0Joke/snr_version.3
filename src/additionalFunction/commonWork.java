package additionalFunction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import static SNRui.MainWindow.SNRVar;

public class commonWork {
    public static ArrayList<String> commonCalculateSNR(DefaultTableModel tableModel, ArrayList<String> meanWhileList, ArrayList<String> ABSmeanWhile,
                                                       ArrayList<String> varianceList, ArrayList<String> SNRList, int columnCount, ArrayList<Object[]> matrix, JTable table1) {
        //Очищаем списки
        meanWhileList.clear();
        ABSmeanWhile.clear();
        varianceList.clear();
        SNRList.clear();
        //добавляем названия столбцов в таблицу
        meanWhileList.add("Мат ожидание");
        ABSmeanWhile.add("Модуль мат. ожидания");
        varianceList.add("Дисперсия");
        SNRList.add("SNR");

        //проводим рассчеты по всей таблице
        for (int i = 1; i < columnCount; i++) {
            //получаем мат ожидание
            String meanwhile = String.valueOf(workWithMath.meanWhile(workWithArray.takeVerticalColumn(matrix, i)));
//            System.out.println(meanwhile);
            //заносим его в таблицу
            meanWhileList.add(meanwhile);
            //получаем модуль мат ожидания
            String abs = String.valueOf(Math.abs(Double.parseDouble(meanwhile)));
//            System.out.println(abs);
            //заносим его в таблицу
            ABSmeanWhile.add(abs);
            //получаем дисперсию
            String variance = String.valueOf(workWithMath.variance(workWithArray.takeVerticalColumn(matrix, i), Double.parseDouble(meanwhile)));
//            System.out.println(variance);
            //заносим ее в таблицу
            varianceList.add(variance);
            //получем SNR
            String SNR = String.valueOf(workWithMath.SNR(Double.parseDouble(abs), Double.parseDouble(variance)));
//            System.out.println(SNR);
            //заносим ее в таблицу
            SNRList.add(SNR);
        }
        //добавляем строки которые мы заполнили данными выше
        tableModel.addRow(meanWhileList.toArray());
        tableModel.addRow(ABSmeanWhile.toArray());
        tableModel.addRow(varianceList.toArray());
        tableModel.addRow(SNRList.toArray());
        //округляем данные в САМОЙ ТАБЛИЦЕ
        workWithTable.tableTwoDecimals(tableModel);
        //перекрашиваем таблицу
        workWithTable.renderTable(table1);
        return SNRList;
    }

    public static ArrayList<String> calculateSNRForAllDirectionVectors(DefaultTableModel tableModel, ArrayList<String> meanWhileList, ArrayList<String> ABSmeanWhile,
                                                                       ArrayList<String> varianceList, ArrayList<String> SNRList, int columnCount, ArrayList<Object[]> matrix, JTable table1) {


        double tempSNR;
        double resultSNR = 0;
        double result = 0;
        double lastResult = 0;
        double lastResultSNR = 0;
        ArrayList<Object[]> tempMatrix = new ArrayList<>();
        ArrayList<String> tempMeanWhileList = new ArrayList<>();
        ArrayList<String> tempABSmeanWhile = new ArrayList<>();
        ArrayList<String> tempVarianceList = new ArrayList<>();
        ArrayList<String> tempSNRList = new ArrayList<>();
        ArrayList<String> lastMeanWhileList = new ArrayList<>();
        ArrayList<String> lastABSmeanWhile = new ArrayList<>();
        ArrayList<String> lastVarianceList = new ArrayList<>();
        ArrayList<String> lastSNRList = new ArrayList<>();
        //Очищаем списки
        meanWhileList.clear();
        ABSmeanWhile.clear();
        varianceList.clear();
        SNRList.clear();
        //добавляем названия столбцов в таблицу
        meanWhileList.add("Мат ожидание");
        ABSmeanWhile.add("Модуль мат. ожидания");
        varianceList.add("Дисперсия");
        SNRList.add("SNR");


        for (int i = 1; i < columnCount; i++) {
            //получаем мат ожидание
            String meanwhile = String.valueOf(workWithMath.meanWhile(workWithArray.takeVerticalColumn(matrix, i)));
//            System.out.println(meanwhile);
            //заносим его в таблицу
            meanWhileList.add(meanwhile);
            //получаем модуль мат ожидания
            String abs = String.valueOf(Math.abs(Double.parseDouble(meanwhile)));
//            System.out.println(abs);
            //заносим его в таблицу
            ABSmeanWhile.add(abs);
            //получаем дисперсию
            String variance = String.valueOf(workWithMath.variance(workWithArray.takeVerticalColumn(matrix, i), Double.parseDouble(meanwhile)));
//            System.out.println(variance);
            //заносим ее в таблицу
            varianceList.add(variance);
            //получем SNR
            String SNR = String.valueOf(workWithMath.SNR(Double.parseDouble(abs), Double.parseDouble(variance)));
//            System.out.println(SNR);
            //заносим ее в таблицу
            SNRList.add(SNR);
        }


        //В цикле подсчитываем общую сумму по строке (массиву) и те, которые удовлетворяют условию, > установленный пользователем SNR
        for (int i = 1; i < SNRList.size(); i++) {
            tempSNR = Double.parseDouble(SNRList.get(i));
            result += tempSNR;
            if (tempSNR > SNRVar) {
                resultSNR += tempSNR;
            }
        }

        lastResultSNR = resultSNR;
        lastResult = result;
//        tempMatrix=matrix;
//        tempMeanWhileList=meanWhileList;
//        tempABSmeanWhile=ABSmeanWhile;
//        tempVarianceList=varianceList;
//        tempSNRList=SNRList;
        tempMatrix.clear();
        tempMatrix.addAll(matrix);
        tempMeanWhileList.clear();
        tempMeanWhileList.addAll(meanWhileList);
        tempABSmeanWhile.clear();
        tempABSmeanWhile.addAll(ABSmeanWhile);
        tempVarianceList.clear();
        tempVarianceList.addAll(varianceList);
        tempSNRList.clear();
        tempSNRList.addAll(SNRList);

        for (int j = 1; j < columnCount; j++) {
            resultSNR=0;
            result=0;
            //Очищаем списки
            meanWhileList.clear();
            ABSmeanWhile.clear();
            varianceList.clear();
            SNRList.clear();
            //добавляем названия столбцов в таблицу
            meanWhileList.add("Мат ожидание");
            ABSmeanWhile.add("Модуль мат. ожидания");
            varianceList.add("Дисперсия");
            SNRList.add("SNR");

            workWithArray.changeSingColumn(matrix, j);

            for (int i = 1; i < columnCount; i++) {
                //получаем мат ожидание
                String meanwhile = String.valueOf(workWithMath.meanWhile(workWithArray.takeVerticalColumn(matrix, i)));
//            System.out.println(meanwhile);
                //заносим его в таблицу
                meanWhileList.add(meanwhile);
                //получаем модуль мат ожидания
                String abs = String.valueOf(Math.abs(Double.parseDouble(meanwhile)));
//            System.out.println(abs);
                //заносим его в таблицу
                ABSmeanWhile.add(abs);
                //получаем дисперсию
                String variance = String.valueOf(workWithMath.variance(workWithArray.takeVerticalColumn(matrix, i), Double.parseDouble(meanwhile)));
//            System.out.println(variance);
                //заносим ее в таблицу
                varianceList.add(variance);
                //получем SNR
                String SNR = String.valueOf(workWithMath.SNR(Double.parseDouble(abs), Double.parseDouble(variance)));
//            System.out.println(SNR);
                //заносим ее в таблицу
                SNRList.add(SNR);
            }


            //В цикле подсчитываем общую сумму по строке (массиву) и те, которые удовлетворяют условию, > установленный пользователем SNR
            for (int i = 1; i < SNRList.size(); i++) {
                tempSNR = Double.parseDouble(SNRList.get(i));
                result += tempSNR;
                if (tempSNR > SNRVar) {
                    resultSNR += tempSNR;
                }
            }

            if(resultSNR>lastResultSNR){
                lastResultSNR=resultSNR;
                lastResult=result;
                tempMatrix.clear();
                tempMatrix.addAll(matrix);
                tempMeanWhileList.clear();
                tempMeanWhileList.addAll(meanWhileList);
                tempABSmeanWhile.clear();
                tempABSmeanWhile.addAll(ABSmeanWhile);
                tempVarianceList.clear();
                tempVarianceList.addAll(varianceList);
                tempSNRList.clear();
                tempSNRList.addAll(SNRList);

            }


        }
        workWithTable.clearTable(tableModel);
        workWithTable.conclusionTable(tempMatrix, tableModel);
        tableModel.addRow(tempMeanWhileList.toArray());
        tableModel.addRow(tempABSmeanWhile.toArray());
        tableModel.addRow(tempVarianceList.toArray());
        tableModel.addRow(tempSNRList.toArray());

        //округляем данные в САМОЙ ТАБЛИЦЕ
        workWithTable.tableTwoDecimals(tableModel);
        //перекрашиваем таблицу
        workWithTable.renderTable(table1);

        ArrayList<String> snrResultList = workWithTable.alertWithSumSNR(tempSNRList);

        ArrayList<String> resultList = new ArrayList<>();
        resultList.addAll(tempSNRList);
        resultList.add(" ");
        resultList.addAll(snrResultList);
        return resultList;
    }

    public static ArrayList<String> calculateForBook(DefaultTableModel tableModel, ArrayList<String> meanWhileList, ArrayList<String> ABSmeanWhile,
                                        ArrayList<String> varianceList, ArrayList<String> SNRList, int columnCount, ArrayList<Object[]> matrix, JTable table1){

        double tempSNR;
        double resultSNR = 0;
        double result = 0;
        double lastResult = 0;
        double lastResultSNR = 0;
        ArrayList<Object[]> tempMatrix = new ArrayList<>();
        ArrayList<String> tempMeanWhileList = new ArrayList<>();
        ArrayList<String> tempABSmeanWhile = new ArrayList<>();
        ArrayList<String> tempVarianceList = new ArrayList<>();
        ArrayList<String> tempSNRList = new ArrayList<>();
        ArrayList<String> lastMeanWhileList = new ArrayList<>();
        ArrayList<String> lastABSmeanWhile = new ArrayList<>();
        ArrayList<String> lastVarianceList = new ArrayList<>();
        ArrayList<String> lastSNRList = new ArrayList<>();
        //Очищаем списки
        meanWhileList.clear();
        ABSmeanWhile.clear();
        varianceList.clear();
        SNRList.clear();
        //добавляем названия столбцов в таблицу
        meanWhileList.add("Мат ожидание");
        ABSmeanWhile.add("Модуль мат. ожидания");
        varianceList.add("СКО");
        SNRList.add("SNR");


        for (int i = 1; i < columnCount; i++) {
            //получаем мат ожидание
            String meanwhile = String.valueOf(workWithMath.meanWhile(workWithArray.takeVerticalColumn(matrix, i)));
//            System.out.println(meanwhile);
            //заносим его в таблицу
            meanWhileList.add(meanwhile);
            //получаем модуль мат ожидания
            String abs = String.valueOf(Math.abs(Double.parseDouble(meanwhile)));
//            System.out.println(abs);
            //заносим его в таблицу
            ABSmeanWhile.add(abs);
            //получаем дисперсию
            String variance = String.valueOf(workWithMath.variance(workWithArray.takeVerticalColumn(matrix, i), Double.parseDouble(meanwhile)));
//            System.out.println(variance);
            //заносим ее в таблицу
            varianceList.add(variance);
            //получем SNR
            String SNR = String.valueOf(workWithMath.SNR(Double.parseDouble(abs), Double.parseDouble(variance)));
//            System.out.println(SNR);
            //заносим ее в таблицу
            SNRList.add(SNR);
        }


        //В цикле подсчитываем общую сумму по строке (массиву) и те, которые удовлетворяют условию, > установленный пользователем SNR
        for (int i = 1; i < SNRList.size(); i++) {
            tempSNR = Double.parseDouble(SNRList.get(i));
            result += tempSNR;
            if (tempSNR > SNRVar) {
                resultSNR += tempSNR;
            }
        }

        lastResultSNR = resultSNR;
        lastResult = result;
        tempMatrix.clear();
        tempMatrix.addAll(matrix);
        tempMeanWhileList.clear();
        tempMeanWhileList.addAll(meanWhileList);
        tempABSmeanWhile.clear();
        tempABSmeanWhile.addAll(ABSmeanWhile);
        tempVarianceList.clear();
        tempVarianceList.addAll(varianceList);
        tempSNRList.clear();
        tempSNRList.addAll(SNRList);

        for (int j = 1; j < columnCount; j++) {
            resultSNR=0;
            result=0;
            //Очищаем списки
            meanWhileList.clear();
            ABSmeanWhile.clear();
            varianceList.clear();
            SNRList.clear();
            //добавляем названия столбцов в таблицу
            meanWhileList.add("Мат ожидание");
            ABSmeanWhile.add("Модуль мат. ожидания");
            varianceList.add("СКО");
            SNRList.add("SNR");

            workWithArray.changeSingColumn(matrix, j);

            for (int i = 1; i < columnCount; i++) {
                //получаем мат ожидание
                String meanwhile = String.valueOf(workWithMath.meanWhile(workWithArray.takeVerticalColumn(matrix, i)));
//            System.out.println(meanwhile);
                //заносим его в таблицу
                meanWhileList.add(meanwhile);
                //получаем модуль мат ожидания
                String abs = String.valueOf(Math.abs(Double.parseDouble(meanwhile)));
//            System.out.println(abs);
                //заносим его в таблицу
                ABSmeanWhile.add(abs);
                //получаем дисперсию
                String variance = String.valueOf(workWithMath.variance(workWithArray.takeVerticalColumn(matrix, i), Double.parseDouble(meanwhile)));
//            System.out.println(variance);
                //заносим ее в таблицу
                varianceList.add(variance);
                //получем SNR
                String SNR = String.valueOf(workWithMath.SNR(Double.parseDouble(abs), Double.parseDouble(variance)));
//            System.out.println(SNR);
                //заносим ее в таблицу
                SNRList.add(SNR);
            }


            //В цикле подсчитываем общую сумму по строке (массиву) и те, которые удовлетворяют условию, > установленный пользователем SNR
            for (int i = 1; i < SNRList.size(); i++) {
                tempSNR = Double.parseDouble(SNRList.get(i));
                result += tempSNR;
                if (tempSNR > SNRVar) {
                    resultSNR += tempSNR;
                }
            }

            if(resultSNR>lastResultSNR){
                lastResultSNR=resultSNR;
                lastResult=result;
                tempMatrix.clear();
                tempMatrix.addAll(matrix);
                tempMeanWhileList.clear();
                tempMeanWhileList.addAll(meanWhileList);
                tempABSmeanWhile.clear();
                tempABSmeanWhile.addAll(ABSmeanWhile);
                tempVarianceList.clear();
                tempVarianceList.addAll(varianceList);
                tempSNRList.clear();
                tempSNRList.addAll(SNRList);

            }


        }
        workWithTable.clearTable(tableModel);
        workWithTable.conclusionTable(tempMatrix, tableModel);
        tableModel.addRow(tempMeanWhileList.toArray());
        tableModel.addRow(tempABSmeanWhile.toArray());
        tableModel.addRow(tempVarianceList.toArray());
        tableModel.addRow(tempSNRList.toArray());

        //округляем данные в САМОЙ ТАБЛИЦЕ
        workWithTable.tableTwoDecimals(tableModel);
        //перекрашиваем таблицу
        workWithTable.renderTable(table1);

        ArrayList<String> snrResultList = workWithTable.alertWithSumSNR(tempSNRList);

        ArrayList<String> resultList = new ArrayList<>();
        resultList.addAll(tempSNRList);
        resultList.add(" ");
        resultList.addAll(snrResultList);
        return resultList;
    }

    public static ArrayList<ArrayList<String>> multiplyVectorsSNROnDownloadVector(ArrayList<ArrayList<String>> matrix, ArrayList<String> vector){
        ArrayList<ArrayList<String>> tempMatrix = new ArrayList<>();
        for(int i=0; i<matrix.size();i++){
            ArrayList<String> tempList = new ArrayList<>();
            for (int j=1; j<matrix.get(0).size()-4;j++){
                tempList.add(String.valueOf(Double.parseDouble(matrix.get(i).get(j))*Math.sqrt(Double.parseDouble(vector.get(i)))));
            }
            tempMatrix.add(tempList);
        }
        return tempMatrix;
    }

    public static void writeMultVectorOnTable(DefaultTableModel tableModel, ArrayList<ArrayList<String>> matrix){
        for(int j=0; j<matrix.get(0).size(); j++)
            tableModel.addColumn(j);
        for (int i=0; i< matrix.size(); i++){
            tableModel.addRow(matrix.get(i).toArray());
        }
        workWithTable.tableTwoForEndVectorsDecimals(tableModel);
    }


}
