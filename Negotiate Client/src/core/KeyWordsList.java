package core;

public class KeyWordsList {
	public enum KeyType {
		AccountGood, AccountBad, AccountBanned;

	}

	public final KeyType type;

	KeyWordsList(KeyType _type) {
		type = _type;
	}

	public String Key = null;

	public String toString() {
		return Key;
	}
}
