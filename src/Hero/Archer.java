package Hero;

import java.util.Random;

import GUI.JOB;
import Monster.Monster;
import Skill.HeroSkill;

public class Archer extends Hero implements HeroSkill {

	public Archer(String name) {
		super(150, 20, 8, 6, 5, "Archer", 15);
		setName(name);
		setSkill1Name("Single Shot");
		setSkill1Des("shoot an enemy. ( Mp cost 0 )");
		setSkill2Name("Charged shot");
		setSkill2Des("shoot an enemy. have chance to draw critical. \n\t\t        ( Mp cost 15 )");
		setHeroImageUrl(JOB.Archer.getUrl());
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
		if (damage < 0)
			damage = 0;
		monster.setCurrentHp(monster.getCurrentHp() - damage);

	}

	@Override
	public void attackWithSkill2(Monster monster) {
		int damage;
		if (this.getCurrentMp() >= getSkill2MpCost()) {
			this.setCurrentMp(this.getCurrentMp() - getSkill2MpCost());
			Random random = new Random();
			int Crit = random.nextInt(10);
			if (Crit > 6) {
				Crit = 3;
			} else {
				Crit = 2;
			}
			damage = (this.getAtk() * Crit) - monster.getDef();
			if (damage < 0)
				damage = 0;
			monster.setCurrentHp(monster.getCurrentHp() - damage);
		}
	}

}
