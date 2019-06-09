package additionalFunction;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static additionalFunction.workWithArray.takeVerticalColumn;

public class workWithMath {


    //мат ожидание
    @NotNull
    public static Double meanWhile(ArrayList<Double> arrayList){
        double result = 0;
        for (int i = 0; i<arrayList.size();i++){
            result+=arrayList.get(i);
        }
        return result/arrayList.size();
    }


    //Вычисление дисперсии
    @NotNull
    public static Double variance(ArrayList<Double> arrayList, Double meanWhile){
        ArrayList<Double> tempArray = new ArrayList<>();
        for(int i = 0;i<arrayList.size();i++){
            tempArray.add(Math.pow((arrayList.get(i)-meanWhile),2));
        }
        return Math.sqrt(meanWhile(tempArray));
    }


    //Вычисление SNR
    @NotNull
    @Contract(pure = true)
    public static Double SNR(Double meanWhile, Double dispersion){
        return meanWhile/dispersion;
    }

    //Перемножение вектора на полученные значения ОСШ
    public static ArrayList<String> multiplicationVectorAndSNR(ArrayList<String> snrList, ArrayList<String> vectorList){
        ArrayList<String> resultList = new ArrayList<>();
        for(int i=0; i<snrList.size();i++){
            resultList.add(String.valueOf(Double.parseDouble(snrList.get(i))*Double.parseDouble(vectorList.get(i))));
        }
        return resultList;
    }

    //Сумма компонент вектора ЭГК
    public static Boolean sumOfVector(ArrayList<Object[]> matrix, int column){
        ArrayList<Double> vector =  workWithArray.takeVerticalColumn(matrix, column);
        double sum = 0;
        for(int j=0;j<vector.size();j++){
            sum+=vector.get(j);
        }
        if(sum<0)
            return false;
        else
            return true;
    }

}
