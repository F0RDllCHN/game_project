package Hero;

import GUI.JOB;
import Monster.Monster;
import Skill.HeroSkill;

public class Mage extends Hero implements HeroSkill {

	public Mage(String name) {
		super(150, 10, 8, 18, 5, "Mage", 19);
		setName(name);
		setSkill1Name("Mana Ball");
		setSkill1Des("Deal damage base on int stat to enemy. ( Mp cost 0 )");
		setSkill2Name("Meteor");
		setSkill2Des("Summun meteor to crush an enemy. ( Mp cost 19 )");
		setHeroImageUrl(JOB.Mage.getUrl());
	}

	@Override
	public void CheckUnlockableSkill() {
		if (getLevel() == 7) {
			setUnlockSkill(true);
		}

	}

	@Override
	public void attackWithSkill1(Monster monster) {
		int damage;
		damage = this.getInt() - monster.getDef();
		if (damage < 0) {
			damage = 0;
		}
		monster.setCurrentHp(monster.getCurrentHp() - damage);
	}

	@Override
	public void attackWithSkill2(Monster monster) {
		int damage;
		if (this.getCurrentMp() >= getSkill2MpCost()) {
			this.setCurrentMp(this.getCurrentMp() - getSkill2MpCost());
			damage = ((this.getInt() + 5) * 2) - monster.getDef();
			if (damage < 0)
				damage = 0;
			monster.setCurrentHp(monster.getCurrentHp() - damage);
		}
	}

}
