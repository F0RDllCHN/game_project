package Stage;

import GUI.GameButton;
import GUI.StageNode;
import Hero.Hero;
import Monster.Thanatos;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Stage10 extends StageDefault {

	public Stage10(Hero player, Stage mainStage, Scene gameScene, StageNode stageNode, GameButton jobChangeButton) {
		super(player, mainStage, gameScene, stageNode, 100, jobChangeButton);
		Thanatos thanatos = new Thanatos();
		spawnMonster(thanatos);
	}

}
