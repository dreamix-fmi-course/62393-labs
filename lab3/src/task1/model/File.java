package lab3.task1.model;

import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.IllegalArgumentException;

public abstract class File {
    protected String name;
    protected String location;
    protected LocalDate creationDate;
    protected boolean isDeleted;

    public File(String name, String location) {
        this.setName(name);
        this.setLocation(location);
        this.creationDate = LocalDate.now();
        this.isDeleted = false;
    }

    public String getName() { return this.name; }
    public String getLocation() { return this.location; }
    public LocalDate getCreationDate() { return this.creationDate; }
    public boolean isDeleted() { return this.isDeleted; }
    public String getPath() { return this.location + this.name; }

    private void setName(String newName) throws IllegalArgumentException {
        if (newName == null || newName.trim().isEmpty() || !newName.matches("^[\\w\\-.]+$") || newName.contains("/")) { 
            throw new IllegalArgumentException("Invalid file name \"" + newName + "\"");
        }
        this.name = newName;
    }

    private void setLocation(String newLocation) throws IllegalArgumentException {
        if (newLocation == null || !newLocation.matches("^/([\\w\\-.]+/?)*$")) { 
            throw new IllegalArgumentException("Invalid path \"" + newLocation + "\""); 
        }
        this.location = (newLocation.charAt(newLocation.length() - 1) == '/' ? newLocation : newLocation + "/");
    }

    public File(File other) {
        this(other.name, other.location);
    }

    public void move(String newLocation) {
        this.location = newLocation;
    }
    
    public void delete() {
        this.isDeleted = true;
    }

    public abstract File copy(String newLocation);

    public abstract String getInfo();

    @Override
    public String toString() {
        return "File {" +
            "name=\"" + this.name + "\", " +
            "location=\"" + this.location + "\", " +
            "creationDate=\"" + this.creationDate + "\", " +
            "isDeleted=" + this.isDeleted +
        "}";
    }
}
