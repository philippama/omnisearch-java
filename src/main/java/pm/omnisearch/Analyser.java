package pm.omnisearch;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public class Analyser {

    private final PhraseNormaliser phraseNormaliser;
    private final Map<String, LookUps> searchTerms;

    public Analyser(NormalisedDictionaryFactory dictionaryFactory, PhraseNormaliser phraseNormaliser) throws IOException {

        this.phraseNormaliser = phraseNormaliser;

        //TODO: Be able to handle duplicates in makes.txt. See makes_duplicates.txt for suggestions.
        //TODO: Be able to handle duplicates in models.txt. See models_duplicates.txt for suggestions.
        searchTerms = new HashMap<>();
        searchTerms.put("makes", getSearchTermLookups(dictionaryFactory, "makes_no_duplicates.txt", Search.Builder::withMake));
        searchTerms.put("models", getSearchTermLookups(dictionaryFactory, "models_no_duplicates.txt", Search.Builder::withModel));
        searchTerms.put("bodies", getSearchTermLookups(dictionaryFactory, "bodies.txt", Search.Builder::withBodyType));
        searchTerms.put("fuels", getSearchTermLookups(dictionaryFactory, "fuels.txt", Search.Builder::withFuel));
        searchTerms.put("colours", getSearchTermLookups(dictionaryFactory, "colours.txt", Search.Builder::withColour));
        searchTerms.put("specialPatterns", getSearchTermLookups(dictionaryFactory, "special_patterns.txt", Search.Builder::withOther));
    }

    private LookUps getSearchTermLookups(NormalisedDictionaryFactory dictionaryFactory,
                                         String fileName,
                                         BiConsumer<Search.Builder, String> operation) throws IOException {
        return new LookUps(createFromFile(dictionaryFactory, fileName), operation);
    }

    private NormalisedDictionary createFromFile(NormalisedDictionaryFactory dictionaryFactory, String fileName) throws IOException {
        String filePath = this.getClass().getResource(fileName).getPath();
        return dictionaryFactory.createFromFile(filePath);
    }

    public Search buildSearch(String searchPhrase) {

        List<String> normalisedTokens = phraseNormaliser.getNormalisedTokens(searchPhrase);

        Search.Builder builder = Search.builder();

        int first = 0;
        int last = normalisedTokens.size() - 1;
        for (int i = first; i <= last; i++) {
            for (int j = last; j >= i; j--) {
                List<String> subList = normalisedTokens.subList(i, j + 1);
                String normalisedPhrase = String.join(" ", subList);
                Optional<String> matchedKey = searchTerms.keySet().stream()
                                                     .filter(key -> searchTerms.get(key).getDictionary().contains(normalisedPhrase))
                                                     .findFirst();

                if (matchedKey.isPresent()) {
                    LookUps matchedLookUps = searchTerms.get(matchedKey.get());
                    String actualPhrase = matchedLookUps.getDictionary().get(normalisedPhrase);
                    matchedLookUps.getOperation().accept(builder, actualPhrase);
                    i = j;
                    break;
                }
            }
        }

        return builder.build();
    }

    private static class LookUps {
        private final NormalisedDictionary dictionary;
        private final BiConsumer<Search.Builder, String> operation;

        private LookUps(NormalisedDictionary normalisedDictionary, BiConsumer<Search.Builder, String> operation) {
            this.dictionary = normalisedDictionary;
            this.operation = operation;
        }

        NormalisedDictionary getDictionary() {
            return dictionary;
        }

        BiConsumer<Search.Builder, String> getOperation() {
            return operation;
        }
    }
}
