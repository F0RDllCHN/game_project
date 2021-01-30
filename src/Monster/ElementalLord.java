package Monster;

import java.util.Random;

import Hero.Hero;

public class ElementalLord extends Monster {

	private static final String ELEMENTALLORD_URL = "Nachtjart.png";

	public ElementalLord() {
		super("Elemental Lord", 130, 70, 8);
		setUrl(ELEMENTALLORD_URL);
	}

	@Override
	public void attack(Hero hero) {
		int damage;
		Random random = new Random();
		int chance = random.nextInt(10);
		if (chance > 7) {
			damage = (this.getAtk() + 40) - hero.getDef();
		} else {
			damage = this.getAtk() - hero.getDef();
		}
		if (damage < 0) {
			damage = 0;
		}
		hero.setCurrentHp(hero.getCurrentHp() - damage);

	}

}
