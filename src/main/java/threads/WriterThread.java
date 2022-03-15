package threads;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class WriterThread implements Runnable{

    private int[] array;
    private int start;
    private int end;
    private Map elements;

    /**
     * Создание потока для записи элементов массива в Map
     * @param array Массив с элементами
     * @param start Начальный индекс массива для записи
     * @param end Конечный индекс массива для записи
     * @param elements Map для записи элементов
     */
    public WriterThread(int[] array, int start, int end, Map<Integer, Integer> elements) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.elements = elements;
    }

    @Override
    public void run() {
        System.out.println("Поток " + Thread.currentThread().getName() + " начал запись элементов.");
        for(int j = start; j <= end; j++) {
            elements.put(j, array[j]);
        }
        System.out.println("Поток " + Thread.currentThread().getName() + " завершил запись элементов.");
    }
}
