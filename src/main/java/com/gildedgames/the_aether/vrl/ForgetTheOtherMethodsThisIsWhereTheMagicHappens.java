package com.gildedgames.the_aether.vrl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ForgetTheOtherMethodsThisIsWhereTheMagicHappens {

    private static final String RESOURCE_PACK_NAME = "aether_legacy";
    private static final String AETHER_LEGACY_NAMESPACE = "aether_legacy";

    public static boolean download(String urlstr) {
        try {
            String fileURL = urlstr;
            String saveDir = System.getProperty("user.dir") + "/cow";

            File directory = new File(saveDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);

            URL url = new URL(fileURL);
            URLConnection conn = url.openConnection();

            InputStream inputStream = conn.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            File outputFile = new File(saveDir + File.separator + fileName);
            OutputStream outputStream = new FileOutputStream(outputFile);

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            bufferedInputStream.close();

            System.out.println("File downloaded successfully to: " + outputFile.getAbsolutePath());

            unpackJar(outputFile, saveDir);

            pruneAetherLegacyAssetsDirectory(Paths.get(System.getProperty("user.dir") + "/cow/assets/" + AETHER_LEGACY_NAMESPACE));

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void unpackJar(File jarFile, String outputDir) throws IOException {
        Path outputPath = Paths.get(outputDir).toAbsolutePath().normalize();

        try (JarInputStream jarInputStream = new JarInputStream(new FileInputStream(jarFile))) {
            JarEntry entry;

            while ((entry = jarInputStream.getNextJarEntry()) != null) {
                String entryName = entry.getName().replace('\\', '/');

                Path entryPath = outputPath.resolve(entryName).normalize();

                if (!entryPath.startsWith(outputPath)) {
                    throw new IOException("Blocked unsafe jar entry: " + entryName);
                }

                if (entry.isDirectory()) {
                    Files.createDirectories(entryPath);
                    continue;
                }

                Path parent = entryPath.getParent();

                if (parent != null && !Files.exists(parent)) {
                    Files.createDirectories(parent);
                }

                try (OutputStream outputStream = Files.newOutputStream(entryPath)) {
                    byte[] buffer = new byte[8192];
                    int bytesRead;

                    while ((bytesRead = jarInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }
        }
    }

    public static File getJarName() {
        String modFolderStr = System.getProperty("user.dir") + "/mods";
        String cowFolderStr = System.getProperty("user.dir") + "/cow";
        String aaaFolderStr = System.getProperty("user.dir") + "/unpack";

        File modFolder = new File(modFolderStr);
        File cowFolder = new File(cowFolderStr);
        File aaaFolder = new File(aaaFolderStr);

        if (!modFolder.exists()) {
            modFolder.mkdirs();
        }

        if (!cowFolder.exists()) {
            cowFolder.mkdirs();
        }

        if (!aaaFolder.exists()) {
            aaaFolder.mkdirs();
        }

        File[] modFiles = modFolder.listFiles();

        if (modFiles != null) {
            for (File modFile : modFiles) {
                if (modFile != null) {
                    String name = modFile.getName().toLowerCase();

                    if ((name.contains("aether") && name.contains("eparture")) || (name.contains("aether") && name.contains("dirty"))) {
                        System.out.println("Detected Aether Departure jar name: " + modFile);
                        return modFile;
                    }
                }
            }
        }

        if (modFiles != null) {
            for (File modFile : modFiles) {
                if (modFile != null) {
                    String name = modFile.getName().toLowerCase();

                    if (name.contains("aether") || name.contains("departure")) {
                        System.out.println("Detected Aether Departure jar name: " + modFile);
                        return modFile;
                    }
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
        String cowFolderStr = System.getProperty("user.dir") + "/cow";
        File cowFolder = new File(cowFolderStr);

        if (!cowFolder.exists()) {
            cowFolder.mkdirs();
        }

        /*
         * Unpack current mod jar into /unpack.
         */
        String destDir = System.getProperty("user.dir") + "/unpack";
        Path unpackPath = Paths.get(destDir);

        if (!Files.exists(unpackPath)) {
            Files.createDirectories(unpackPath);
        }

        JarFile jar = new JarFile(jarFile);
        Enumeration<JarEntry> enumEntries = jar.entries();

        while (enumEntries.hasMoreElements()) {
            JarEntry file = enumEntries.nextElement();

            Path outputPath = unpackPath.resolve(file.getName()).normalize();

            if (!outputPath.startsWith(unpackPath.toAbsolutePath().normalize())
                && !outputPath.startsWith(unpackPath.normalize())) {
                jar.close();
                throw new IOException("Blocked unsafe jar entry while unpacking mod jar: " + file.getName());
            }

            if (file.isDirectory()) {
                Files.createDirectories(outputPath);
                continue;
            }

            Path parent = outputPath.getParent();

            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }

            InputStream is = jar.getInputStream(file);
            FileOutputStream fos = new FileOutputStream(outputPath.toFile());

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            fos.close();
            is.close();
        }

        jar.close();

        pruneAetherLegacyAssetsDirectory(Paths.get(System.getProperty("user.dir") + "/cow/assets/" + AETHER_LEGACY_NAMESPACE));

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            Path packFolder = Paths.get(System.getProperty("user.dir"), "resourcepacks", RESOURCE_PACK_NAME);

            if (!Files.exists(packFolder)) {
                Files.createDirectories(packFolder);
            }

            Path sourceAetherLegacy = Paths.get(System.getProperty("user.dir"), "cow", "assets", AETHER_LEGACY_NAMESPACE);

            Path targetAetherLegacy = Paths.get(System.getProperty("user.dir"), "resourcepacks", RESOURCE_PACK_NAME, "assets", AETHER_LEGACY_NAMESPACE);

            Path sourceAetherII = Paths.get(System.getProperty("user.dir"), "cow", "assets", "aetherii");

            Path targetAetherII = Paths.get(System.getProperty("user.dir"), "resourcepacks", RESOURCE_PACK_NAME, "assets", "aetherii");

            copyFolderIfExists(sourceAetherLegacy, targetAetherLegacy);
            copyFolderIfExists(sourceAetherII, targetAetherII);

            pruneAetherLegacyAssetsDirectory(targetAetherLegacy);

            Path packMcmetaSource = Paths.get(System.getProperty("user.dir"), "unpack", "pack.mcmeta");
            Path packPngSource = Paths.get(System.getProperty("user.dir"), "unpack", "pack.png");

            Path packMcmetaTarget = packFolder.resolve("pack.mcmeta");
            Path packPngTarget = packFolder.resolve("pack.png");

            if (Files.exists(packMcmetaSource)) {
                Files.copy(packMcmetaSource, packMcmetaTarget, StandardCopyOption.REPLACE_EXISTING);
            } else {
                writeDefaultPackMcmeta(packMcmetaTarget);
            }

            if (Files.exists(packPngSource)) {
                Files.copy(packPngSource, packPngTarget, StandardCopyOption.REPLACE_EXISTING);
            }

            System.out.println("Created/updated resource pack: " + packFolder.toAbsolutePath());
            System.out.println("winblows");
        } else {
            Path sourceAetherLegacy = Paths.get(System.getProperty("user.dir"), "cow", "assets", AETHER_LEGACY_NAMESPACE);
            Path targetAetherLegacy = Paths.get(System.getProperty("user.dir"), "unpack", "assets", AETHER_LEGACY_NAMESPACE);

            Path sourceAetherII = Paths.get(System.getProperty("user.dir"), "cow", "assets", "aetherii");
            Path targetAetherII = Paths.get(System.getProperty("user.dir"), "unpack", "assets", "aetherii");

            copyFolderIfExists(sourceAetherLegacy, targetAetherLegacy);
            copyFolderIfExists(sourceAetherII, targetAetherII);

            pruneAetherLegacyAssetsDirectory(targetAetherLegacy);

            compress(System.getProperty("user.dir") + "/unpack");

            Files.move(Paths.get(System.getProperty("user.dir") + "/mods/temptemptemp.zip"), Paths.get(jarFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
        }

        deleteDirectory(Paths.get(System.getProperty("user.dir") + "/unpack/"));
        deleteDirectory(Paths.get(System.getProperty("user.dir") + "/cow/"));
    }

    private static void pruneAetherLegacyAssetsDirectory(Path aetherLegacyAssetsDir) throws IOException {
        if (aetherLegacyAssetsDir == null) {
            return;
        }

        if (!Files.exists(aetherLegacyAssetsDir) || !Files.isDirectory(aetherLegacyAssetsDir)) {
            return;
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(aetherLegacyAssetsDir)) {
            for (Path child : stream) {
                String name = child.getFileName().toString();

                boolean allowed = name.equals("lang") || name.equals("sounds") || name.equals("textures") || name.equals("sounds.json");

                if (allowed) {
                    continue;
                }

                if (Files.isDirectory(child)) {
                    deleteDirectory(child);
                } else {
                    Files.deleteIfExists(child);
                }
            }
        }
    }

    private static void writeDefaultPackMcmeta(Path target) throws IOException {
        Path parent = target.getParent();

        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }

        String packMcmeta = "{\n" + "  \"pack\": {\n" + "    \"pack_format\": 1,\n" + "    \"description\": \"Aether Legacy assets\"\n" + "  }\n" + "}\n";

        Files.write(target, packMcmeta.getBytes("UTF-8"));
    }

    public static void listFilesRecursively(File directory, List<File> fileList) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFilesRecursively(file, fileList);
                } else {
                    fileList.add(file);
                }
            }
        }
    }

    public static void copyFolderIfExists(Path src, Path dest) throws IOException {
        if (src == null || !Files.exists(src)) {
            return;
        }

        copyFolder(src, dest);
    }

    public static void copyFolder(Path src, Path dest) throws IOException {
        if (src == null || dest == null) {
            return;
        }

        if (!Files.exists(src)) {
            return;
        }

        final Path normalizedDest = dest.toAbsolutePath().normalize();

        try (Stream<Path> stream = Files.walk(src)) {
            Iterator<Path> iterator = stream.iterator();

            while (iterator.hasNext()) {
                Path source = iterator.next();

                try {
                    Path destination = dest.resolve(src.relativize(source)).normalize();

                    if (!destination.toAbsolutePath().normalize().startsWith(normalizedDest)) {
                        throw new IOException("Blocked unsafe copy destination: " + destination);
                    }

                    if (Files.isDirectory(source)) {
                        if (!Files.exists(destination)) {
                            Files.createDirectories(destination);
                        }
                    } else {
                        Path parent = destination.getParent();

                        if (parent != null && !Files.exists(parent)) {
                            Files.createDirectories(parent);
                        }

                        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Error copying file: " + e.getMessage(), e);
                }
            }
        }
    }

    private static void copy(Path source, Path dest) {
        try {
            Files.copy(source, dest, REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void compress(String dirPath) {
        final Path sourceDir = Paths.get(dirPath);
        String zipFileName = System.getProperty("user.dir") + "/mods/temptemptemp.zip";

        try {
            final ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(zipFileName));

            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                    try {
                        Path targetFile = sourceDir.relativize(file);
                        String zipEntryName = targetFile.toString().replace('\\', '/');

                        outputStream.putNextEntry(new ZipEntry(zipEntryName));

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
        try {
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether/lang/en_US.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/en_US.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/texts/en_US.txt"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether/lang/zh_CN.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether/lang/es_ES.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/es_MX.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/ja_JP.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/ru_RU.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/zh_CN.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/de_DE.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/nl_NL.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/uk_UA.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/zh_TW.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/ro_RO.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/ko_KR.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/pl_PL.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/fr_FR.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/sv_SE.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/th_TH.lang"));
            Files.delete(Paths.get(System.getProperty("user.dir") + "/cow/assets/aether_legacy/lang/it_IT.lang"));
        } catch (Exception e) {
            System.out.println("Failed to delete the file: " + e.getMessage());
        }
    }

    private static void deleteDirectoryMaybeBadIdk(Path directory) throws IOException {
        deleteDirectory(directory);
    }

    private static void deleteDirectory(Path directory) throws IOException {
        if (directory == null) {
            return;
        }

        if (!Files.exists(directory)) {
            return;
        }

        if (!Files.isDirectory(directory)) {
            Files.deleteIfExists(directory);
            return;
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path subPath : stream) {
                if (Files.isDirectory(subPath)) {
                    deleteDirectory(subPath);
                } else {
                    try {
                        Files.deleteIfExists(subPath);
                    } catch (IOException e) {
                        System.err.println("Failed to delete " + subPath + ": " + e.getMessage());
                    }
                }
            }
        }

        try {
            Files.deleteIfExists(directory);
        } catch (IOException e) {
            System.err.println("Failed to delete " + directory + ": " + e.getMessage());
        }
    }

    public static void renameThingy() {
        File oldFolder = new File(System.getProperty("user.dir") + "/cow/assets/aether/");
        File newFolder = new File(System.getProperty("user.dir") + "/cow/assets/aetherii/");
        oldFolder.renameTo(newFolder);
    }
}
