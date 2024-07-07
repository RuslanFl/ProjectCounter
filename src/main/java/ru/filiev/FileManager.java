package ru.filiev;

import java.io.*;

public class FileManager {

    private final String FILE_NAME;
    private final File PATH_TO_FOLDER_STORAGE;


    public FileManager(String fileName) {
        this.FILE_NAME = fileName;
        this.PATH_TO_FOLDER_STORAGE = new File("src/main/resource/temp/folder_counter");
        PATH_TO_FOLDER_STORAGE.mkdirs();
    }

    public FileManager(String pathToFolderStorage, String fileName) {
        this.FILE_NAME = fileName;
        this.PATH_TO_FOLDER_STORAGE = new File(pathToFolderStorage);
        PATH_TO_FOLDER_STORAGE.mkdirs();
    }

    public CounterHistoryPojo readFile() {

        try {
            var objectInputStream = new ObjectInputStream(new FileInputStream(PATH_TO_FOLDER_STORAGE + "/" + FILE_NAME));
            return (CounterHistoryPojo) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Не удалось прочитать файл счетчика. " +
                    "\n" +
                    "Создаем новый файл счетчика, путь файла: " + PATH_TO_FOLDER_STORAGE);
            return new CounterHistoryPojo(0);
        }
    }

    public void writeFile(CounterHistoryPojo counter) {
        try {
            var objectOutputStream = new ObjectOutputStream(new FileOutputStream(PATH_TO_FOLDER_STORAGE + "/" + FILE_NAME));
            objectOutputStream.writeObject(counter);
        } catch (IOException e) {
            throw new RuntimeException("Остановка приложения. Не удалось прочитать файл счетчика по указанному пути: " + PATH_TO_FOLDER_STORAGE, e.getCause());
        }
    }
}