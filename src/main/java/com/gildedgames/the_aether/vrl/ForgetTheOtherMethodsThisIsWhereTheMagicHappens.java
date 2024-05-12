package com.gildedgames.the_aether.vrl;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import java.net.URISyntaxException;

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

	// TODO delete all subdirs but "assets" and "data"
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

	public String getJarName() {
		try {
            String jarFilePath = ForgetTheOtherMethodsThisIsWhereTheMagicHappens.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            String jarFileName = new File(jarFilePath).getName();
            System.out.println("JAR file name: " + jarFileName);
			return jarFileName;
        } catch (URISyntaxException e) {
            e.printStackTrace();
			return null;
        }
	}
}

