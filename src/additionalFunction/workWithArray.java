package additionalFunction;



import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class workWithArray {

    //Получение столбца таблицы
    public static  ArrayList<Double> takeVerticalColumn(@NotNull ArrayList<Object[]> arrayList, int column) {
        ArrayList<Double> doubleArrayList = new ArrayList<>();
        for (int j = 0; j < arrayList.size(); j++) {
            doubleArrayList.add(Double.parseDouble("" + arrayList.get(j)[column]));
        }
        return doubleArrayList;
    }

    //Изменение знака у определенного столбца
    public static void changeSingColumn(ArrayList<Object[]> matrix, int columnIndex){
        for(int i = 0; i < matrix.size();i++){
            if(Double.parseDouble(matrix.get(i)[columnIndex].toString())<0) {
                matrix.get(i)[columnIndex] = String.valueOf(Double.parseDouble(matrix.get(i)[columnIndex].toString()) * (-1));
                changeSignRow(matrix, i, columnIndex);
            }
        }

    }

    public static void changeSignRow(ArrayList<Object[]> matrix, int rowIndex, int columnIndex){
        for(int i = 1; i < matrix.get(0).length;i++){
            if(i!=columnIndex)
                matrix.get(rowIndex)[i]=String.valueOf(Double.parseDouble(matrix.get(rowIndex)[i].toString())*(-1));
        }
    }

    public static ArrayList<String> changeNotCorrectNumbersToNull(ArrayList<String> matrix, double snr){
        matrix.remove(0);
        ArrayList<String> resultList = new ArrayList<>();
        for(int i=0;i<matrix.size();i++){
            if(Double.parseDouble(matrix.get(i))<snr){
                resultList.add("0");
            }
            else {
                resultList.add(matrix.get(i));
            }
        }
        return resultList;
    }


}
