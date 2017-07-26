package pm.omnisearch.transformers;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrailingPunctuationStripperTest {

    private TrailingPunctuationStripper stripper;

    @Before
    public void setUp() {
        stripper = new TrailingPunctuationStripper();
    }

    @Test
    public void stripsTrailingPunctuationCharacterAsRequired() {
        assertThat(stripper.transform("Ka+")).isEqualTo("Ka");
        assertThat(stripper.transform("Ka!")).isEqualTo("Ka");
        assertThat(stripper.transform("Ka?")).isEqualTo("Ka");
    }

    @Test
    public void doesNotStripTrailingPunctuationCharacterWhenNotRequired() {
        assertThat(stripper.transform("Ka=")).isEqualTo("Ka=");
    }

}