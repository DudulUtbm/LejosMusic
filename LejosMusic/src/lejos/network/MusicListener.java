package lejos.network;

public class MusicListener implements BroadcastListener {

	private boolean ready;
	private float leaderTime;

	public MusicListener() {
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

		String messageS = new String(message);
		System.out.println(messageS);
		if (messageS.equals("Start")) {
			ready = true;
		} else {
			try{
				leaderTime = Float.parseFloat(messageS);
			} catch (NumberFormatException e) {
				System.out.println(messageS);
			}
			
		}

	}

}
