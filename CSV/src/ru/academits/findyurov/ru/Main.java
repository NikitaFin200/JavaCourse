package ru.academits.findyurov.ru;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            CSVToHTMLConverter.convert("fileForTaskCSV.csv", "CSV.html");
            System.out.println("The conversion was completed successfully");
        } catch (IOException e) {
            System.out.println("Conversion error:" + e.getMessage());
        }
    }

    public static class CSVToHTMLConverter {
        public static void convert(String csvFilePath, String htmlFilePath) throws IOException {
            // Чтение файла CSV
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String line;
            String[] headers = null; // заголовки таблицы
            StringBuilder tableData = new StringBuilder(); // данные таблицы

            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(","); // разбиение строки на ячейки по запятой
                // первая строка - заголовки таблицы, остальные строки - данные
                if (headers == null) {
                    headers = cells;
                } else {
                    tableData.append("<tr>"); // начало строки таблицы

                    for (String cell : cells) {
                        tableData.append("<td>").append(cell).append("</td>"); // добавление ячейки
                    }

                    tableData.append("</tr>"); // конец строки таблицы
                    tableData.append("<br/>");
                }
            }

            reader.close();

            // Запись файла HTML
            FileWriter writer = new FileWriter(htmlFilePath);

            writer.write("<table>");
            writer.write("<tr>"); // начало строки заголовков

            for (String header : headers) {
                writer.write("<th>");
                writer.write(header);
                writer.write("</th>"); // добавление заголовка
                tableData.append("<br/>");
            }

            writer.write("</tr>"); // конец строки заголовков
            writer.write(tableData.toString()); // добавление данных таблицы
            writer.write("</table>");
            writer.close();
        }
    }
}