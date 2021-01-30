package Monster;

import Hero.Hero;
import Skill.MonsterEvolution;

public class Slime extends Monster implements MonsterEvolution {

	private static final String SLIME_URL = "poring04.png";
	private static final String BLACK_SLIME_URL = "Slime.png";

	public Slime() {
		super("Slime", 5, 5, 0);
		setUrl(SLIME_URL);

	}

	private void setAsBlackSlime() {
		setName("Black Slime");
		setAtk(11);
		setDef(2);
		setMaxHp(15);
		setUrl(BLACK_SLIME_URL);
	}

	@Override
	public void attack(Hero hero) {
		int damage;
		damage = this.getAtk() - hero.getDef();
		if (damage < 0) {
			damage = 0;
		}
		hero.setCurrentHp(hero.getCurrentHp() - damage);
	}

	@Override
	public void evolution() {
		setAsBlackSlime();

	}

}
