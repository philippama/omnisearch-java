package pm.omnisearch;

import org.junit.Before;
import org.junit.Test;
import pm.omnisearch.transformers.PhoneticNormaliser;
import pm.omnisearch.transformers.ToLowerCase;
import pm.omnisearch.transformers.TrailingPunctuationStripper;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

public class AnalyserIntegrationTest {

    private Analyser analyser;

    @Before
    public void setUp() throws IOException {
        PhraseNormaliser phraseNormaliser = new PhraseNormaliser(new Tokeniser(), new ToLowerCase(), new TrailingPunctuationStripper(), new PhoneticNormaliser());
        NormalisedDictionaryFactory normalisedDictionaryFactory = new NormalisedDictionaryFactory(phraseNormaliser);
        analyser = new Analyser(normalisedDictionaryFactory, phraseNormaliser);
    }

    @Test
    public void interimTest() {
        String searchPhrase = "red 5-door Ford Ka+ desel cupe";

        Search actualSearch = analyser.buildSearch(searchPhrase);

        Search expectedSearch = Search.builder()
                                      .withMake("Ford")
                                      .withModel("Ka")
                                      .withBodyType("Coupe")
                                      .withFuel("Diesel")
                                      .withColour("Red")
                                      .build();
        assertThat(actualSearch).isEqualTo(expectedSearch);
    }

    @Test
    public void normalisesSearchRequest() {
        fail("TODO");
        /*
        build dictionaries of key = normalised value, value = make, model etc.
        with search phrase
            split into tokens
            to lower case
            remove trailing spaces
            ignore empty strings
            normalise
            create a collection of normalised matching tokens / phrases plus unmatched phrases using dictionary keys
        matching normalised tokens -> actual make / model etc. using dictionaries
        */

        String searchPhrase = "5-door alpha romeo desel coop";

        Search actualSearch = analyser.buildSearch(searchPhrase);

        Search expectedSearch = Search.builder()
                                      .withMake("Alfa Romeo")
//                .withModel("")
                                      .withBodyType("Coupe")
                                      .withFuel("Diesel")
//                .withColour("")
                                      .build();
        assertThat(actualSearch).isEqualTo(expectedSearch);
    }
}
