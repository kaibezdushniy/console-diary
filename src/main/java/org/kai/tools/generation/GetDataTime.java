package org.kai.tools.generation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetDataTime {
    private static final String DATA_TIME_PATTERN = "dd MMMM yyyy HH mm";


    public String dataTime() {
        // Получаем дату и время с компьютера
        LocalDateTime dateTime = LocalDateTime.now();
        // Форматируем полученое значение, и так-же опредялем нужный формат
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATA_TIME_PATTERN);
        String formattedDataTime = dateTime.format(formatter);

        // Возвращаем дату и время
        return formattedDataTime;
    }


}
