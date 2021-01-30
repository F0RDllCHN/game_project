package Hero;

import GUI.JOB;
import Monster.Monster;
import Skill.HeroSkill;

public class Swordman extends Hero implements HeroSkill {

	public Swordman(String name) {
		super(200, 15, 10, 6, 5, "Swordman", 12);
		setName(name);
		setSkill1Name("Slash");
		setSkill1Des("Deal damage to enemy. ( Mp cost 0 )");
		setSkill2Name("Giga Impact");
		setSkill2Des("Deal a lot of damage to enemy. ( Mp cost 12 )");
		setHeroImageUrl(JOB.Swordman.getUrl());
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
		damage = this.getAtk() - monster.getDef();
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
			damage = (this.getAtk() * 2) - monster.getDef();
			if (damage < 0) {
				damage = 0;
			}
			monster.setCurrentHp(monster.getCurrentHp() - damage);
		}

	}

}
