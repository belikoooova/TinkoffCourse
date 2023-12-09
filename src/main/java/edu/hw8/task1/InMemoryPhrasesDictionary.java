package edu.hw8.task1;

import java.util.Map;

@SuppressWarnings("LineLength")
public class InMemoryPhrasesDictionary implements PhrasesDictionary {
    private static final Map<String, String> DICTIONARY = Map.of(
        "личности",
        "Не переходи на личности там, где их нет.\n",
        "оскорбления",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.\n",
        "глупый",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.\n",
        "интеллект",
        "Чем ниже интеллект, тем громче оскорбления.\n"
    );

    @Override
    public String getReply(String request) {
        if (DICTIONARY.containsKey(request)) {
            return DICTIONARY.get(request);
        }
        return "На такую глупость я и отвечать не буду!\n";
    }
}
