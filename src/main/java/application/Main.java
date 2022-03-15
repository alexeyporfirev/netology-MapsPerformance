package application;

import threads.ReaderThread;
import threads.WriterThread;
import tools.ArrayGenerator;

import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    // Кол-во элементов генерируемого массива
    private final static int ARRAY_SIZE = 3_000_000_0;
    // Максимальное значение для элементов генерируемого массива
    private final static int MAX_VALUE_OF_ELEMENT = 10;


    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = Runtime.getRuntime().availableProcessors();

        // выбираем идну из Map
        Map<Integer, Integer> hashMap = new ConcurrentHashMap<Integer, Integer>();
        //Map<Integer, Integer> hashMap = Collections.synchronizedMap(new HashMap<Integer, Integer>());


        // Генерируем массив
        int[] array = ArrayGenerator.generateArray(ARRAY_SIZE, MAX_VALUE_OF_ELEMENT);

        // Содаем пулы потоков для записи и чтения
        final ExecutorService writerPool = Executors.newFixedThreadPool(numberOfThreads);
        final ExecutorService readerPool = Executors.newFixedThreadPool(numberOfThreads);

        // Для равномерного заполнения Map'ы всеми потоками записи вычисляем шаг разбития
        int step = array.length / numberOfThreads;

        // начальное время
        long startTime = System.currentTimeMillis();

        // Запускаем потоки в пулах
        for(int i = 0; i < numberOfThreads; i++) {
            int start = i * step;
            int end = step < (array.length - 1 - (i + 1) * step)?(array.length - 1):(i + 1) * step;
            writerPool.submit(new WriterThread(array, start, end, hashMap));
            readerPool.submit(new ReaderThread(hashMap, ARRAY_SIZE));
        }

        // Ожидаем завершения потоков в пулах
        writerPool.shutdown();
        readerPool.shutdown();
        writerPool.awaitTermination(10000, TimeUnit.MILLISECONDS);
        readerPool.awaitTermination(10000, TimeUnit.MILLISECONDS);

        //конечное время
        long endTime = System.currentTimeMillis();

        System.out.println("Время записи/чтения: " + (endTime - startTime) + " миллисекунд");



    }
}
