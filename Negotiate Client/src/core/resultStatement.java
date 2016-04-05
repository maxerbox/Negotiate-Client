package core;

import java.net.URLConnection;
import java.util.Map;

import sonalb.net.http.cookie.Client;
import sonalb.net.http.cookie.CookieJar;

//va peut-etre etre remplace par un resultStatement de base avec des interfaces

public class resultStatement implements stageAttributes {
	final stageBase stage;
	final String html;

	resultStatement(stageBase _stage) {
		html = (String) _stage.stageAttributes.get(stageAttr.httpResponse.name());
		stage = _stage;
	}

	public Map<String, String> GetCapturedValues() {

		return null;
	}

	public CookieJar GetCookies() {
		if (!(boolean) stage.stageAttributes.get(stageAttr.saveCookie.name()))
			throw new Exception("Cookie set to no capture! Invalid Stage ?");
		Client client = new Client();
		URLConnection _connection = (URLConnection) stage.stageAttributes.get(stageAttr.httpResponse.name());
		return client.getCookies(_connection);

	}

	public String GetHtml() {
		return html;
	}
}
