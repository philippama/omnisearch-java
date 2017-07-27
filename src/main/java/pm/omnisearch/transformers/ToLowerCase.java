package pm.omnisearch.transformers;

public class ToLowerCase implements Transformer {

    @Override
    public String apply(String string) {
        return string.toLowerCase();
    }
}
