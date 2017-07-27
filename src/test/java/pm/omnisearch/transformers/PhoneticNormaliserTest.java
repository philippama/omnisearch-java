package pm.omnisearch.transformers;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneticNormaliserTest {

    private PhoneticNormaliser normaliser;

    @Before
    public void setUp() {
        normaliser = new PhoneticNormaliser();
    }

    @Test
    public void keepsFirstCharacter() {
        assertThat(normaliser.apply("pad")).isEqualTo("pad");
    }

    @Test
    public void mapsCharactersAccordingToLetterGroups() {
        assertCharactersMapTo("aeiouhwy", "a");
        assertCharactersMapTo("bfpv", "b");
        assertCharactersMapTo("cgjkqsxz", "c");
        assertCharactersMapTo("dt", "d");
        assertCharactersMapTo("l", "l");
        assertCharactersMapTo("mn", "m");
        assertCharactersMapTo("r", "r");
    }

    private void assertCharactersMapTo(String characters, String expectedCharacter) {
        assertThat(normaliser.apply("p" + characters)).isEqualTo("p" + expectedCharacter);
    }

    @Test
    public void keepsCharactersNotInALetterGroup() {
        String token = "p0123%^&+";
        assertThat(normaliser.apply(token)).isEqualTo(token);
    }

    @Test
    public void compressesDuplicatesWhenCharactersWereInLetterGroup() {
        assertThat(normaliser.apply("aeioubfpv000")).isEqualTo("ab000");
    }
}