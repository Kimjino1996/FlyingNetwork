package FLYING_NE;

import COMMON.Pos;

import java.util.ArrayList;
import java.util.List;

public class NormalMode implements Mode{
    @Override
    public Pos moving(Pos x) {
        return x;
    }

    @Override
    public List<Pos> getCover(int type, int power) {
        List<Pos> temp=new ArrayList<Pos>();

        Pos element;
        Pos element2;


        if (type==1){
            for (int i=1;i<=power;i++){
                element= new Pos(i,-i);
                element2=new Pos(-i,i);
                temp.add(element);
                temp.add(element2);
            }
            return temp;

        }
        else if(type==2){
            for (int i=1;i<=power;i++){
                element= new Pos(i,i);
                element2=new Pos(-i,-i);
                temp.add(element);
                temp.add(element2);
            }
            return temp;
        }
        else if(type==3){
            for (int i=1;i<=power;i++){
                element= new Pos(-i,0);
                element2=new Pos(i,0);
                temp.add(element);
                temp.add(element2);
            }
            return temp;
        }
        else if(type==4){
            for (int i=1;i<=power;i++){
                element= new Pos(0,-i);
                element2=new Pos(0,i);
                temp.add(element);
                temp.add(element2);
            }
            return temp;
        }
        return null;
    }

    @Override
    public String getModeName() {
        return "Normal Mode";
    }
}
