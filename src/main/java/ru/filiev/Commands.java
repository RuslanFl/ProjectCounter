package ru.filiev;

import java.util.Arrays;

public enum Commands {

    УВЕЛИЧИТЬ_СЧЕТЧИК("/inc", "Значение счетчика увеличино."),
    СТОП("/stop", "Завершаю работу"),
    ОБНУЛИТЬ_СЧЕТЧИК("/reset", "Обнуляю счетчик"),
    СПИСОК_КОМАНД("/help", "Список команд");

    String command;
    String description;

    Commands(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command.replaceAll("[\\s]{2,}", "");
    }

    public String getDescription() {
        return description;
    }

    public static String getAllCommands() {
        var commandList = new StringBuffer("\nСписко допустимых команд:");
        Arrays.stream(Commands.values()).toList()
                .forEach(command ->
                        commandList
                                .append("\n")
                                .append(command.getCommand())
                                .append(" - ")
                                .append(command.getDescription())
                );
        return commandList.toString();
    }
}
