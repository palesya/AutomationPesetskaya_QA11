package Lecture_5;

import java.util.List;

public class FormData {
    public String rWidth;
    public String rHeight;
    public String lWidth;
    public String lHeight;
    public String pack;
    public String pr;
    public String direction;
    public String bi;
    public String wDist;

    public FormData(String rWidth, String rHeight, String lWidth, String lHeight, String pack, String pr,String direction, String bi, String wDist) {
        this.rWidth = rWidth;
        this.rHeight = rHeight;
        this.lWidth = lWidth;
        this.lHeight = lHeight;
        this.pack = pack;
        this.direction = direction;
        this.pr = pr;
        this.bi = bi;
        this.wDist = wDist;
    }
}
