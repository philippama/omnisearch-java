package pm.omnisearch;

import java.io.IOException;

class NormalisedDictionaryFactory {
    private PhraseNormaliser phraseNormaliser;

    NormalisedDictionaryFactory(PhraseNormaliser phraseNormaliser) {

        this.phraseNormaliser = phraseNormaliser;
    }

    NormalisedDictionary createFromFile(String filePath) throws IOException {
        return new NormalisedDictionary(filePath, phraseNormaliser);
    }
}
