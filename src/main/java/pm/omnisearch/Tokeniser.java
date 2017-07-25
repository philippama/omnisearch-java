package pm.omnisearch;

class Tokeniser {
    String[] tokenise(String text) {
        return text.split("[\\s-_]+");
    }
}
