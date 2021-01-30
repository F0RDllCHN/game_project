package Monster;

import Hero.Hero;
import Skill.MonsterEvolution;

public class Bug extends Monster implements MonsterEvolution {

	private static final String BUG_URL = "Bug.png";
	private static final String GOLDEN_BUG_URL = "GoldenBug.png";

	public Bug() {
		super("Thief Bug", 20, 25, 3);
		setUrl(BUG_URL);
	}

	private void setAsGoldenBug() {
		setName("Golden Bug");
		setAtk(40);
		setMaxHp(50);
		setDef(6);
		setUrl(GOLDEN_BUG_URL);
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
		setAsGoldenBug();
	}

}
