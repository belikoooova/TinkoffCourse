package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
            .newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .GET()
            .build();
        String response = getResponse(request);
        return parseToLongArray(response);
    }

    private static long[] parseToLongArray(String string) {
        return Arrays.stream(string.substring(1, string.length() - 1).split(","))
            .mapToLong(Long::parseLong)
            .toArray();
    }

    private static String getResponse(HttpRequest request) throws IOException, InterruptedException {
        return HttpClient
            .newBuilder()
            .build()
            .send(request, HttpResponse.BodyHandlers.ofString())
            .body();
    }

    public static String newsTitle(long id) throws IOException, InterruptedException {
        String uri = String.format("https://hacker-news.firebaseio.com/v0/item/%d.json", id);
        HttpRequest request = HttpRequest
            .newBuilder()
            .uri(URI.create(uri))
            .GET()
            .build();
        String response = getResponse(request);
        Matcher title = Pattern.compile("\"title\":\"([^\"]*)\"").matcher(response);
        if (title.find()) {
            return title.group(1);
        } else {
            return "Title not found";
        }
    }
}
