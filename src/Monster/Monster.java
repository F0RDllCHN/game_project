package Monster;

import Hero.Hero;

public abstract class Monster {
	protected int maxHp;
	protected int currentHp;
	protected int atk;
	protected int def;
	protected String name;
	protected String MonsterUrl;

	public Monster(String name, int maxHp, int atk, int def) {
		setMaxHp(maxHp);
		setAtk(atk);
		setDef(def);
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDefeated() {
		return this.currentHp == 0;
	}

	// setter - getter
	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp < 1 ? 1 : maxHp;
		setCurrentHp(maxHp);
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp < 0 ? 0 : currentHp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk < 1 ? 1 : atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def < 0 ? 0 : def;
	}

	protected void setUrl(String Url) {
		this.MonsterUrl = Url;
	}

	public String getUrl() {
		return this.MonsterUrl;
	}

	public abstract void attack(Hero hero);

}
