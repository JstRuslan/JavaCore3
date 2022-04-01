package less1;

import java.util.ArrayList;
import java.util.Collections;

public class ChangeItems {
    private double val;

    <T extends Number> ChangeItems(T arg){
        val = arg.doubleValue();
    }


    public static <T> void changeItems(T[] array, int i, int j ){
        T temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T> ArrayList transformToCollection(T[] array){
        ArrayList<T> list = new ArrayList<>();
        Collections.addAll(list, array);
        return list;
    }

}