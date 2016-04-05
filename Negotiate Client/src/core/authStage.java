package core;

import java.util.ArrayList;

//The engine will check if some keywords are on the page and determine if the is OK..
public class authStage extends stageBase {
	authStage(ArrayList<KeyWordsList> KeyWords) {
		this.stageAttributes.put(stageAttr.statusKeyWords.name(), new ArrayList<KeyWordsList>());
	}
}
