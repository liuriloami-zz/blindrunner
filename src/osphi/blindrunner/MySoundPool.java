package osphi.blindrunner;

import java.io.IOException;

import android.app.Activity;
import android.app.Application;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;

public class MySoundPool extends Application {
	
	private static SoundPool soundPool = null;
	private static Bundle bundle;
	private static MediaPlayer mediaPlayer;
	private static Activity mainActivity;
	
	public static void init(Activity activity) throws IllegalArgumentException, IllegalStateException, IOException {
		
		bundle = new Bundle();
		mainActivity = activity;
		
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setDataSource(activity.getAssets().openFd("ambienceMusic.mp3").getFileDescriptor());
		mediaPlayer.setVolume(0.7f, 0.7f);
		mediaPlayer.prepare();
		mediaPlayer.setLooping(true);
		
		soundPool = new SoundPool(7, AudioManager.STREAM_MUSIC, 0);
		soundPool.setOnLoadCompleteListener( new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				if (status == 0)
					try {
							soundPool.load(mainActivity.getAssets().openFd(bundle.getString(Integer.toString(sampleId))), 1);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		
		bundle.putInt("monsterId", MySoundPool.load(activity,  "monster.mp3"));
		bundle.putInt("exitId", MySoundPool.load(activity,  "exitSound.mp3"));
		bundle.putInt("playerId", MySoundPool.load(activity,  "playerStep.mp3"));
		bundle.putInt("playerTurningId", MySoundPool.load(activity,  "playerStep2.mp3"));
		bundle.putInt("wallId", MySoundPool.load(activity,  "wall.mp3"));
		bundle.putInt("freedomId", MySoundPool.load(activity,  "freedom.mp3"));
		bundle.putInt("gameoverId", MySoundPool.load(activity,  "monsterGrowl.mp3"));

	}
	public static void set (Activity act) throws IOException {
		
		//MySoundPool.stop();
	}
	
	public static void resume (String in) {
		soundPool.resume(bundle.getInt(in));
	}
	
	public static void startPlayer () throws IllegalStateException, IOException {
			mediaPlayer.start();
	}
	
	public static void setRate (String in, float rate) {
		soundPool.setRate(bundle.getInt(in), rate);
	}
	
	public static int load (Activity activity, String filename) throws IOException {
		return soundPool.load(activity.getAssets().openFd(filename), 1);
	}
	
	public static void stopOne (String in) {
		soundPool.stop(bundle.getInt(in));
	}

	public static void stop () throws IllegalStateException, IOException {
		mediaPlayer.stop();
		mediaPlayer.prepareAsync();
		MySoundPool.stopOne("monsterId");
		MySoundPool.stopOne("exitId");
	}	
	
	public static void release () {
		mediaPlayer.release();
		soundPool.release();
	}
	
	public static void play(String id, float vLeft, float vRight, boolean loop) {
			if (loop == false)
				soundPool.play(bundle.getInt(id), vLeft, vRight, 1, 0, 1.0f);
			else 
				soundPool.play(bundle.getInt(id), vLeft, vRight, 1, -1, 1.0f);
	}
	
	public static void setVolume (String id, float vLeft, float vRight) {
		soundPool.setVolume(bundle.getInt(id), vLeft, vRight);
	}
}