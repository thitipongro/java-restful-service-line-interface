package ws.integation.system.environment;

import ws.integation.util.LineInterfaceException;

public class SystemType {

	public static final EnvType ENV_TYPE = EnvType.LOCALHOST;
	
	private SystemType() throws LineInterfaceException {
		throw new LineInterfaceException("Utility class");
	}
}
