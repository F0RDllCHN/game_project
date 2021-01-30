package Hero;

import Skill.HeroSkill;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class Hero implements HeroSkill {
	protected int maxHp;
	protected int currentHp;
	protected int maxMp;
	protected int currentMp;
	protected String Name;
	protected int Atk;
	protected int Def;
	protected int Int;
	protected String Job;
	protected int Level;
	protected int Exp;
	protected int maxExp;
	protected boolean unlockSkill = false;
	protected String skill1Name;
	protected String skill1Des;
	protected String skill2Name;
	protected String skill2Des;
	protected int skill2MpCost;
	protected String HeroImageUrl;

	public Hero(int maxHp, int atk, int def, int Int, int level, String job, int skill2MpCost) {
		
		setMaxHp(maxHp);
		setAtk(atk);
		setDef(def);
		setInt(Int);
		setMaxMp();
		setLevel(level);
		setMaxExp();
		setJob(job);
		recovery();
		setSkill2MpCost(skill2MpCost);

	}

	public void recovery() {
		setCurrentHp(getMaxHp());
		setCurrentMp(getMaxMp());
	}

	private void increaseAllStat() {
		setMaxHp(getMaxHp() + 5);
		setAtk(getAtk() + 3);
		setDef(getDef() + 2);
		setInt(getInt() + 1);
		setMaxMp();
		recovery();
	}

	private void checkLevelUp() {
		if (getExp() >= getMaxExp()) {
			int leftOverExp = getExp() - getMaxExp();
			setLevel(getLevel() + 1);
			setMaxExp();
			setExp(leftOverExp);
			increaseAllStat();
			CheckUnlockableSkill();
			Alert levelUp = new Alert(AlertType.INFORMATION);
			levelUp.setTitle("Paper Adventure");
			levelUp.setHeaderText("  ------ LEVEL UP !! ------  ");
			levelUp.setContentText("     Increase all stat    ");
			levelUp.showAndWait();
			checkLevelUp();
		}

	}

	public void recieveExp(int exp) {
		this.Exp += exp;
		checkLevelUp();
	}

	public boolean isDefeated() {
		return this.currentHp == 0;
	}

	// getter - setter

	public int getMaxHp() {
		return maxHp;
	}

	public int getSkill2MpCost() {
		return skill2MpCost;
	}

	public void setSkill2MpCost(int skill2MpCost) {
		this.skill2MpCost = skill2MpCost < 0 ? 0 : skill2MpCost;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp < 1 ? 1 : maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp < 0 ? 0 : currentHp;
	}

	public int getCurrentMp() {
		return currentMp;
	}

	public void setCurrentMp(int currentMp) {
		this.currentMp = currentMp < 0 ? 0 : currentMp;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAtk() {
		return Atk;
	}

	public void setAtk(int atk) {
		Atk = atk < 1 ? 1 : atk;
	}

	public int getDef() {
		return Def;
	}

	public void setDef(int def) {
		Def = def < 0 ? 0 : def;
	}

	public int getInt() {
		return Int;
	}

	public void setInt(int i) {
		Int = i < 1 ? 1 : i;
	}

	public String getJob() {
		return Job;
	}

	public void setJob(String job) {
		Job = job;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level < 1 ? 1 : level;
	}

	public int getMaxMp() {
		return maxMp;
	}

	public void setMaxMp() {
		this.maxMp = this.Int * 5;
	}

	public int getExp() {
		return Exp;
	}

	public void setExp(int exp) {
		Exp = exp < 0 ? 0 : exp;
	}

	public int getMaxExp() {
		return maxExp;
	}

	public void setMaxExp() {
		this.maxExp = this.Level * 10;
	}

	public boolean isUnlockSkill() {
		return unlockSkill;
	}

	public void setUnlockSkill(boolean unlockSkill) {
		this.unlockSkill = unlockSkill;
	}

	public String getSkill1Name() {
		return skill1Name;
	}

	public void setSkill1Name(String skill1Name) {
		this.skill1Name = skill1Name;
	}

	public String getSkill1Des() {
		return skill1Des;
	}

	public void setSkill1Des(String skill1Des) {
		this.skill1Des = skill1Des;
	}

	public String getSkill2Name() {
		return skill2Name;
	}

	public void setSkill2Name(String skill2Name) {
		this.skill2Name = skill2Name;
	}

	public String getSkill2Des() {
		return skill2Des;
	}

	public void setSkill2Des(String skill2Des) {
		this.skill2Des = skill2Des;
	}

	public String getHeroImageUrl() {
		return HeroImageUrl;
	}

	public void setHeroImageUrl(String heroImageUrl) {
		HeroImageUrl = heroImageUrl;
	}

	//

}
