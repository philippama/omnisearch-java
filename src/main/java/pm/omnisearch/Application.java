package pm.omnisearch;

import pm.omnisearch.transformers.PhoneticNormaliser;
import pm.omnisearch.transformers.ToLowerCase;
import pm.omnisearch.transformers.TrailingPunctuationStripper;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String... args) throws IOException, InterruptedException {
        Analyser analyser = createAnalyser();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nEnter a search phrase >>> ");
            String searchPhrase = scanner.nextLine();
            Search search = analyser.buildSearch(searchPhrase);
            System.out.println("Searching for " + search);
        }
    }

    private static Analyser createAnalyser() throws IOException {
        PhraseNormaliser phraseNormaliser = new PhraseNormaliser(new Tokeniser(), new ToLowerCase(), new TrailingPunctuationStripper(), new PhoneticNormaliser());
        NormalisedDictionaryFactory normalisedDictionaryFactory = new NormalisedDictionaryFactory(phraseNormaliser);
        return new Analyser(normalisedDictionaryFactory, phraseNormaliser);
    }
}
