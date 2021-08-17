package FLYING_NE;

import COMMON.Pos;

import java.util.ArrayList;

import FLYING_NE.F_NE_Information;
public class builder {
    private ArrayList<Integer> AntenaType;
    private ArrayList <Integer> AntenaPower;
    private Pos location;

    private int Battery ;
    //private String Mode;
    private Mode mode;
    private int Alarm;
    private String vendorName;
    private SavingEnergy saveEnergy=new SavingEnergy();
    private NoPowerMode noPower= new NoPowerMode();
    private NormalMode normal=new NormalMode();

    private ArrayList<String> featureList;



    public builder (){

    }
    public builder setVendorName(String name){this.vendorName=name; return this;}
    public builder setAntenaType(int x){
        if(this.AntenaType==null){
            this.AntenaType=new ArrayList<Integer>();
        }
        this.AntenaType.add(x);
        return this;
    }
    public builder setAntenaPower(int x){
        if(this.AntenaPower==null){
            this.AntenaPower=new ArrayList<Integer>();
        }
        this.AntenaPower.add(x);
        return this;
    }

    public builder setBattery(int battery){
        this.Battery=battery;
        if (this.Battery>50){
            setMode(normal);
            //  Mode kkk= new SavingEnergy();
            // kkk.moving(kkk,AntenaPower);
//           ���⼭ moving(this) �� ��ü ���� �Ǵ��ؼ� �ٽ� ������� build ����� �� �� ?
            // �ƴϸ� �׳� setMode�� �� ��Ż� ���� ���� overide �ص� ��.

        }
        else if(this.Battery<=50 && this.Battery>=20){
            setMode(saveEnergy);
        }
        else if(this.Battery<20){
            setMode(noPower);
        }
        return this;
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
    public builder setFeatureList(String feature, String onOff){
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
        return this;
    }
    public builder setLocation(int x, int y){
        this.location=new Pos(x,y);
        return this;
    }

    public F_NE_Information result(){
        F_NE_Information temp =new F_NE_Information(AntenaType,AntenaPower,location,vendorName,Battery,mode,Alarm,featureList);
        return temp;
    }
}
