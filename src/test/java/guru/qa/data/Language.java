package guru.qa.data;

public enum Language {
    Deutsch("Deutsch", "Online courses"),
    Russian("Русский", "Онлайн-курсы");

    public final String name;
    private final String description;
    private Language(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

}
