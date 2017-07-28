package pm.omnisearch;

import org.junit.Test;
import pm.omnisearch.transformers.PhoneticNormaliser;
import pm.omnisearch.transformers.ToLowerCase;
import pm.omnisearch.transformers.TrailingPunctuationStripper;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalisedDictionaryFactoryTest {

    @Test
    public void getsDictionary() throws IOException {
        PhraseNormaliser phraseNormaliser = new PhraseNormaliser(new Tokeniser(), new ToLowerCase(), new TrailingPunctuationStripper(), new PhoneticNormaliser());
        NormalisedDictionaryFactory dictionaryFactory = new NormalisedDictionaryFactory(phraseNormaliser);
        String filePath = this.getClass().getResource("things.txt").getPath();

        NormalisedDictionary dictionary = dictionaryFactory.createFromFile(filePath);

        assertThat(dictionary).isNotNull();
        assertThat(dictionary.get("sabarmam")).isEqualTo("Superman");
    }

}