package pm.omnisearch;

import org.junit.Test;
import pm.omnisearch.transformers.PhoneticNormaliser;
import pm.omnisearch.transformers.ToLowerCase;
import pm.omnisearch.transformers.TrailingPunctuationStripper;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalisedDictionaryTest {

    @Test
    public void buildsDictionaryFromFile() throws IOException {
        String filePath = this.getClass().getResource("things.txt").getPath();
        PhraseNormaliser phraseNormaliser = new PhraseNormaliser(new Tokeniser(), new ToLowerCase(), new TrailingPunctuationStripper(), new PhoneticNormaliser());
        NormalisedDictionary dictionary = new NormalisedDictionary(filePath, phraseNormaliser);

        assertThat(dictionary.get("wamdar wamam")).isEqualTo("Wonder Woman");
        assertThat(dictionary.get("sabarmam")).isEqualTo("Superman");
        assertThat(dictionary.get("elacdacarl")).isEqualTo("Elastigirl");
        assertThat(dictionary.get("something else")).isNull();
    }

}