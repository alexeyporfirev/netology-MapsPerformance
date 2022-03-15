package tools;

import java.util.Random;

public class ArrayGenerator {

    /**
     * Метод формирования массива целых чисел
     * @param size заднный размер массива
     * @param maxValue заданное максимлаьное значение для элементов массива
     * @return ссылка на создынный массив
     */
    public static int[] generateArray(int size, int maxValue) {
        int[] array = new int[size];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(maxValue);
        }
        return array;
    }
}