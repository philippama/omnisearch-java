package pm.omnisearch;

import pm.omnisearch.transformers.TrailingPunctuationStripper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyser {
    private Tokeniser tokeniser;

    public Analyser(Tokeniser tokeniser) {

        this.tokeniser = tokeniser;
    }

    public Search buildSearch(String searchPhrase) {
        String[] tokens = tokeniser.tokenise(searchPhrase);
        TrailingPunctuationStripper trailingPunctuationStripper = new TrailingPunctuationStripper();
        List<String> preparedTokens = Stream.of(tokens)
                                            .map(String::toLowerCase)
                                            .map(trailingPunctuationStripper::transform)
                                            .collect(Collectors.toList());

        return Search.builder()
                     .withMake(preparedTokens.get(3))
                     .withModel(preparedTokens.get(4))
                     .withBodyType(preparedTokens.get(6))
                     .withFuel(preparedTokens.get(5))
                     .withColour(preparedTokens.get(0))
                     .build();
    }
}
