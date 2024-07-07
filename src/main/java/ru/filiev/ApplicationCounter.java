package ru.filiev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static ru.filiev.Commands.СПИСОК_КОМАНД;

public class ApplicationCounter {
    private final FileManager fileManager;

    public ApplicationCounter(String fileName) {
        this.fileManager = new FileManager(fileName);
    }

    public ApplicationCounter(String pathToFolderStorage, String fileName) {
        this.fileManager = new FileManager(pathToFolderStorage, fileName);
    }

    public void run() {
        CounterHistoryPojo counterHistoryPojo = fileManager.readFile();
        System.out.println("Счетчик загружен со сначением: " + counterHistoryPojo.getValue());

        while (true) {
            System.out.print("\nВведите команду или команду '/help' - для получения инструкций:");
            Commands commands = getCommand(new BufferedReader(new InputStreamReader(System.in)));
            switch (commands) {
                case УВЕЛИЧИТЬ_СЧЕТЧИК -> {
                    counterHistoryPojo.incrementValue();
                    System.out.println("Значение счетчика увеличино. Новое значение: " + counterHistoryPojo.getValue());
                    fileManager.writeFile(counterHistoryPojo);
                }
                case СТОП -> {
                    System.out.println("Значение счетчика: " + counterHistoryPojo.getValue() + "\nЗавершение программы");
                    return;
                }
                case ОБНУЛИТЬ_СЧЕТЧИК -> {
                    counterHistoryPojo.resetValue();
                    System.out.println("Значение счетчика сброшено. Новое значение равно: " + counterHistoryPojo.getValue());
                    fileManager.writeFile(counterHistoryPojo);
                }
                case СПИСОК_КОМАНД -> System.out.println(Commands.getAllCommands());
                default ->
                        System.out.println("Команда найдена, но не добавлена в case. \n" + Commands.getAllCommands());
            }
        }
    }

    private Commands getCommand(BufferedReader inputStream) {
        String input;
        try {
            input = inputStream.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка (IOException) ввода/ввывода: ", e.getCause());
        }

        return Arrays.stream(Commands.values()).toList().stream().
                filter(
                        v -> v.getCommand().equals(
                                input.replaceAll("[\\s]{2,}", "")
                        )
                ).findFirst().orElse(СПИСОК_КОМАНД);
    }
}