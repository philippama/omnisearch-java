package pm.omnisearch.transformers;

public class TrailingPunctuationStripper implements Transformer {

    public String apply(String token) {
        if ("+!?".indexOf(token.charAt(token.length() - 1)) >= 0) {
            return token.substring(0, token.length() - 1);
        }
        return token;
    }
}
