package app.stands;

public class Stand {
    private final long id;
    private final String title;
    private final String description;
    private final String macAddress;
    private final String iconUrl;

    public Stand(final long theId, final String theTitle, final String theDescription, final String theMacAddress,
                 final String theIconUrl) {
        this.id = theId;
        this.title = theTitle;
        this.description = theDescription;
        this.macAddress = theMacAddress;
        this.iconUrl = theIconUrl;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
