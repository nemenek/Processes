package hu.javaproject.processes.Rest;

import hu.javaproject.processes.Store.ProcessRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import hu.javaproject.processes.Data.Process;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping(path = "/process")
public class ProcessesController {
    @Autowired
    private ProcessRepository service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sikeres lekérdezés",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Process.class))) }),
    })
    @Operation(summary = "Folyamatok lekérdezése")
    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Process> getProcesses(){
        return service.GetAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A keresett folyamat",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Process.class)) }),
            @ApiResponse(responseCode = "500", description = "Sikertelen lekérdezés",
                    content = { @Content(mediaType = "application/json")})
    })
    @Operation(summary = "Folyamat lekérdezése ID alapján")
    @GetMapping(path = "/getbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Process getProcessById(@Parameter(description = "id", example = "0", required = true)
                                              @PathVariable(name = "id", required = true) int ID){
        return service.GetProcessByID(ID);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A keresett folyamat",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Process.class)) }),
            @ApiResponse(responseCode = "500", description = "Sikertelen lekérdezés",
                    content = { @Content(mediaType = "application/json")})
    })
    @Operation(summary = "Folyamat lekérdezése leírás alapján")
    @GetMapping(path = "/getbydescription/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Process getProcessByDescription(@Parameter(description = "description", example = "", required = true)
                                                       @PathVariable(name = "description", required = true) String Description){
        return service.GetByDescription(Description);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A keresett folyamat",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Process.class))) }),
            @ApiResponse(responseCode = "500", description = "Sikertelen lekérdezés",
                    content = { @Content(mediaType = "application/json")})
    })
    @Operation(summary = "Folyamat lekérdezése időtartam alapján")
    @GetMapping(path = "/getbyduration/{duration}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Process> getByDuration(@Parameter(description = "duration", example = "0", required = true)
                                                   @PathVariable(name = "duration", required = true) int Duration){
        return service.GetByDuration(Duration);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sikeres felvitel",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Process.class)) }),
            @ApiResponse(responseCode = "500", description = "Folyamat már létezik",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(summary = "Folyamat hozzáadása")
    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public void Add(@Parameter(description = "Az új folyamat",required = true) @RequestBody(required = true) Process process){
        service.add(process);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Nincs ilyen folyamat",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(summary = "Folyamat törlése")
    @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean Delete(@Parameter(description = "A törölni kívánt folyamat",required = true) @RequestBody(required = true) Process process){
        return service.delete(process);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sikeres művelet",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Nincs ilyen folyamat",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(summary = "Folyamat törlése")
    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteById(@Parameter(description = "A törölni kívánt folyamat azonosítója",example = "1", required = true)
                                          @PathVariable(name = "id", required = true) int id){
        return service.delete(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sikeres módosítás",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Process.class)) }),
            @ApiResponse(responseCode = "500", description = "Folyamat nem létezik",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(summary = "Folyamat módosítása")
    @PutMapping(path = "/modify", produces = MediaType.APPLICATION_JSON_VALUE)
    public Process modify(
            @Parameter(description = "Folyamat", required = true)
            @RequestBody(required = true) Process pTable) throws IllegalAccessException, InvocationTargetException {
        service.modify(pTable);
        return pTable;
    }
}
