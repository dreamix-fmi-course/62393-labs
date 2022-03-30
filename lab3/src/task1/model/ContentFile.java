package lab3.task1.model;

import java.util.List;
import java.time.LocalDate;

public class ContentFile extends File {
    protected String content;
    protected LocalDate lastModiciationDate;

    public ContentFile(String name, String location, String content) {
        super(name, location);
        this.content = content;
        this.lastModiciationDate = LocalDate.now();
    }

    public ContentFile(ContentFile other) {
        this(other.name, other.location, other.content);
    }

    @Override
    public File copy(String newLocation) {
        File copy = new ContentFile(this);
        copy.move(newLocation);
        return null;
    }

    public void modify(String newContent) {
        this.lastModiciationDate = LocalDate.now();
        this.content = newContent;
    }

    @Override
    public String getInfo() {
        return "Name: \"" + this.getPath() + "\"" + (this.isDeleted ? " DELETED" : "") + "\n" 
            + "Creation date: " + this.creationDate + "\n"
            + "Last modification date: " + this.lastModiciationDate;
    }
}
