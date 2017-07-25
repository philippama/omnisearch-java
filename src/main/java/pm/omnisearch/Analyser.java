package pm.omnisearch;

public class Analyser {
    private Tokeniser tokeniser;

    public Analyser(Tokeniser tokeniser) {

        this.tokeniser = tokeniser;
    }

    public Search buildSearch(String searchPhrase) {
        String[] tokens = tokeniser.tokenise(searchPhrase);
        return Search.builder()
                     .withMake(tokens[3])
                     .withModel(tokens[4])
                     .withBodyType(tokens[6])
                     .withFuel(tokens[5])
                     .withColour(tokens[0])
                     .build();
    }
}
