package core;

public interface httpType {

	public enum protocol {
		HTTP, HTTPS
	}

	public enum type {
		POST("POST"), GET("POST");
		private String tpe = "";

		type(String typ) {
			tpe = typ;
		}

		public String toString() {
			return tpe;
		}
	}

	public enum stageClass {
		simple, capture, look
	}
}
