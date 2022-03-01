package hu.javaproject.processes.Rest;

import hu.javaproject.processes.Store.ProcessRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hu.javaproject.processes.Data.Process;

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
}
