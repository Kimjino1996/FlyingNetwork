package FLYING_NE;

import java.util.List;

import COMMON.AttachInfo;
import COMMON.AttachRsp;
import COMMON.Pos;
import java.util.ArrayList;

public class ConcreteF_NE implements F_NE{

	@Override
	public AttachRsp attach(AttachInfo attach_req) {
		// TODO Auto-generated method stub

		FNE_controller controller= FNE_controller.getInstance();
		AttachRsp reponse;
		if (attach_req.getAuth_key().equals("samsung")||attach_req.getAuth_key().equals("network")){
			List<String> ActivateFeature=controller.getFNEInformation().getFeatureList();
			List<String> temp=new ArrayList<String>();
			int flag=0;
			for (String UeFeature:attach_req.getUeCapability()){
				for (String FNE_Feature:ActivateFeature) {
					if (UeFeature.equals(FNE_Feature)) {
						flag = 1;
					}
				}
				if(flag==0){
					temp.add(UeFeature);
				}
				else{
					flag=0;
				}
			}
			int ip=controller.assignIP();
			if (ip!=-1) {
				reponse=new AttachRsp(true,Integer.toString(ip));
				controller.setAttached(attach_req.getUserId(), attach_req.getUeCapability(), attach_req.getAuth_key(),ip );
				return reponse;
			}
			System.out.print("วาด็");
		}
		reponse=new AttachRsp(false,Integer.toString(-1));
		return reponse;
	}

	@Override
	public void detach(int ue_id) {
		// TODO Auto-generated method stub
		FNE_controller controller= FNE_controller.getInstance();
		controller.setDetach(ue_id);
	}

	@Override
	public List<Pos> get_coverage() {
		// TODO Auto-generated method stub
		FNE_controller controller= FNE_controller.getInstance();
		return controller.getCoverage();

	}

	@Override
	public void send_data(int ue_id, String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean command(String command) {
		// TODO Auto-generated method stub
		FNE_controller controller= FNE_controller.getInstance();
		if(command.equals("show attach")){
			controller.show_attach();
		}
		else if(command.equals("move right")){
			controller.moveLocation(1,0);
		}
		else if(command.equals("move left")){
			controller.moveLocation(-1,0);
		}
		else if(command.equals("move up")){
			controller.moveLocation(0,-1);
		}
		else if (command.equals("move down")){
			controller.moveLocation(0,1);
		}
		else if(command.equals("show fne")){
			controller.show_FNE_Inform();
		}
		else if(command.equals("move many")){
			controller.getFNEInformation().setBattery(40);
		}
		return false;
	}

	@Override
	public Pos get_pos() {
		// TODO Auto-generated method stub
		FNE_controller controller= FNE_controller.getInstance();
		return controller.getLocation();
	}

}
