package Monster;

import Hero.Hero;

public class EvilSnake extends Monster {

	private static final String EVIL_SNAKE_URL = "darklordsnake.png";

	public EvilSnake() {
		super("EvilSnake", 120, 35, 8);
		setUrl(EVIL_SNAKE_URL);
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
