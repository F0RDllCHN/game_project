package Stage;

import GUI.GameButton;
import GUI.StageNode;
import Hero.Hero;
import Monster.Orc;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Stage6 extends StageDefault {

	public Stage6(Hero player, Stage mainStage, Scene gameScene, StageNode stageNode, GameButton jobChangeButton) {
		super(player, mainStage, gameScene, stageNode, 60, jobChangeButton);
		Orc orc = new Orc();
		spawnMonster(orc);
	}

}
