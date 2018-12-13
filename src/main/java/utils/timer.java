package utils;

public enum timer {
	INSTANCE;
	long timerStart;
	long timerEnd;

	public void setTimerStart() {
		this.timerStart = System.currentTimeMillis();
	}

	public void setTimerEnd() {
		this.timerEnd = System.currentTimeMillis();
	}

	public void runTime() {
		System.out.println("Run time: " + (this.timerEnd - this.timerStart) + "ms");
	}

}
