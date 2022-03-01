package hu.javaproject.processes.Data;
//import io.swagger.v3.oas.annotiations.meda.Schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@Schema(description = "Folyamat")
public class Process {
    @Schema(description = "Folyamat azonosító")
    private int ID;
    @Schema(description = "Folyamat leírása")
    private String description;
    @Schema(description = "Folyamat hossza percekben")
    private int duration_in_minutes;

    public Process(int id, String description, int duration){
        this.ID = id;
        this.description = description;
        this.duration_in_minutes = duration;
    }

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
