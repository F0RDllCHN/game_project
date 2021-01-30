package Stage;

import GUI.GameButton;
import GUI.StageNode;
import Hero.Hero;
import Monster.Bug;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Stage5 extends StageDefault {

	public Stage5(Hero player, Stage mainStage, Scene gameScene, StageNode stageNode, GameButton jobChangeButton) {
		super(player, mainStage, gameScene, stageNode, 50, jobChangeButton);
		Bug GoldenBug = new Bug();
		GoldenBug.evolution();
		spawnMonster(GoldenBug);
	}

}
