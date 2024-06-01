package com.gildedgames.the_aether.vrl;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.*;
import java.util.*;

import java.util.ArrayList;
import java.util.List;

import java.net.URISyntaxException;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ForgetTheOtherMethodsThisIsWhereTheMagicHappens {
    public static void download(String urlstr) {
        try {
            // URL of the file you want to download
            String fileURL = urlstr;
            
            // Destination directory where you want to save the file
            String saveDir = System.getProperty("user.dir") + "/cow"; // Save in the mods folder
            
            // Create the save directory if it doesn't exist
            File directory = new File(saveDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            // Get the file name from the URL
            String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
            
            // Open a connection to the URL
            URL url = new URL(fileURL);
            URLConnection conn = url.openConnection();
            
            // Get the input stream
            InputStream inputStream = conn.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            
            // Create the output file
            File outputFile = new File(saveDir + File.separator + fileName);
            OutputStream outputStream = new FileOutputStream(outputFile);
            
            // Write data to the output file
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            
            // Close streams
            outputStream.close();
            bufferedInputStream.close();
            
            System.out.println("File downloaded successfully to: " + outputFile.getAbsolutePath());

			unpackJar(outputFile, saveDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private static void unpackJar(File jarFile, String outputDir) throws IOException {
        try (JarInputStream jarInputStream = new JarInputStream(new FileInputStream(jarFile))) {
            JarEntry entry;
            while ((entry = jarInputStream.getNextJarEntry()) != null) {
                if (!entry.isDirectory()) {
                    String entryName = entry.getName();
                    File entryFile = new File(outputDir, entryName);
                    File parent = entryFile.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    try (OutputStream outputStream = new FileOutputStream(entryFile)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = jarInputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                }
            }
        }
    }

	public static File getJarName() {
		//try {
        //    String jarFilePath = ForgetTheOtherMethodsThisIsWhereTheMagicHappens.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        //    String jarFileName = new File(jarFilePath).getName();
        //    System.out.println("JAR file name: " + jarFileName);
		//	return jarFileName;
        //} catch (URISyntaxException e) {
        //    e.printStackTrace();
		//	return null;
        //}

		String modFolderStr = System.getProperty("user.dir") + "/mods"; 
		String cowFolderStr = System.getProperty("user.dir") + "/cow";
		String aaaFolderStr = System.getProperty("user.dir") + "/unpack";

		File modFolder = new File(modFolderStr);
		File aaaFolder = new File(aaaFolderStr);
        if (!modFolder.exists()) {
            modFolder.mkdirs();
        }
        if (!aaaFolder.exists()) {
            aaaFolder.mkdirs();
        }

		File[] modFiles = modFolder.listFiles();

        //List<File> ronaldoMods = new ArrayList<>();

        if (modFiles != null) {
            // Iterate through each file in the mods folder
            for (File modFile : modFiles) {
				if(modFile != null)
                if ((modFile.getName().toLowerCase().contains("aether") && modFile.getName().toLowerCase().contains("eparture")) || (modFile.getName().toLowerCase().contains("aether") && modFile.getName().toLowerCase().contains("dirty"))) {
                    //ronaldoMods.add(modFile);
					System.out.println("Detected Aether Departure jar name: " + modFile); //actually keep this one
					return modFile;
                }
            }
        }
		if (modFiles != null) {
            // Iterate through each file in the mods folder
            for (File modFile : modFiles) {
				if(modFile != null)
                if ((modFile.getName().toLowerCase().contains("aether") || modFile.getName().toLowerCase().contains("departure"))) {
                    //ronaldoMods.add(modFile);
                    System.out.println("Detected Aether Departure jar name: " + modFile); //actually keep this one
                    return modFile;
                }
            }
        }
		System.out.println("ERROR ERROR ERROR ERROR ERROR");
		System.out.println("COULD NOT FIND THE JAR NAME FOR AETHER LEGACY DEPARTURE.");
		System.out.println("PLEASE RENAME IT SO IT HAS BOTH aether AND departure IN THE NAME");
		System.out.println("ERROR ERROR ERROR ERROR ERROR");
		return null;
	}

	public static void updateJar(File jarFile) throws Exception {
        //File tempFile = File.createTempFile("temp", ".jar");

		String cowFolderStr = System.getProperty("user.dir") + "/cow";
        File cowFolder = new File(cowFolderStr);

		//File[] filesInCowFolder = cowFolder.listFiles();
		List<File> fileList = new ArrayList<>();
        listFilesRecursively(cowFolder, fileList);
		File[] filesInCowFolder = fileList.toArray(new File[0]);

        if (filesInCowFolder == null) {
            System.err.println("Cow folder empty??");
            return;
        }

		//for (File fileToAdd : filesToAdd) {
		//	System.out.println(fileToAdd);
		//}

		File[] filesToAdd = new File[filesInCowFolder.length];
        for (int i = 0; i < filesInCowFolder.length; i++) {
			//if (filesInCowFolder[i].getAbsolutePath().contains("assets") && !(filesInCowFolder[i].getAbsolutePath().contains("LICENSE")))
			if (filesInCowFolder[i].toString().contains("assets") && !filesInCowFolder[i].toString().contains("legacy") && !filesInCowFolder[i].toString().contains("LICENSE")) {
            	filesToAdd[i] = filesInCowFolder[i];
			}
        }

		for (File fileToAdd : filesToAdd) {
			System.out.println("MOO " + fileToAdd);
		}

		// it was a valiant effort. works on *nix-like, windows is shit and idk how to script for it
		/*
		if(System.getProperty("os.name").toLowerCase().contains("windows")){
			//String[] cmd1 = {"copy", "/Y", jarFile.getAbsolutePath(), (System.getProperty("user.dir") + "\\unpack\\tmp1.zip")};
			String[] cmd1 = {"powershell", "-command", "\"" + "/Y" + " " + jarFile.getAbsolutePath() + " " + (System.getProperty("user.dir") + "\\unpack\\tmp1.zip" + "\"")};
            for (String str : cmd1) {
                System.out.print(str + " ");
            }
            Process process1 = Runtime.getRuntime().exec(cmd1);
            int exitCode = process1.waitFor();
            System.out.println(exitCode);

            String[] cmd2 = {"powershell", "-command", "\"Expand-Archive -Path " + (System.getProperty("user.dir") + "\\unpack\\tmp1.zip") + " -DestinationPath " + (System.getProperty("user.dir") + "\\unpack\\unpacked") + "-Force\""};
            for (String str : cmd2) {
                System.out.print(str + " ");
            }
            Process process2 = Runtime.getRuntime().exec(cmd2);
            exitCode = process2.waitFor();
            System.out.println(exitCode);

            //String[] cmd3 = {"cp", "-r", (System.getProperty("user.dir") + "/cow/assets/."), (System.getProperty("user.dir") + "/unpack/unpacked/assets")};
			Process process33 = Runtime.getRuntime().exec("xcopy /E /Y " + (System.getProperty("user.dir") + "\\cow\\assets\\* " + (System.getProperty("user.dir") + "\\unpack\\unpacked\\assets\\ > nul")));
            //for (String str : cmd3) {
            //    System.out.print(str + " ");
            //}
			System.out.println("xcopy /E /Y " + (System.getProperty("user.dir") + "\\cow\\assets\\* " + (System.getProperty("user.dir") + "\\unpack\\unpacked\\assets\\ > nul")));
            //Process process3 = Runtime.getRuntime().exec(cmd3);
            exitCode = process33.waitFor();
            System.out.println(exitCode);

            String[] cmd333 = {"del", jarFile.getAbsolutePath()};
            for (String str : cmd333) {
                System.out.print(str + " ");
            }
            Process process333 = Runtime.getRuntime().exec(cmd333);
            exitCode = process333.waitFor();
            System.out.println(exitCode);

            String[] cmd4 = {"Compact", "/C", "/S:\"" + (System.getProperty("user.dir") + "\\unpack\\unpacked") + "\"", "/F:\"" + jarFile.getAbsolutePath() + "\""};
            for (String str : cmd4) {
                System.out.print(str + " ");
            }
            Process process4 = Runtime.getRuntime().exec(cmd4);
            exitCode = process4.waitFor();
            System.out.println(exitCode);

		} else {
			String[] cmd1 = {"cp", jarFile.getAbsolutePath(), (System.getProperty("user.dir") + "/unpack/tmp1.zip")}; 
			for (String str : cmd1) {
                System.out.print(str + " ");
        	}
            Process process1 = Runtime.getRuntime().exec(cmd1); 
            int exitCode = process1.waitFor();
			System.out.println(exitCode);

			String[] cmd2 = {"unzip", "-q", "-o", (System.getProperty("user.dir") + "/unpack/tmp1.zip"), "-d", (System.getProperty("user.dir") + "/unpack/unpacked")}; 
			for (String str : cmd2) {
                System.out.print(str + " ");
            }
            Process process2 = Runtime.getRuntime().exec(cmd2); 
            exitCode = process2.waitFor();
			System.out.println(exitCode);

			String[] cmd3 = {"cp", "-r", (System.getProperty("user.dir") + "/cow/assets/."), (System.getProperty("user.dir") + "/unpack/unpacked/assets")}; 
			for (String str : cmd3) {
                System.out.print(str + " ");
            }
            Process process3 = Runtime.getRuntime().exec(cmd3); 
            exitCode = process3.waitFor();
			System.out.println(exitCode);

			String[] cmd4 = {"zip", "-q", "-FS", "-r", jarFile.getAbsolutePath(), (System.getProperty("user.dir") + "/unpack/unpacked")}; 
			for (String str : cmd4) {
                System.out.print(str + " ");
            }
            Process process4 = Runtime.getRuntime().exec(cmd4); 
            exitCode = process4.waitFor();
			System.out.println(exitCode);
		}
		*/

		/*
        try (JarFile originalJar = new JarFile(jarFile);
        JarOutputStream tempJar = new JarOutputStream(new FileOutputStream(tempFile))) {

            // Copy entries from the original JAR to the temporary JAR
            Enumeration<JarEntry> entries = originalJar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                try (InputStream entryStream = originalJar.getInputStream(entry)) {
                    tempJar.putNextEntry(new JarEntry(entry.getName()));
					//tempJar.closeEntry();
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = entryStream.read(buffer)) != -1) {
                        tempJar.write(buffer, 0, bytesRead);
                    }
					tempJar.closeEntry();
                }
            }

            // Add additional files to the temporary JAR
            for (File fileToAdd : filesToAdd) {
				if(fileToAdd != null){ // AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                try (FileInputStream fis = new FileInputStream(fileToAdd)) {
                    tempJar.putNextEntry(new JarEntry(fileToAdd.getName()));//
					//tempJar.closeEntry();
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        tempJar.write(buffer, 0, bytesRead);
                    }
					tempJar.closeEntry();
                }
				}
            }
        }

         Replace the original JAR file with the temporary JAR file
        if (!jarFile.delete() || !tempFile.renameTo(jarFile)) {
            throw new IOException("Failed to update JAR file");
        }
		*/


		// I clearly failed. stackoverflow copy and paste time
		// unpack jar
		String destDir = ((System.getProperty("user.dir") + "/unpack"));
		java.util.jar.JarFile jar = new java.util.jar.JarFile(jarFile);
		java.util.Enumeration enumEntries = jar.entries();
		while (enumEntries.hasMoreElements()) {
		    java.util.jar.JarEntry file = (java.util.jar.JarEntry) enumEntries.nextElement();
		    java.io.File f = new java.io.File(destDir + java.io.File.separator + file.getName());
		    if (file.isDirectory()) { // if its a directory, create it
		        f.mkdir();
		        continue;
		    }
		    java.io.InputStream is = jar.getInputStream(file); // get the input stream
		    java.io.FileOutputStream fos = new java.io.FileOutputStream(f);
		    while (is.available() > 0) {  // write contents of 'is' to 'fos'
		        fos.write(is.read());
		    }
		    fos.close();
		    is.close();
		}
		jar.close();

		deleteThingies();

		// copy files, recursively. who tf invented java this is the most liberal way to do this ever
		copyFolder(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether"), Paths.get(System.getProperty("user.dir") + "/unpack/assets/aether"));
		copyFolder(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy"), Paths.get(System.getProperty("user.dir") + "/unpack/assets/aether_legacy"));

		compress((System.getProperty("user.dir") + "/unpack"));

		// I wrote all the file BS in java so it would "just work" on windows. JAVA. and still
		// it. does. not. work. on. windows. shelling out to the system, I should have never left you
		if(System.getProperty("os.name").toLowerCase().contains("windows")) {
			//ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "xcopy", "/Y", "/F", (System.getProperty("user.dir") + "/mods/temptemptemp.zip"), (jarFile.getAbsolutePath()));
			//ProcessBuilder processBuilder = new ProcessBuilder("C:\\Windows\\System32\\cmd.exe", "/c", "xcopy", "/Y", "/F", (System.getProperty("user.dir") + "/mods/temptemptemp.zip"), (jarFile.getAbsolutePath()));
			//Process process = processBuilder.start();
			//int exitCode = process.waitFor();
			//System.out.println(exitCode);
			Files.move(Paths.get(System.getProperty("user.dir") + "/mods/temptemptemp.zip"), Paths.get(System.getProperty("user.dir") + "/resourcepacks/AetherDepartureAssets.zip"), StandardCopyOption.REPLACE_EXISTING);
		} else {
			Files.move(Paths.get(System.getProperty("user.dir") + "/mods/temptemptemp.zip"), Paths.get(jarFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
		}
		// also, maybe instead of checking if we're on windows we should check if the OS uses retard locks

		deleteDirectory(Paths.get((System.getProperty("user.dir") + "/unpack/")));
		deleteDirectory(Paths.get((System.getProperty("user.dir") + "/cow/")));
    }

	public static void listFilesRecursively(File directory, List<File> fileList) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // If it's a directory, recursively call listFilesRecursively
                    listFilesRecursively(file, fileList);
                } else {
                    // If it's a file, add it to the list
                    fileList.add(file);
                }
            }
        }
    }

	public  static void copyFolder(Path src, Path dest) throws IOException {
	    try (Stream<Path> stream = Files.walk(src)) {
	        stream.forEach(source -> {
	            try {
	                Path destination = dest.resolve(src.relativize(source));
	                if (Files.isDirectory(source)) {
	                    if (!Files.exists(destination)) {
	                        Files.createDirectories(destination);
	                    }
	                } else {
	                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
	                }
	            } catch (IOException e) {
	                throw new RuntimeException("Error copying file: " + e.getMessage(), e);
	            }
	        });
	    }
	}
	/*
	public  static void copyFolder(Path src, Path dest) throws IOException {
    	try (Stream<Path> stream = Files.walk(src)) {
	        stream.forEach(source -> copy(source, dest.resolve(src.relativize(source))));
	    }
	}
	*/

	private static void copy(Path source, Path dest) {
	    try {
	        Files.copy(source, dest, REPLACE_EXISTING);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage(), e);
	    }
	}

	public static void compress(String dirPath) {
        final Path sourceDir = Paths.get(dirPath);
        //String zipFileName = dirPath.concat(".zip");
        String zipFileName = (System.getProperty("user.dir") + "/mods/temptemptemp.zip");
        try {
            final ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(zipFileName));
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                    try {
                        Path targetFile = sourceDir.relativize(file);
                        outputStream.putNextEntry(new ZipEntry(targetFile.toString()));
                        byte[] bytes = Files.readAllBytes(file);
                        outputStream.write(bytes, 0, bytes.length);
                        outputStream.closeEntry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void deleteThingies() throws IOException {
		try{
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether/lang/en_US.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/en_US.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/texts/en_US.txt")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether/lang/zh_CN.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether/lang/es_ES.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/es_MX.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/ja_JP.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/ru_RU.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/zh_CN.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/de_DE.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/nl_NL.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/uk_UA.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/zh_TW.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/ro_RO.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/ko_KR.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/pl_PL.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/fr_FR.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/sv_SE.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/th_TH.lang")));
		Files.delete(Paths.get((System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/it_IT.lang")));
		} catch (Exception e) { System.out.println("Failed to delete the file: " + e.getMessage()); }
	}

	private static void deleteDirectoryMaybeBadIdk(Path directory) throws IOException {
        // Check if the path exists and is a directory
        if (Files.exists(directory) && Files.isDirectory(directory)) {
            // List all files and subdirectories within the directory
            Files.list(directory).forEach(subPath -> {
                try {
                    // If subpath is a directory, recursively delete it
                    if (Files.isDirectory(subPath)) {
                        deleteDirectory(subPath);
                    } else {
                        // Delete the file
                        Files.delete(subPath);
                    }
                } catch (IOException e) {
                    System.err.println("Failed to delete " + subPath + ": " + e.getMessage());
                }
            });

            // Delete the empty directory
            Files.delete(directory);
        }
    }

	private static void deleteDirectory(Path directory) throws IOException {
	    if (Files.exists(directory) && Files.isDirectory(directory)) {
	        // List all files and subdirectories within the directory
	        Files.list(directory)
	             // Exclude "." and ".." directories
	             .filter(path -> !path.getFileName().toString().equals(".") &&
	                              !path.getFileName().toString().equals(".."))
	             .forEach(subPath -> {
	            try {
	                // If subpath is a directory, recursively delete it
	                if (Files.isDirectory(subPath)) {
	                    deleteDirectory(subPath);
	                } else {
	                    // Delete the file
	                    Files.delete(subPath);
	                }
	            } catch (IOException e) {
	                System.err.println("Failed to delete " + subPath + ": " + e.getMessage());
	            }
    	    });

	        // Delete the empty directory
	        Files.delete(directory);
    	}
	}
}
