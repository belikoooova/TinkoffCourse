package edu.project3.writers;

import edu.project3.LogReport;
import lombok.AllArgsConstructor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import static edu.project3.writers.WriterUtils.FORMATTER;

@AllArgsConstructor
public class ADOCWriter implements Writer {
    private LogReport logReport;

    @Override
    public void write() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("logReport.md");
             PrintWriter printWriter = new PrintWriter(fileOutputStream)) {
            printWriter.println(getAdocString());
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to the file", e);
        }
    }

    private String getAdocString() {
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
        stringBuilder.append("==== Общая информация\n\n");
        stringBuilder.append("[options=\"header\"]\n");
        stringBuilder.append("|===\n");
        stringBuilder.append("| Метрика | Значение\n");

        stringBuilder.append(String.format(
            "| Начальная дата | %s\n",
            logReport.startDate() == null ? "-" : logReport.startDate().format(FORMATTER)
        ));
        stringBuilder.append(String.format(
            "| Конечная дата | %s\n",
            logReport.endDate() == null ? "-" : logReport.endDate().format(FORMATTER)
        ));
        stringBuilder.append(String.format("| Количество запросов | %d\n", logReport.totalAmount()));
        stringBuilder.append(String.format("| Средний размер ответа | %d\n", logReport.averageSize()));
        stringBuilder.append(String.format(
            "| День с наибольшим числом запросов | %s\n",
            logReport.dayWithMaxAmountRequests() == null ? "-" : logReport.dayWithMaxAmountRequests().format(FORMATTER)
        ));
        stringBuilder.append(String.format(
            "| Наибольшее число запросов за день | %d\n",
            logReport.maxAmountRequestsPerDay()
        ));

        stringBuilder.append("|===\n");
        return stringBuilder.toString();
    }

    private String getResources() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== Запрашиваемые ресурсы\n\n");
        stringBuilder.append("[options=\"header\"]\n");
        stringBuilder.append("|===\n");
        stringBuilder.append("| Ресурс | Количество\n");

        for (var resourceWithAmount : logReport.mostPopularResources()) {
            stringBuilder.append(String.format(
                "| %s | %d\n",
                resourceWithAmount.getKey(),
                resourceWithAmount.getValue()
            ));
        }

        stringBuilder.append("|===\n");
        return stringBuilder.toString();
    }

    private String getAnswers() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== Запрашиваемые ресурсы\n\n");
        stringBuilder.append("[options=\"header\"]\n");
        stringBuilder.append("|===\n");
        stringBuilder.append("| Ресурс | Количество\n");

        for (var resourceWithAmount : logReport.mostPopularResources()) {
            stringBuilder.append(String.format(
                "| %s | %d\n",
                resourceWithAmount.getKey(),
                resourceWithAmount.getValue()
            ));
        }

        stringBuilder.append("|===\n");
        return stringBuilder.toString();
    }

    private String getCodes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== Коды ответа\n\n");
        stringBuilder.append("[options=\"header\"]\n");
        stringBuilder.append("|===\n");
        stringBuilder.append("| Код | Имя | Количество\n");

        for (var codeWithAmount : logReport.mostPopularAnswers()) {
            stringBuilder.append(String.format(
                "| %d | %s | %d\n",
                codeWithAmount.getKey(),
                WriterUtils.getHttpStatusDescriptions().get(codeWithAmount.getKey()),
                codeWithAmount.getValue()
            ));
        }

        stringBuilder.append("|===\n");
        return stringBuilder.toString();
    }

    private String getTypes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==== Типы запроса\n\n");
        stringBuilder.append("[options=\"header\"]\n");
        stringBuilder.append("|===\n");
        stringBuilder.append("| Тип | Количество\n");

        for (var typeWithAmount : logReport.mostPopularTypes()) {
            stringBuilder.append(String.format(
                "| %s | %d\n",
                typeWithAmount.getKey(),
                typeWithAmount.getValue()
            ));
        }

        stringBuilder.append("|===\n");
        return stringBuilder.toString();
    }

}
