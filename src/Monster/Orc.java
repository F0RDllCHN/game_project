package Monster;

import Hero.Hero;
import Skill.MonsterEvolution;

public class Orc extends Monster implements MonsterEvolution {

	private static final String ORC_URL = "Orc.png";
	private static final String ORC_HERO_URL = "OrcHero.png";

	public Orc() {
		super("Orc", 70, 30, 3);
		setUrl(ORC_URL);
	}

	private void setAsOrcHero() {
		setName("Orc Hero");
		setMaxHp(90);
		setAtk(35);
		setUrl(ORC_HERO_URL);
	}

	@Override
	public void evolution() {
		setAsOrcHero();
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

}
