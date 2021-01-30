package Monster;

import Hero.Hero;

public class Dragon extends Monster {

	private static final String DRAGON_URL = "dragon.png";

	public Dragon() {
		super("Dragon", 150, 40, 10);
		setUrl(DRAGON_URL);
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
