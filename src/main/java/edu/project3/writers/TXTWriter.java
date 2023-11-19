package edu.project3.writers;

import edu.project3.logs.LogReport;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.AllArgsConstructor;
import static edu.project3.writers.WriterUtils.FORMATTER;

@AllArgsConstructor
public class TXTWriter implements Writer {
    private LogReport logReport;
    private static final String PATH = "logReport.txt";
    private static final String TWO_ARGS_LINE = "%s\t%d\n";

    @Override
    public void write() {
        WriterUtils.deleteIfExist(PATH);
        try (FileOutputStream fileOutputStream = new FileOutputStream(PATH);
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
                TWO_ARGS_LINE,
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
                TWO_ARGS_LINE,
                typeWithAmount.getKey(),
                typeWithAmount.getValue()
            ));
        }
        return stringBuilder.toString();
    }
}
