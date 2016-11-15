package lejos.network;

public class MusicListenerCommon implements BroadcastListener {

	private boolean ready;
	private float leaderTime;

	public MusicListenerCommon() {
		super();
		this.ready = false;
		this.leaderTime = 0;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public float getLeaderTime() {
		return leaderTime;
	}

	public void setLeaderTime(float time) {
		this.leaderTime = time;
	}

	@Override
	public void onBroadcastReceived(byte[] message) {

		String messageS = message.toString();
		if (messageS.equals("Start")) {
			ready = true;
		} else {
			leaderTime = Float.valueOf(messageS);
		}

	}

}
