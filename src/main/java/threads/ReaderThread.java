package threads;

import java.util.Map;

public class ReaderThread implements Runnable{

    private Map<Integer, Integer> elements;
    private int size;

    /**
     * Содание потока для чтения из Map
     * @param elements Объект-Map для чтения
     * @param size Общее кол-во элементов, которые надо будет считать
     */
    public ReaderThread(Map<Integer, Integer> elements, int size) {
        this.elements = elements;
        this.size = size;
    }

    @Override
    public void run() {
        int i = 0;
        System.out.println("Поток " + Thread.currentThread().getName() + " начал чтение элементов.");
        while(i < size) {
            // Если элемента с таким ключом нет, то ждем, когда он появится
            while(elements.get(i) == null) ;
            elements.get(i);
            i++;
        }
        System.out.println("Поток " + Thread.currentThread().getName() + " завершил чтение элементов." +
                "Прочитано " + i + " элементов.");

    }
}
