package Monster;

import Hero.Hero;

public class Thanatos extends Monster {

	private static final String THANATOS_URL = "Thanatos.png";

	public Thanatos() {
		super("Thanatos", 200, 70, 12);
		setUrl(THANATOS_URL);
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
