package pm.omnisearch;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnalyserIntegrationTest {

    @Test
    public void interimTest() {
        Analyser analyser = new Analyser(new Tokeniser());
        String searchPhrase = "red 5-door ford fieste desel coop";

        Search actualSearch = analyser.buildSearch(searchPhrase);

        Search expectedSearch = Search.builder()
                                      .withMake("ford")
                                      .withModel("fieste")
                                      .withBodyType("coop")
                                      .withFuel("desel")
                                      .withColour("red")
                                      .build();
        assertThat(actualSearch).isEqualTo(expectedSearch);
    }

    @Test
    public void normalisesSearchRequest() {
        Analyser analyser = new Analyser(new Tokeniser());
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
