package lab3.task1.service;

import lab3.task1.model.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.*;

public class FileManagement {
    private Map<String, File> fileSystem;
        
    public FileManagement() {
        this.fileSystem = new HashMap<String, File>();
    }

    private String correctPath(String location) {
        if (location.charAt(0) != '/') location = "/" + location;
        if (location.charAt(location.length() - 1) != '/') location += "/";
        return location;
    }

    private boolean promptOverwrite(String filePath) {
        Scanner sc = new Scanner(System.in);

        System.out.println("File \"" + filePath + "\" already exists. Would you like to overwrite? [Y/n]");
        if (sc.nextLine().startsWith("n")) {
            return false;
        }
        System.out.println("File \"" + filePath + "\" overwritten.");
        return true;
    }    

    private void createFile(String name, String location, List<String> args) {
        File newFile = null;

        if (args != null && !args.isEmpty() && args.get(0).equals("CONTENT")) {
            String content = String.join(" ", args.subList(1, args.size()));
            if (name.endsWith(".avi") || name.endsWith(".mp3")) {
                newFile = new MediaContentFile(name, location, content);
            }
            else {
                newFile = new DocumentContentFile(name, location, content);
            }
        }
        else {
            newFile = new ExecutableFile(name, location, args);
        }

        String filePath = newFile.getPath();
        File f = this.fileSystem.get(filePath);

        if (f != null && !f.isDeleted() && !this.promptOverwrite(filePath)) {
            return;
        }

        this.fileSystem.put(filePath, newFile);
    }

    private void moveFile(File f, List<String> args) {
        if (f == null || f.isDeleted()) {
            System.out.println("File \"" + args.get(1) + "\" does not exist.");
        }
        else {
            String loc = correctPath(args.get(2));
            File o = this.fileSystem.get(loc + f.getName());
            System.out.println(loc);

            if (o != null && !o.isDeleted() && !this.promptOverwrite(loc + f.getName())) {
                return;
            }

            this.fileSystem.remove(f.getPath());
            f.move(loc);
            this.fileSystem.put(f.getPath(), f);
        }
    }

    private void modifyFile(File f, List<String> args) {
        if (f == null || f.isDeleted()) {
            System.out.println("File \"" + args.get(1) + "\" does not exist.");
        }
        else {
            ((ContentFile)f).modify((args.size() > 2 ? String.join(" ", args.subList(2, args.size())) : null));
        }
    }

    private void copyFile(File f, List<String> args) {
        if (f == null || f.isDeleted()) {
            System.out.println("File \"" + args.get(1) + "\" does not exist.");
        }
        else {
            File copy = f.copy(args.get(2));
            this.moveFile(copy, args);
        }
    }

    private void deleteFile(File f, List<String> args) {
        if (f == null || f.isDeleted()) {
            System.out.println("File \"" + args.get(1) + "\" does not exist.");
        }
        else {
            f.delete();
        }
    }

    private void executeFile(File f, List<String> args) {
        if (f == null || f.isDeleted()) {
            System.out.println("File \"" + args.get(1) + "\" does not exist.");
        }
        else {
            ((ExecutableFile)f).execute();
        }
    }

    private void printFileInfo(File f, List<String> args) {
        if (f == null) {
            System.out.println("File \"" + args.get(1) + "\" does not exist.");
        }
        else {
            System.out.println(f.getInfo());
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            String line = sc.nextLine();
            List<String> args = new ArrayList<String>();
            Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(line);

            while (m.find()) {
                args.add(m.group(1).replace("\"", ""));
            }

            try { 
                File f = null;
                if (args.size() > 1) {
                    f = this.fileSystem.getOrDefault((args.get(1).charAt(0) == '/' ? args.get(1) : "/" + args.get(1)), null);
                }

                switch (args.get(0).toUpperCase()) {
                    case "END":
                        return;
                    case "MAKE":
                        this.createFile(args.get(1), args.get(2), args.subList(3, args.size()));
                        break;
                    case "MOVE":
                        this.moveFile(f, args);
                        break;
                    case "MOD":
                        this.modifyFile(f, args);
                        break;
                    case "COPY":
                        this.copyFile(f, args);
                        break;
                    case "DEL":
                        this.deleteFile(f, args);
                        break;
                    case "EXEC":
                        this.executeFile(f, args);
                        break;
                    case "INFO":
                        this.printFileInfo(f, args);
                        break;
                }
            }
            catch (Exception e) {
                System.out.println("ERROR: Invalid operation!");
            }
        }
    }
}
