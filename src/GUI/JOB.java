package GUI;

public enum JOB {
	Swordman("swordsman-min.png"), Mage("mage.png"), Archer("Archer.png"), Novice("Novice.png");

	private String urlJob;

	private JOB(String urlJob) {
		this.urlJob = urlJob;
	}

	public String getUrl() {
		return this.urlJob;
	}
}
