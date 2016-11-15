package lejos.music;

import java.io.IOException;
import java.net.SocketException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.network.BroadcastManager;

public class LauncherLeader {
	/**
	 * Starts a robot, according to the pressed button, a different track will be played
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		final TrackReader trackReader = new TrackReader();
		
		final Track violin1 = trackReader.read(LauncherLeader.class.getResourceAsStream("/lejos/music/samples/score01/violin1.txt"));
		final Track violin2 = trackReader.read(LauncherLeader.class.getResourceAsStream("/lejos/music/samples/score01/violin2.txt"));
		final Track violoncello = trackReader.read(LauncherLeader.class.getResourceAsStream("/lejos/music/samples/score01/violoncello.txt"));
		final Track contrabass = trackReader.read(LauncherLeader.class.getResourceAsStream("/lejos/music/samples/score01/contrabass.txt"));
	
		violin1.setBpm(90);
		violin2.setBpm(90);
		violoncello.setBpm(90);
		contrabass.setBpm(90);
		
		LCD.clear();
		LCD.drawString("Leader", 0, 2);
		
		final int button = Button.waitForAnyPress();
		
		if(button == Button.ID_UP) {
			playTrack(violin1);
		} else if(button == Button.ID_RIGHT) {
			playTrack(violin2);
		} else if(button == Button.ID_LEFT) {
			playTrack(violoncello);
		} else if(button == Button.ID_DOWN) {
			playTrack(contrabass);
		}
	}
	
	private static void playTrack(Track track) throws SocketException, IOException {	
		LCD.clear();
		LCD.drawString("Playing...", 0, 2);
		
		String messageS = "Start";
		byte[] messageB = new byte[1024];
		messageB = messageS.getBytes();
		BroadcastManager.getInstance().broadcast(messageB);
		
		while(!track.isOver()) {
			LCD.drawString(String.format("%.4f", track.getTime()), 0, 3);
			messageS = Float.toString(track.getTime());
			messageB = messageS.getBytes();
			BroadcastManager.getInstance().broadcast(messageB);
			
			track.play();
		}
	}
}
