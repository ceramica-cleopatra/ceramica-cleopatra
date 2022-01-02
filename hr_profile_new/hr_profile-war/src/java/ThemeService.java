
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="themeService", eager = true)
@SessionScoped
public class ThemeService{

    private List<Theme> themes;

    @PostConstruct
    public void init() {
        themes = new ArrayList<Theme>();
        themes.add(new Theme(1, "Afternoon", "afternoon",""));
        themes.add(new Theme(2, "Afterwork", "afterwork",""));
        themes.add(new Theme(3, "Base", "base",""));
        themes.add(new Theme(4, "Black-Tie", "black-tie","black-tie"));
        themes.add(new Theme(5, "Blitzer", "blitzer","blitzer"));
        themes.add(new Theme(6, "Cupertino", "cupertino",""));
        themes.add(new Theme(7, "Dark-Hive", "dark-hive","dark-hive"));
        themes.add(new Theme(8, "Dot-Luv", "dot-luv","dot-luv"));
        themes.add(new Theme(9, "Eggplant", "eggplant","eggplant"));
        themes.add(new Theme(10, "Excite-Bike", "excite-bike","excite-bike"));
        themes.add(new Theme(11, "Flick", "flick","flick"));
        themes.add(new Theme(12, "Hot-Sneaks", "hot-sneaks","hot-sneaks"));
        themes.add(new Theme(13, "Humanity", "humanity","humanity"));
        themes.add(new Theme(14, "Le-Frog", "le-frog","le-frog"));
        themes.add(new Theme(15, "Mint-Choc", "mint-choc","mint-choc"));
        themes.add(new Theme(16, "Overcast", "overcast",""));
        themes.add(new Theme(17, "Pepper-Grinder", "pepper-grinder","pepper-grinder"));
        themes.add(new Theme(18, "Redmond", "redmond",""));
        themes.add(new Theme(19, "Smoothness", "smoothness",""));
        themes.add(new Theme(20, "South-Street", "south-street","south-street"));
        themes.add(new Theme(21, "Start", "start","start"));
        themes.add(new Theme(22, "Sunny", "sunny","sunny"));
        themes.add(new Theme(23, "Swanky-Purse", "swanky-purse","swanky-purse"));
        themes.add(new Theme(24, "Trontastic", "trontastic","trontastic"));
        themes.add(new Theme(25, "UI-Darkness", "ui-darkness","ui-darkness"));
        themes.add(new Theme(26, "UI-Lightness", "ui-lightness","ui-lightness"));
        themes.add(new Theme(27, "Vader", "vader","vader"));
        themes.add(new Theme(28, "Afterdark", "afterdark","afterdark"));
        themes.add(new Theme(29, "Blitzer", "blitzer","blitzer"));
        themes.add(new Theme(30, "South-Street", "south-street","south-street"));
        themes.add(new Theme(31, "Flick", "flick","flick"));
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    
}
