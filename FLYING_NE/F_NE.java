package FLYING_NE;
import java.util.List;

import COMMON.AttachInfo;
import COMMON.AttachRsp;
import COMMON.Pos;

public interface F_NE {
	AttachRsp attach(AttachInfo attach_req);
	void detach(int ue_id);
	List<Pos> get_coverage();
	void send_data(int ue_id, String data);
	boolean command(String command);
	Pos get_pos();
}



