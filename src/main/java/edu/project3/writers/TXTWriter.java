package edu.project3.writers;

import edu.project3.LogReport;
import lombok.AllArgsConstructor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import static edu.project3.writers.WriterUtils.FORMATTER;

@AllArgsConstructor
public class TXTWriter implements Writer {
    private LogReport logReport;

    @Override
    public void write() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("logReport.txt");
             PrintWriter printWriter = new PrintWriter(fileOutputStream)) {
            printWriter.println(getTextString());
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to the file", e);
        }
    }

    private String getTextString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getGeneralInfo());
        stringBuilder.append(getResources());
        stringBuilder.append(getAnswers());
        stringBuilder.append(getCodes());
        stringBuilder.append(getTypes());
        return stringBuilder.toString();
    }

    private String getGeneralInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Общая информация\n");
        stringBuilder.append("Метрика\tЗначение\n");
        stringBuilder.append(String.format(
            "Начальная дата\t%s\n",
            logReport.startDate() == null ? "-" : logReport.startDate().format(FORMATTER)
        ));
        stringBuilder.append(String.format(
            "Конечная дата\t%s\n",
            logReport.endDate() == null ? "-" : logReport.endDate().format(FORMATTER)
        ));
        stringBuilder.append(String.format("Количество запросов\t%d\n", logReport.totalAmount()));
        stringBuilder.append(String.format("Средний размер ответа\t%d\n", logReport.averageSize()));
        stringBuilder.append(String.format(
            "День с наибольшим числом запросов\t%s\n",
            logReport.dayWithMaxAmountRequests() == null ? "-" : logReport.dayWithMaxAmountRequests().format(FORMATTER)
        ));
        stringBuilder.append(String.format(
            "Наибольшее число запросов за день\t%d\n",
            logReport.maxAmountRequestsPerDay()
        ));
        return stringBuilder.toString();
    }

    private String getResources() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nЗапрашиваемые ресурсы\n");
        stringBuilder.append("Ресурс\tКоличество\n");
        for (var resourceWithAmount : logReport.mostPopularResources()) {
            stringBuilder.append(String.format(
                "%s\t%d\n",
                resourceWithAmount.getKey(),
                resourceWithAmount.getValue()
            ));
        }
        return stringBuilder.toString();
    }

    private String getAnswers() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nЗапрашиваемые ресурсы\n");
        stringBuilder.append("Ресурс\tКоличество\n");
        for (var resourceWithAmount : logReport.mostPopularResources()) {
            stringBuilder.append(String.format(
                "%s\t%d\n",
                resourceWithAmount.getKey(),
                resourceWithAmount.getValue()
            ));
        }
        return stringBuilder.toString();
    }

    private String getCodes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nКоды ответа\n");
        stringBuilder.append("Код\tИмя\tКоличество\n");
        for (var codeWithAmount : logReport.mostPopularAnswers()) {
            stringBuilder.append(String.format(
                "%d\t%s\t%d\n",
                codeWithAmount.getKey(),
                WriterUtils.getHttpStatusDescriptions().get(codeWithAmount.getKey()),
                codeWithAmount.getValue()
            ));
        }
        return stringBuilder.toString();
    }

    private String getTypes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nТипы запроса\n");
        stringBuilder.append("Тип\tКоличество\n");
        for (var typeWithAmount : logReport.mostPopularTypes()) {
            stringBuilder.append(String.format(
                "%s\t%d\n",
                typeWithAmount.getKey(),
                typeWithAmount.getValue()
            ));
        }
        return stringBuilder.toString();
    }
}
