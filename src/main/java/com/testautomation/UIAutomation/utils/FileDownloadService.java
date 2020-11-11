package com.testautomation.UIAutomation.utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.Uninterruptibles;

@Service
public class FileDownloadService {

	@Value("${downloadedfiles.path}")
	private File dirPath;

	public boolean hasFileDownloaded(String fileName) {
		File[] files = dirPath.listFiles();
		int i = 0;
		while(files.length == 0 && i < 15) {
			Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
			i++;
		}
		if(files.length > 0) {
			List<File> list = Arrays.asList(files); 
			System.out.println("\nFiles are present in the directory!!\n");
			Optional<String> downloadedFile = list.stream()
													.map(file -> file.getName().trim())
													.filter(file -> file.equals(fileName))
													.findFirst();
			return downloadedFile.isPresent();
		}else {
			System.err.println("File Not Downloaded...");
			return false;
		}
	}
}
