package PageObject.herocuapp;

public enum HomePageLinksEnum {

    AB_TESTING("A/B Testing"),
    CHECKBOXES("Checkboxes"),
    CONTEXT_MENU("Context Menu"),
    DYNAMIC_CONTROLS("Dynamic Controls"),
    FILE_UPLOAD("File Upload"),
    FILE_DOWNLOAD("File Download"),
    FRAMES("Frames"),
    JS_ALERT("JavaScript Alerts"),
    DRAG_AND_DROP("Drag and Drop");

    String link;

    HomePageLinksEnum(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
