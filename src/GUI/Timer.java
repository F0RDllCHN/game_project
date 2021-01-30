package GUI;

import javafx.application.Platform;

public class Timer {

	private static final int SLEEP_TIME = 1000;
	private int time;
	private Thread thread;
	private boolean start;
	private InfoLabel timeGui;

	public Timer() {
		this.time = 0;
		timeGui = new InfoLabel("", 585, 590);
		threadInitialize();

	}

	private void threadInitialize() {

		if (thread != null) {
			thread.interrupt();
		}
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while (start) {
						time++;
						updateGUI();
						Thread.sleep(SLEEP_TIME);
					}
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
			}
		});
	}

	public void startStopTiming() {

		// implement your code here
		if (start == false) {
			start = true;
			threadInitialize();
			getThread().start();
		} else if (start == true) {
			start = false;
		}
	}

	public Thread getThread() {
		return thread;
	}

	public int getTime() {
		return time;
	}

	public InfoLabel getTimeGui() {
		return timeGui;
	}

	private void updateGUI() {

		// implement your code here
		Platform.runLater(() -> {
			timeGui.setText("Played Time : " + getTime() / 3600 + "H " + getTime() / 60 + "M " + getTime() % 60 + "S");
			;
		});
	}

}
