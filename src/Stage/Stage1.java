package Stage;

import GUI.GameButton;
import GUI.StageNode;
import Hero.Hero;
import Monster.Slime;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Stage1 extends StageDefault {

	public Stage1(Hero player, Stage mainStage, Scene gameScene, StageNode stageNode, GameButton JobChangeButton) {
		super(player, mainStage, gameScene, stageNode, 10, JobChangeButton);
		Slime slime = new Slime();
		spawnMonster(slime);
	}

}
