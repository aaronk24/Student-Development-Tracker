package org.tg8.sdt.data.file;

import java.io.IOException;
import java.util.Date;

class SDTAutoSave implements Runnable {

	private static final long AUTO_SAVE_INTERVAL = 60000;
	//how often to save, in milliseconds
	
	private SDTDAOImpl dao;
	private boolean keepRunning=true;
	private long nextSaveTime; //next time to autosave, in milliseconds
		
	
	SDTAutoSave (SDTDAOImpl dao) {
		this.dao = dao;
	}
	
	synchronized boolean isKeepRunning() {
		return keepRunning;
	}
	synchronized void setKeepRunning(boolean keepRunning) {
		this.keepRunning = keepRunning;
	}
	private long getNextSaveTime() {
		return nextSaveTime;
	}
	private void setNextSaveTime(long nextSaveTime) {
		this.nextSaveTime = nextSaveTime;
	}
	@Override
	public void run() {
		while(this.isKeepRunning()) {
			long currentTime = new Date().getTime();
			if (currentTime >= this.getNextSaveTime()) {
				try {
					this.dao.save();
					this.setNextSaveTime(new Date().getTime() + SDTAutoSave.AUTO_SAVE_INTERVAL);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			Thread.yield();
		}
	}
}
