package core;

public interface stageAttributes {
	public enum stageAttr {
		saveCookie("saveCookie", boolean.class), cookies("cookies",
				sonalb.net.http.cookie.CookieJar.class), urlConnection("urlConnection",
						java.net.URLConnection.class), httpResponse("httpResponse",
								String.class), statusKeyWords("statusKeyWord", java.util.ArrayList.class);
		private final String value;
		private final Class<?> type;

		stageAttr(String _value, Class<?> _type) {
			this.value = _value;
			type = _type;

		}

		public String toString() {
			return value;
		}

		public Class<?> getType() {
			return type;
		}
	}
}
