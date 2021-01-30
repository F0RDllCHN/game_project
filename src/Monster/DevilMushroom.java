package Monster;

import Hero.Hero;

public class DevilMushroom extends Monster {

	private static final String MUSHROOM_URL = "Mushroom.png";

	public DevilMushroom() {
		super("Devil Mushroom", 30, 35, 4);
		setUrl(MUSHROOM_URL);
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
