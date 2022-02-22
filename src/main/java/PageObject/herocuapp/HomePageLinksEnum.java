package PageObject.herocuapp;

public enum HomePageLinksEnum {

    AB_TESTING("A/B Testing"),
    CHECKBOXES("Checkboxes");

    String link;

    HomePageLinksEnum(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
