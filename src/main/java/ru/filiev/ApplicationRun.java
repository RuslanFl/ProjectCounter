package ru.filiev;

public class ApplicationRun {

    public static void main(String[] args) {
        var applicationCounter = new ApplicationCounter("counter_value_history.txt");
//       var applicationCounter = new ApplicationCounter("test_folder_1/test_folder_2/folder_counter", "counter_value_history.txt");
        applicationCounter.run();
    }

}