package pm.omnisearch;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Analyser {

    private final PhraseNormaliser phraseNormaliser;

    private final NormalisedDictionary makes;
    private final NormalisedDictionary models;
    private final NormalisedDictionary bodies;
    private final NormalisedDictionary fuels;
    private final NormalisedDictionary colours;

    public Analyser(NormalisedDictionaryFactory normalisedDictionaryFactory, PhraseNormaliser phraseNormaliser) throws IOException {

        this.phraseNormaliser = phraseNormaliser;
//        this.makes = createFromFile(normalisedDictionaryFactory, "makes.txt"); //TODO: Be able to handle duplicates in makes.txt. See makes_duplicates.txt for suggestions.
        this.makes = createFromFile(normalisedDictionaryFactory, "makes_no_duplicates.txt");
//        this.models = createFromFile(normalisedDictionaryFactory, "models.txt"); //TODO: Be able to handle duplicates in models.txt. See models_duplicates.txt for suggestions.
        this.models = createFromFile(normalisedDictionaryFactory, "models_no_duplicates.txt");
        this.bodies = createFromFile(normalisedDictionaryFactory, "bodies.txt");
        this.fuels = createFromFile(normalisedDictionaryFactory, "fuels.txt");
        this.colours = createFromFile(normalisedDictionaryFactory, "colours.txt");
    }

    private NormalisedDictionary createFromFile(NormalisedDictionaryFactory dictionaryFactory, String fileName) throws IOException {
        String filePath = this.getClass().getResource(fileName).getPath();
        return dictionaryFactory.createFromFile(filePath);
    }

    public Search buildSearch(String searchPhrase) {
        List<String> normalisedTokens = phraseNormaliser.getNormalisedTokens(searchPhrase);

        Search.Builder builder = Search.builder();
        //TODO: have a map of dictionary:function? Or a list (ordered) of dictionary|function objects?
        lookUpTokens(normalisedTokens, this.makes, builder::withMake); //TODO
        lookUpTokens(normalisedTokens, this.models, builder::withModel);
        lookUpTokens(normalisedTokens, this.bodies, builder::withBodyType);
        lookUpTokens(normalisedTokens, this.fuels, builder::withFuel);
        lookUpTokens(normalisedTokens, this.colours, builder::withColour);

        return builder.build();
    }

    private void lookUpTokens(List<String> normalisedTokens, NormalisedDictionary dictionary, Consumer<String> consumer) {
        normalisedTokens.stream()
                        .map(dictionary::get)
                        .filter(Objects::nonNull)
                        .findFirst()
                        .ifPresent(consumer);
    }
}
