package Stage;

import GUI.GameButton;
import GUI.StageNode;
import Hero.Hero;
import Monster.ElementalLord;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Stage9 extends StageDefault {

	public Stage9(Hero player, Stage mainStage, Scene gameScene, StageNode stageNode, GameButton jobChangeButton) {
		super(player, mainStage, gameScene, stageNode, 90, jobChangeButton);
		ElementalLord elementalLord = new ElementalLord();
		spawnMonster(elementalLord);

	}

}
