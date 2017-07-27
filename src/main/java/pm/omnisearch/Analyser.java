package pm.omnisearch;

import java.util.List;

public class Analyser {

    private PhraseNormaliser phraseNormaliser;

    public Analyser(PhraseNormaliser phraseNormaliser) {

        this.phraseNormaliser = phraseNormaliser;
    }

    public Search buildSearch(String searchPhrase) {
        List<String> normalisedTokens = phraseNormaliser.getNormalisedTokens(searchPhrase);

        return Search.builder()
                     .withMake(normalisedTokens.get(3))
                     .withModel(normalisedTokens.get(4))
                     .withBodyType(normalisedTokens.get(6))
                     .withFuel(normalisedTokens.get(5))
                     .withColour(normalisedTokens.get(0))
                     .build();
    }
}
