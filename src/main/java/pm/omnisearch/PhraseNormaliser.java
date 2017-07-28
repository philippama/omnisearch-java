package pm.omnisearch;

import pm.omnisearch.transformers.PhoneticNormaliser;
import pm.omnisearch.transformers.ToLowerCase;
import pm.omnisearch.transformers.TrailingPunctuationStripper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PhraseNormaliser {
    private final Tokeniser tokeniser;
    private final ToLowerCase toLowerCase;
    private final TrailingPunctuationStripper trailingPunctuationStripper;
    private final PhoneticNormaliser normaliser;

    PhraseNormaliser(Tokeniser tokeniser, ToLowerCase toLowerCase, TrailingPunctuationStripper trailingPunctuationStripper, PhoneticNormaliser normaliser) {

        this.tokeniser = tokeniser;
        this.toLowerCase = toLowerCase;
        this.trailingPunctuationStripper = trailingPunctuationStripper;
        this.normaliser = normaliser;
    }

    List<String> getNormalisedTokens(String phrase) {
        return getNormalisedTokenStream(phrase).collect(Collectors.toList());
    }

    String getNormalisedPhrase(String phrase) {
        return getNormalisedTokenStream(phrase).collect(Collectors.joining(" "));
    }

    private Stream<String> getNormalisedTokenStream(String phrase) {
        String[] tokens = tokeniser.tokenise(phrase);
        return Stream.of(tokens)
                     .map(toLowerCase)
                     .map(trailingPunctuationStripper)
                     .map(normaliser);
    }

}
