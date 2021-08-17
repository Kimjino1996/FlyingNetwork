package FLYING_NE;

import java.util.ArrayList;
import java.util.List;
import COMMON.Pos;

public interface Mode {
    public Pos moving(Pos x);
    public List<Pos> getCover(int type,int power);
    public String getModeName();
}
