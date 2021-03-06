package core;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class StageRequester implements httpType, stageAttributes {
 public enum RequesterAttr {
	 UseProxy("useProxy",boolean.class),
	 ProxyIp("ipProxy",String.class),
	 ProxyPort("portProxy",Int.class),
	 UseSocks("useSocks",boolean.class),
	 SocksIp("ipSocks",String.class),
	 SocksPort("portSocks",String.class);
	 
		private final String value;
		private final Class<?> type;
		RequesterAttr(String _value,Class<?> _type){
			value=_value;
			type=_type;
			
		}
		Class<?> GetType() {
			return type;
		}
		String toString(){
			return value;
		}
 }
	private Map<String,Object> engineAttr = null;
	private stageBase stage = null;
	public void SetStage(stageBase _stage) {
		stage=_stage;
	}
	StageRequester(stageBase _stage) {
		stage=_stage;
	}
	StageRequester(){}
	public resultStatement request() throws Exception {
		if(stage==null)
			throw new Exception("Stage not set");
		stageBase _response = null;
		switch (stage.prt) {
		case HTTP:
			_response = HTTP(stage);
			break;

		case HTTPS:
			_response = HTTPS(stage);
			break;
		// No default needed
		}

		return new resultStatement(_response);

	}

	private stageBase HTTP(stageBase stage) throws Exception {
		HttpURLConnection _connection = null;
		URL url = stage.url;
		_connection = (HttpURLConnection) url.openConnection();
		_connection.setRequestMethod(stage.tpe.toString());
		for (Map.Entry<String, String> entry : stage.headers.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			_connection.setRequestProperty(key, value);
		}
		_connection.setRequestProperty("Content-Length", Integer.toString(stage.data.getBytes().length));
		_connection.setUseCaches(false);
		_connection.setDoOutput(true);

		DataOutputStream wr = new DataOutputStream(_connection.getOutputStream());
		wr.writeBytes(stage.data);
		wr.close();

		InputStream is = _connection.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		StringBuilder response = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			response.append(line);
			response.append('\r');

		}
		stage.stageAttributes.put(stageAttr.urlConnection.name(), (URLConnection) _connection);
		stage.stageAttributes.put(stageAttr.httpResponse.name(), response);
		return stage;
	}

	private stageBase HTTPS(stageBase stage) throws Exception {
		HttpURLConnection _connection = null;
		URL url = stage.url;
		_connection = (HttpsURLConnection) url.openConnection();
		_connection.setRequestMethod(stage.tpe.toString());
		for (Map.Entry<String, String> entry : stage.headers.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			_connection.setRequestProperty(key, value);
		}
		_connection.setRequestProperty("Content-Length", Integer.toString(stage.data.getBytes().length));
		_connection.setUseCaches(false);
		_connection.setDoOutput(true);

		DataOutputStream wr = new DataOutputStream(_connection.getOutputStream());
		wr.writeBytes(stage.data);
		wr.close();

		InputStream is = _connection.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		StringBuilder response = new StringBuilder(); // or StringBuffer if not
														// Java 5+
		String line;
		while ((line = rd.readLine()) != null) {
			response.append(line);
			response.append('\r');

		}
		stage.stageAttributes.put(stageAttr.urlConnection.name(), (URLConnection) _connection);
		stage.stageAttributes.put(stageAttr.httpResponse.name(), response);
		return stage;
	}
}
