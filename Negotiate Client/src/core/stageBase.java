package core;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class stageBase implements httpType, stageAttributes {
	Map<String, Object> stageAttributes;

	// Essential
	Map<String, String> headers = new HashMap<>();
	protected protocol prt = protocol.HTTP;
	type tpe = type.GET;
	URL url = null;
	String data = null;
	stageClass classStage = stageClass.simple;
	String StageName;
	String StageDescription;

	protected void setProtocol(URL url) throws Exception {
		String prpt = url.getProtocol();
		switch (prpt) {
		case "http":
			prt = protocol.HTTP;
			break;
		case "https":
			prt = protocol.HTTPS;
			break;
		default:

			throw new Exception("protocol unavailable: " + prpt);
		}
	}
}
