package osgi.enroute.iot.toolkit;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import osgi.enroute.dto.api.DTOs;
import osgi.enroute.iot.gpio.api.CircuitBoard;
import osgi.enroute.iot.gpio.api.IC;
import osgi.enroute.iot.gpio.util.Digital;
import osgi.enroute.iot.gpio.util.ICAdapter;
import osgi.enroute.iot.toolkit.Toggle.FlipConfig;

@Component(designateFactory=FlipConfig.class, provide=IC.class, name="osgi.enroute.iot.toolkit.toggle")
public class Toggle extends ICAdapter<Digital, Digital> implements Digital {

	interface FlipConfig {
		String name();
	}
	boolean state;
	
	@Override
	public synchronized void set(boolean value) throws Exception {
		if ( value == false ) {
			state = !state;
		}
		out().set(state);
	}
	
	@Reference
	protected void setDTOs(DTOs dtos) {
		super.setDTOs(dtos);
	}


	@Reference
	protected
	void setCircuitBoard(CircuitBoard board) {
		super.setCircuitBoard(board);
	}

}
