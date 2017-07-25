package pm.omnisearch;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TokeniserTest {

    private Tokeniser tokeniser;

    @Before
    public void setUp() {

        tokeniser = new Tokeniser();
    }

    @Test
    public void splitsOnWhiteSpace() {

        String[] tokens = tokeniser.tokenise("one two \t3");

        assertThat(tokens).containsExactly("one", "two", "3");
    }

    @Test
    public void splitsOnHyphen() {

        String[] tokens = tokeniser.tokenise("one- two--3");

        assertThat(tokens).containsExactly("one", "two", "3");
    }

    @Test
    public void splitsOnUnderscore() {

        String[] tokens = tokeniser.tokenise("one_ two__3");

        assertThat(tokens).containsExactly("one", "two", "3");
    }

}