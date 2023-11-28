package edu.hw8.task1;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("LineLength")
public class InMemoryPhrasesDictionary implements PhrasesDictionary {
    private static Map<String, String> dictionary;

    public InMemoryPhrasesDictionary() {
        fillDictionary();
    }

    @Override
    public String getReply(String request) {
        if (dictionary.containsKey(request)) {
            return dictionary.get(request);
        }
        return "На такую глупость я и отвечать не буду!\n";
    }

    private static void fillDictionary() {
        dictionary = new HashMap<>();
        dictionary.put("личности", "Не переходи на личности там, где их нет.\n");
        dictionary.put("оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.\n");
        dictionary.put("глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.\n");
        dictionary.put("интеллект", "Чем ниже интеллект, тем громче оскорбления.\n");
    }
}
