package hu.javaproject.processes.Data;
//import io.swagger.v3.oas.annotiations.meda.Schema;

public class Process {
    private int ID;
    private String description;
    private int duration_in_minutes;

//    @Schema(description = "Folyamat")
    public Process(){}

    public Process Description(String value){
        this.description = value;
        return this;
    }

    public Process Duration(int value){
        this.duration_in_minutes = value;
        return this;
    }

    public Process ID(int value){
        this.ID = value;
        return this;
    }

    public int GetID(){
        return ID;
    }

    public String GetDescription(){
        return description;
    }

    public int GetDuration(){
        return duration_in_minutes;
    }
}
