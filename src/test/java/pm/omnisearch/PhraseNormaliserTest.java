package pm.omnisearch;

import org.junit.Before;
import org.junit.Test;
import pm.omnisearch.transformers.PhoneticNormaliser;
import pm.omnisearch.transformers.ToLowerCase;
import pm.omnisearch.transformers.TrailingPunctuationStripper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PhraseNormaliserTest {

    private PhraseNormaliser phraseNormaliser;

    @Before
    public void setUp() {
        phraseNormaliser = new PhraseNormaliser(new Tokeniser(), new ToLowerCase(), new TrailingPunctuationStripper(), new PhoneticNormaliser());
    }

    @Test
    public void returnsNormalisedTokensForPhrase() {
        String phrase = "red 5-door Ford Ka+ desel coop";

        List<String> normalisedTokens = phraseNormaliser.getNormalisedTokens(phrase);

        assertThat(normalisedTokens).containsExactly("rad", "5", "dar", "fard", "ka", "dacal", "cab");
    }

    @Test
    public void returnsNormalisedPhrase() {
        String phrase = "red 5-door Ford Ka+ desel coop";

        String normalisedPhrase = phraseNormaliser.getNormalisedPhrase(phrase);

        assertThat(normalisedPhrase).isEqualTo("rad 5 dar fard ka dacal cab");
    }
}