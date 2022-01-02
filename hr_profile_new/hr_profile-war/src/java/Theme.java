public class Theme {

    private int id;

    private String displayName;

    private String name;

    private String menuStyle;

    public Theme() {}

    public Theme(int id, String displayName, String name,String menuStyle) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
        this.menuStyle=menuStyle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuStyle() {
        return menuStyle;
    }

    public void setMenuStyle(String menuStyle) {
        this.menuStyle = menuStyle;
    }

    @Override
    public String toString() {
        return name;
    }
}
