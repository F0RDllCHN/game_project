package Stage;

import GUI.GameButton;
import GUI.StageNode;
import Hero.Hero;
import Monster.Slime;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Stage2 extends StageDefault {

	public Stage2(Hero player, Stage mainStage, Scene gameScene, StageNode stageNode, GameButton JobChangeButton) {
		super(player, mainStage, gameScene, stageNode, 20, JobChangeButton);
		Slime blackSlime = new Slime();
		blackSlime.evolution();
		spawnMonster(blackSlime);
	}

}
