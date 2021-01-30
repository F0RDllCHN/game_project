package Stage;

import GUI.GameButton;
import GUI.StageNode;
import Hero.Hero;
import Monster.Orc;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Stage7 extends StageDefault {

	public Stage7(Hero player, Stage mainStage, Scene gameScene, StageNode stageNode, GameButton jobChangeButton) {
		super(player, mainStage, gameScene, stageNode, 70, jobChangeButton);
		Orc orcHero = new Orc();
		orcHero.evolution();
		spawnMonster(orcHero);
	}

}
