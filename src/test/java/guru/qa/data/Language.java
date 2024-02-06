package guru.qa.data;

public enum Language {
    Deutsch("Online courses"),
    English("Online courses");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
