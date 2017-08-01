package pm.omnisearch;

import org.junit.Before;
import org.junit.Test;
import pm.omnisearch.transformers.PhoneticNormaliser;
import pm.omnisearch.transformers.ToLowerCase;
import pm.omnisearch.transformers.TrailingPunctuationStripper;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class AnalyserIntegrationTest {

    private Analyser analyser;

    @Before
    public void setUp() throws IOException {
        PhraseNormaliser phraseNormaliser = new PhraseNormaliser(new Tokeniser(), new ToLowerCase(), new TrailingPunctuationStripper(), new PhoneticNormaliser());
        NormalisedDictionaryFactory normalisedDictionaryFactory = new NormalisedDictionaryFactory(phraseNormaliser);
        analyser = new Analyser(normalisedDictionaryFactory, phraseNormaliser);
    }

    @Test
    public void normalisesSearchRequest() {
        String searchPhrase = "red 3-door alfa romi 4c limited edition petrol cupe";

        Search actualSearch = analyser.buildSearch(searchPhrase);

        Search expectedSearch = Search.builder()
                                      .withMake("Alfa Romeo")
                                      .withModel("4C")
                                      .withBodyType("Coupe")
                                      .withFuel("Petrol")
                                      .withColour("Red")
                                      .withOther("3 door")
                                      .build();
        assertThat(actualSearch).isEqualTo(expectedSearch);
    }

}
