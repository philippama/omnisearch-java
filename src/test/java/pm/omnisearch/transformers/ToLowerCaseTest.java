package pm.omnisearch.transformers;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ToLowerCaseTest {

    @Test
    public void lowersCase() {
        ToLowerCase toLowerCase = new ToLowerCase();
        assertThat(toLowerCase.apply("aBcD")).isEqualTo("abcd");
    }

}