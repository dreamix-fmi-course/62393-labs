package lab3.task1.model;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class ExecutableFile extends File {
    private List<String> requiredResources;
    private LocalDate lastExecutionDate;

    public ExecutableFile(String name, String location, List<String> requiredResources) {
        super(name, location);
        this.requiredResources = (requiredResources == null ? new ArrayList<String>() : new ArrayList<String>(requiredResources));
        this.lastExecutionDate = null;
    }

    public ExecutableFile(ExecutableFile other) {
        this(other.name, other.location, other.requiredResources);
    }

    @Override
    public File copy(String newLocation) {
        File copy = new ExecutableFile(this); 
        copy.move(newLocation);
        return copy;
    }

    public void execute() {
        this.lastExecutionDate = LocalDate.now();
        System.out.println("Executing \"" + this.location + this.name + "\"");
    }

    @Override
    public String getInfo() {
        String info = "Name: \"" + this.location + this.name + "\"" + (this.isDeleted ? " DELETED" : "") + "\n"
            + "Creation date: " + this.creationDate + "\n"
            + "Last execution date: " + this.lastExecutionDate + "\n"
            + "Required resources:";
        for (var r : this.requiredResources) { 
            info += "\n" + r.toString(); 
        }
        return info;
    }
}
