package FLYING_NE;

import COMMON.AttachInfo;
import java.util.List;
import java.util.ArrayList;

public class Attached_UeSIM {
    private List<AttachInfo> attached;
    private List<Integer> IP;

    public Attached_UeSIM(){
        attached=new ArrayList<AttachInfo>();
        IP=new ArrayList<Integer>();
    }
    public void setAttached(AttachInfo UeSIM,int Ip){
        attached.add(UeSIM);
        IP.add(Ip);
    }
    public List<AttachInfo> getAttached(){
        return attached;
    }
    public void removeAttached(int ueid){
        for (int i =0; i<attached.size();i++){
            if (attached.get(i).getUserId()==ueid){
                IP.remove(i);
                attached.remove(i);
                break;
            }
        }
    }
    public List<Integer>getIp(){
        return IP;
    }
}
