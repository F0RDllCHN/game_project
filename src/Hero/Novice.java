package Hero;

import GUI.JOB;
import Monster.Monster;
import Skill.HeroSkill;

public class Novice extends Hero implements HeroSkill {

	public Novice() {
		super(100, 1, 1, 1, 1, "Novice", 6);
		setSkill1Name("Stab");
		setSkill1Des("Deal damage to enemy. ( Mp cost 0 )");
		setSkill2Name("triple strike");
		setSkill2Des("Deal damage to enemy 3 times. ( Mp cost 6 )");
		setHeroImageUrl(JOB.Novice.getUrl());
	}

	@Override
	public void CheckUnlockableSkill() {
		if (getLevel() == 3) {
			setUnlockSkill(true);
		}
	}

	@Override
	public void attackWithSkill1(Monster monster) {
		int damage;
		damage = this.getAtk() - monster.getDef();
		if (damage < 0)
			damage = 0;
		monster.setCurrentHp(monster.getCurrentHp() - damage);

	}

	@Override
	public void attackWithSkill2(Monster monster) {
		int damage;
		if (this.getCurrentMp() >= getSkill2MpCost()) {
			this.setCurrentMp(this.getCurrentMp() - getSkill2MpCost());
			damage = ((this.getAtk() / 2) * 3) - monster.getDef();
			if (damage < 0)
				damage = 0;
			monster.setCurrentHp(monster.getCurrentHp() - damage);
		}

	}

}
