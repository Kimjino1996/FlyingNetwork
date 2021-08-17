package FLYING_NE;
import COMMON.AttachInfo;
import COMMON.Pos;

import java.util.ArrayList;
import java.util.List;

public class FNE_controller {
    private static FNE_controller controller=null;
    private F_NE_Information FNE_state;
    private builder build_FNE;
    private Attached_UeSIM attached=null;
    private int [][] IpList={{1000,0},{1001,0},{1002,0},{1003,0},{1004,0}};
    //    Ip ip_list=Ip.getInstance();
    private FNE_controller() {
        //FNE_state = new F_NE_Inform_SKT();
    }
    public static FNE_controller getInstance(){ // 원래 정석 builder 였으면 여기서 directing 해줬어야함.
        if(controller==null){
            controller=new FNE_controller();
            controller.FNE_state = SKT();
            controller.attached= new Attached_UeSIM();
        }
        return controller;
    }
    public static F_NE_Information SKT(){
        controller.build_FNE=new builder();
        F_NE_Information temp;
        controller.build_FNE.setAntenaType(1);
        controller.build_FNE.setAntenaPower(3);
        controller.build_FNE.setAntenaType(2);
        controller.build_FNE.setAntenaPower(4);
        controller.build_FNE.setAntenaType(3);
        controller.build_FNE.setAntenaPower(4);
        controller. build_FNE.setBattery(120);
        controller.build_FNE.setFeatureList("feature1","on");
        controller. build_FNE.setFeatureList("feature2","on");
        controller. build_FNE.setLocation(7,7);
        controller.  build_FNE.setVendorName("SKT");
        temp=controller.build_FNE.result();
        return temp;

    }
    public F_NE_Information getFNEInformation(){
        return FNE_state;
    }
    public Pos getLocation(){
        return FNE_state.getLocation();
    }
    public List<Pos> getCoverage(){

        Mode FNE_MODE=FNE_state.getMode();
        String ModeName=FNE_state.getMode().getModeName();

        List<Pos>CoverRange=new ArrayList<Pos>();

        for (int i=0;i<FNE_state.getAntenaType().size();i++) {
            List<Pos> temp_list=FNE_MODE.getCover(FNE_state.getAntenaType().get(i), FNE_state.getAntenaPower().get(i));
            for (Pos target:temp_list){
                target.x=FNE_state.getLocation().x+target.x;
                target.y=FNE_state.getLocation().y+target.y;
                if (target.x<0||target.x>14){
                    temp_list.remove(target);
                }
                else if (target.y<0||target.y>14){
                    temp_list.remove(target);
                }
            }
            CoverRange.addAll(temp_list);
        }
        if(CoverRange==null){
            return null;
        }
        Pos temp= new Pos(FNE_state.getLocation().x,FNE_state.getLocation().y);
        CoverRange.add(temp);
        return CoverRange;
        //return null;
    }
//    public void show(){
//        System.out.println(FNE_state.getBattery());
//        ArrayList<Integer> AntenaType=FNE_state.getAntenaType();
//        ArrayList<Integer> AntenaPower=FNE_state.getAntenaPower();
////        ip_list.createIp();
////        for (int i=0;i<ip_list.getIp().size();i++){
////            System.out.print(ip_list.getIp().get(i));
////            System.out.println(ip_list.getAssigned().get(i));
////        }
////        ip_list.setAssgined(1,1);
//
//
////        for (int i =0 ; i<AntenaType.size();i++){
////            System.out.print(AntenaType.get(i)+"power"+AntenaPower.get(i));
////            List<Pos> temp =Coverage.range(AntenaType.get(i),AntenaPower.get(i));
////            for (int j=0;j<temp.size();j++){
////                System.out.println("j"+j);
////                System.out.println("Posx"+temp.get(j).x+"Posy"+temp.get(j).y);
////            }
////        }
//    }
    public Attached_UeSIM getAttached(){
        return attached;
    }
    public void moveLocation(int x,int y){
        Pos targetLocate=new Pos(x,y);
        controller.FNE_state.getMode().moving(targetLocate);
        controller.FNE_state.setLocation(FNE_state.getLocation().x+ targetLocate.x,FNE_state.getLocation().y+ targetLocate.y);
    }
    public void setAttached(int userId, List<String> ueCapability, String auth_key, int Ip){
        AttachInfo newAttach=new AttachInfo(userId,  ueCapability, auth_key);
        attached.setAttached(newAttach,Ip);
    }
    public void setDetach(int ueid){
        List<Integer> target_list = attached.getIp();
        for (int i = 0; i<attached.getAttached().size();i++){
            if(attached.getAttached().get(i).getUserId()==ueid)
            {
                for (int j =0; j<5;j++){
                    if (IpList[j][0]==target_list.get(i)){
                        IpList[j][1]=0;
                        attached.removeAttached(ueid);
                        break;
                    }
                }
            }
        }
    }
    public int assignIP(){
        for (int i =0 ; i<5;i++){
            if (IpList[i][1]==0) {
                IpList[i][1] = 1;
                return IpList[i][0];
            }
        }
        return -1;
    }
    public void show_attach(){
        System.out.println("**********************");
        for(AttachInfo show_target:attached.getAttached()){
            System.out.println("Attached user Id:" + show_target.getUserId());
            System.out.println("Attached user auth key:"+show_target.getAuth_key());
            System.out.println("Active Feature");
            for (int i =0 ; i<show_target.getUeCapability().size();i++){
                System.out.println(show_target.getUeCapability().get(i));
            }
            System.out.println("**********************");

        }
    }
    public void show_FNE_Inform(){
        System.out.println("************************");
        System.out.println("vendor :"+controller.FNE_state.getVendorName());
        System.out.println("Location x:"+controller.FNE_state.getLocation().x+" y :" +controller.FNE_state.getLocation().y);
        System.out.println("Battery:"+controller.FNE_state.getBattery());
        System.out.println("Mode:" + controller.FNE_state.getMode().getModeName());
        System.out.println("*************************");
    }
//    public static class Coverage{
//        public static List<Pos> range(int type,int power){
//            List<Pos> temp=new ArrayList<Pos>();
//            Pos element= new Pos(0,0);
//            Pos element2;
//            temp.add(element);
//
//            if (type==1){
//                for (int i=1;i<=power;i++){
//                    element= new Pos(i,-i);
//                    element2=new Pos(-i,i);
//                    temp.add(element);
//                    temp.add(element2);
//                }
//                return temp;
//
//            }
//            else if(type==2){
//                for (int i=1;i<=power;i++){
//                    element= new Pos(i,-i);
//                    element2=new Pos(-i,i);
//                    temp.add(element);
//                    temp.add(element2);
//                }
//                return temp;
//            }
//            else if(type==3){
//                for (int i=1;i<=power;i++){
//                    element= new Pos(i,-i);
//                    element2=new Pos(-i,i);
//                    temp.add(element);
//                    temp.add(element2);
//                }
//                return temp;
//            }
//            return null;
//        }
//    }

}
