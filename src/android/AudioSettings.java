package com.surem.cordova.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.media.AudioManager;

public class AudioSettings extends CordovaPlugin {

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
		if (action.equals("get")) {
			try {
				callbackContext.success(get());
				
			} catch (Exception e) {
				callbackContext.error("get error");
			}
			return true;
			
		} else if (action.equals("set")) {
			try {
				set(args.getJSONObject(0));
				callbackContext.success(get());
				
			} catch (Exception e) {
				callbackContext.error("set error");
			}
			return true;
		}
		return false;
	}
	
	public JSONObject get() throws JSONException {
		AudioManager audioManager = (AudioManager) cordova.getActivity().getApplicationContext().getSystemService(Service.AUDIO_SERVICE);
		
		JSONObject obj = new JSONObject();
		obj.put("volume", audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL));
		obj.put("max_volume", audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL));
		
		obj.put("is_speaker_phone_on", audioManager.isSpeakerphoneOn());
		obj.put("is_microphone_mute", audioManager.isMicrophoneMute());
		
		obj.put("mode", audioManager.getMode());
		obj.put("ringer_mode", audioManager.getRingerMode());
		
		return obj;
	}
	
	public void set(JSONObject obj) throws JSONException {
		AudioManager audioManager = (AudioManager) cordova.getActivity().getApplicationContext().getSystemService(Service.AUDIO_SERVICE);

		int flags = 0;
		
		int volume = obj.getInt("volume");
		audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, volume, flags);
		
		boolean is_speaker_phone_on = obj.getBoolean("is_speaker_phone_on");
		audioManager.setSpeakerphoneOn(is_speaker_phone_on);
		
		boolean is_microphone_mute = obj.getBoolean("is_microphone_mute");
		audioManager.setMicrophoneMute(is_microphone_mute);
		
		int mode = obj.getInt("mode");
		audioManager.setMode(mode);
		
		int ringer_mode = obj.getInt("ringer_mode");
		audioManager.setRingerMode(ringer_mode);
	}
	
}
