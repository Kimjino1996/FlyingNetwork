package FLYING_NE;

import COMMON.Pos;

import java.util.ArrayList;
import java.util.List;

public class NoPowerMode implements Mode{
    @Override
    public Pos moving(Pos x) {
        x.x=0;
        x.y=0;
        return x;
    }
    public List<Pos> getCover(int type, int power){

        return null;
    }
    public String getModeName(){
        return "NoPowerMode";
    }

}
