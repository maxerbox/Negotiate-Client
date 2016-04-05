package core;

import java.net.URL;

import sonalb.net.http.cookie.CookieJar;

public class lookStage extends stageBase {

	lookStage(URL _url, boolean capCookies) throws Exception {
		if (capCookies) {
			this.stageAttributes.put(stageAttr.saveCookie.name(), true);
			this.stageAttributes.put(stageAttr.cookies.name(), new CookieJar());
		}
		this.setProtocol(_url);
		this.classStage = stageClass.look;
	}
}
