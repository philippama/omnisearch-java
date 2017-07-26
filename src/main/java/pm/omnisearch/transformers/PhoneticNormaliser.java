package pm.omnisearch.transformers;

public class PhoneticNormaliser implements Transformer {
    private String[] letterGroups = {
            "aeiouhwy",
            "bfpv",
            "cgjkqsxz",
            "dt",
            "l",
            "mn",
            "r"
    };

    private String allLetters = String.join("", letterGroups);

    @Override
    public String transform(String token) {
        String mappedToken = mapCharactersAccordingToLetterGroup(token);
        return compressAdjacentDuplicatesIfInLetterGroup(mappedToken);
    }

    private String mapCharactersAccordingToLetterGroup(String token) {
        StringBuilder builder = new StringBuilder(token.substring(0, 1));

        for (int i = 1 ; i < token.length() ; i++) {
            builder.append(mapCharacterAccordingToLetterGroup(token.charAt(i)));
        }
        return builder.toString();
    }

    private char mapCharacterAccordingToLetterGroup(char character) {
        for (String letterGroup : letterGroups) {
            if (letterGroup.indexOf(character) >= 0) {
                return letterGroup.charAt(0);
            }
        }
        return character;
    }

    private String compressAdjacentDuplicatesIfInLetterGroup(String normalisedToken) {
        StringBuilder builder = new StringBuilder(normalisedToken.substring(0, 1));

        for (int i = 1 ; i < normalisedToken.length() ; i++) {
            char character = normalisedToken.charAt(i);
            if (!isInALetterGroup(character) || (character != normalisedToken.charAt(i - 1))) {
                builder.append(character);
            }
        }
        return builder.toString();
    }

    private boolean isInALetterGroup(char character) {
        return allLetters.indexOf(character) >= 0;
    }

}
