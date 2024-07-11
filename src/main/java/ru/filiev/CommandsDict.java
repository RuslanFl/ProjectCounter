package ru.filiev;

public enum CommandsDict {

    УВЕЛИЧИТЬ_СЧЕТЧИК("/inc", "Значение счетчика увеличино."),
    СТОП("/stop", "Завершаю работу"),
    ОБНУЛИТЬ_СЧЕТЧИК("/reset", "Обнуляю счетчик"),
    СПИСОК_КОМАНД("/help", "Список команд");

    String command;
    String description;

    CommandsDict(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command.replaceAll("[\\s]{2,}", "");
    }

    public String getDescription() {
        return description;
    }


}
