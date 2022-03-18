package lab3.task1.service;

import lab3.task1.model.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class FileManagement {
    private Map<String, File> fileSystem;
        
    public FileManagement() {
        this.fileSystem = new HashMap<String, File>();
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

        String filePath = newFile.getLocation() + newFile.getName();
        File f = this.fileSystem.get(filePath);

        if (f != null && !f.isDeleted()) {
            Scanner sc = new Scanner(System.in);

            System.out.println("File \"" + filePath + "\" already exists. Would you like to overwrite? [Y/n]");
            if (sc.nextLine().startsWith("n")) {
                return;
            }
            System.out.println("File \"" + filePath + "\" overwritten.");
        }

        this.fileSystem.put(filePath, newFile);
    }

    private void moveFile(String path, String location) {

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
                File f = (args.size() > 1 ? this.fileSystem.get(args.get(1)) : null);

                switch (args.get(0)) {
                    case "END":
                        return;
                    case "MAKE":
                        this.createFile(args.get(1), args.get(2), args.subList(3, args.size()));
                        break;
                    case "MOVE":
                        break;
                    case "MOD":
                        if (f == null) {
                            System.out.println("File \"" + args.get(1) + "\" does not exist.");
                        }
                        else {
                            ((ContentFile)f).modify((args.size() > 2 ? args.get(2) : null));
                        };
                        break;
                    case "COPY":
                        break;
                    case "DEL":
                        if (f == null) {
                            System.out.println("File \"" + args.get(1) + "\" does not exist.");
                        }
                        else {
                            f.delete();
                        };
                        break;
                    case "EXEC":
                        if (f == null) {
                            System.out.println("File \"" + args.get(1) + "\" does not exist.");
                        }
                        else {
                            ((ExecutableFile)f).execute();
                        };
                        break;
                    case "INFO":
                        if (f == null) {
                            System.out.println("File \"" + args.get(1) + "\" does not exist.");
                        }
                        else {
                            System.out.println(f.getInfo());
                        };
                        break;
                }
            }
            catch (Exception e) {
                System.out.println("ERROR: Invalid operation!");
            }
        }
    }
}
