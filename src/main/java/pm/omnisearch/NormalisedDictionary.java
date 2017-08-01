package pm.omnisearch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class NormalisedDictionary {

    private final Map<String, String> dictionary;

    NormalisedDictionary(String filePath, PhraseNormaliser phraseNormaliser) throws IOException {
        dictionary = Files.lines(Paths.get(filePath))
                          .collect(Collectors.toMap(phraseNormaliser::getNormalisedPhrase, Function.identity()));
    }

    String get(String normalisedPhrase) {
        return dictionary.get(normalisedPhrase);
    }

    boolean contains(String key) {
        return dictionary.containsKey(key);
    }
}
