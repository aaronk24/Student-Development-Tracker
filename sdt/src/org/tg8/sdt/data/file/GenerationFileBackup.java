package org.tg8.sdt.data.file;

import java.io.File;

class GenerationFileBackup {
	static final int NBR_GENERATIONS = 10;
	 
	static void backupFiles (String suffix) {
		GenerationFileBackup.copyFileGenerations(SDTDAOImpl.STUDENT_FILE, suffix,
				GenerationFileBackup.NBR_GENERATIONS);
		GenerationFileBackup.copyFileGenerations(SDTDAOImpl.GATE_SERVICE_FILE, suffix,
				GenerationFileBackup.NBR_GENERATIONS);
		GenerationFileBackup.copyFileGenerations(SDTDAOImpl.STUDENT_ATTENDANCE_FILE, suffix,
				GenerationFileBackup.NBR_GENERATIONS);
		GenerationFileBackup.copyFileGenerations(SDTDAOImpl.ID_FILE, suffix,
				GenerationFileBackup.NBR_GENERATIONS);
	}
	
	//delete the oldest allowed generation
	//increment the remaining generations
	//add a generation number to the main file
	static void copyFileGenerations (String fileBaseName, String suffix,
			int nbrGenerations) {
		GenerationFileBackup.deleteFile(new File(SDTDAOImpl.FILE_PATH +
				File.separator + fileBaseName + suffix + nbrGenerations));
		
		for ( int i = nbrGenerations; i > 1; --i) {
			GenerationFileBackup.renameFile(
				new File(SDTDAOImpl.FILE_PATH + File.separator + fileBaseName + suffix + (i-1) ), 
				new File(SDTDAOImpl.FILE_PATH + File.separator + fileBaseName + suffix + i)
			);
		}
		GenerationFileBackup.renameFile( 
			new File(SDTDAOImpl.FILE_PATH + File.separator + fileBaseName), 
			new File(SDTDAOImpl.FILE_PATH +	File.separator + fileBaseName + suffix + 1)
		);
	}
	
	// Deletes a file if it exists
	private static void deleteFile(File toDelete) {
		if (toDelete.exists()) {
			toDelete.delete();
		}
	}
	
	// Renames a file, if it exists
	private static void renameFile(File oldName, File newName) {
		if (oldName.exists()) {
			oldName.renameTo(newName);
		}
	}
	
}
