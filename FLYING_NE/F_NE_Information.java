package FLYING_NE;
import COMMON.Pos;

import java.util.ArrayList;
import java.util.List;
public class F_NE_Information {

    private ArrayList <Integer> AntenaType;
    private ArrayList <Integer> AntenaPower;
    private Pos location;
    private String VendorName="";
    private int Battery ;
    //private String Mode;
    private Mode mode;
    private int Alarm;

    private SavingEnergy saveEnergy=new SavingEnergy();
    private NoPowerMode noPower= new NoPowerMode();
    private NormalMode normal=new NormalMode();

    private ArrayList<String> featureList;

    public F_NE_Information(ArrayList<Integer> AntenaType,ArrayList<Integer> AntenaPower,Pos location,String Vendorname,int Battery,Mode mode,int Alarm,ArrayList<String> featurelist){
        this.AntenaPower=AntenaPower;
        this.AntenaType=AntenaType;
        this.location=location;
        this.VendorName=Vendorname;
        this.Battery=Battery;
        this.mode=mode;
        this.Alarm=Alarm;
        this.featureList=featurelist;
    }
    public void setAntenaType(int prev_type,int after_type){
        for (int i=0; i<AntenaType.size();i++){
            if (AntenaType.get(i)==prev_type){
                AntenaPower.set(i,after_type);
            }
        }
    }
    public void setAntenaPower(int type,int power){
        for (int i=0; i<AntenaType.size();i++){
            if (AntenaType.get(i)==type){
                AntenaPower.set(i,power);
            }
        }
    }
    public void setLocation(int x, int y){
        this.location=new Pos(x,y);
    }
    public void setLocation(Pos x){
        this.location=x;
    }
    public void setBattery(int battery){
        this.Battery=battery;
        if (this.Battery>50){
            setMode(normal);
            //  Mode kkk= new SavingEnergy();
            // kkk.moving(kkk,AntenaPower);
//           여기서 moving(this) 로 객체 종류 판단해서 다시 원래대로 build 해줘야 할 듯 ?
            // 아니면 그냥 setMode를 각 통신사 별로 따로 overide 해도 됨.

        }
        else if(this.Battery<=50 && this.Battery>=20){
            setMode(saveEnergy);
        }
        else if(this.Battery<20){
            setMode(noPower);
        }

    }
    private void setMode(Mode mode){
        this.mode=mode;
        setAlarm();
    }
    private void setAlarm(){
        if(mode.getModeName().equals("NoPowerMode")){
            this.Alarm=1;
        }
        else{
            this.Alarm=0;
        }
    }
    public void setFeatureList(String feature, String onOff){
        if(this.featureList==null){
            this.featureList=new ArrayList<String>();
        }
        int f_index=-1;
        if (onOff.equals("off")){
            for (int i=0; i<this.featureList.size();i++){
                if (this.featureList.get(i).equals(feature)) {
                    f_index=i;
                }
            }
            if (f_index>=0) {
                this.featureList.remove(f_index);
            }
        }
        else{
            for (int i=0;i<this.featureList.size();i++){
                if(this.featureList.get(i).equals(feature)) {
                    f_index = i;
                }
            }
            if (f_index==-1){
                featureList.add(feature);
            }
        }
    }

    public void setVendorName(String name){this.VendorName=name;}

    public String getVendorName(){return VendorName;}
    public ArrayList <Integer> getAntenaType(){
        return AntenaType;
    }
    public ArrayList <Integer> getAntenaPower(){
        return AntenaPower;
    }
    public Pos getLocation(){
        return location;
    }
    public int getBattery(){
        return Battery;
    }
    public Mode getMode() {
        return mode;
    }
    public int getAlarm(){
        return Alarm;
    }

    public ArrayList<String> getFeatureList(){
        return featureList;
    }
   // public abstract void build_antena();
   // public abstract void build_featureList();
   // public abstract void build_battery();
    public void setAntenaType(int x){
        if(this.AntenaType==null){
            this.AntenaType=new ArrayList<Integer>();
        }
        this.AntenaType.add(x);
    }
    public void setAntenaPower(int x){
        if(this.AntenaPower==null){
            this.AntenaPower=new ArrayList<Integer>();
        }
        this.AntenaPower.add(x);
    }

}

