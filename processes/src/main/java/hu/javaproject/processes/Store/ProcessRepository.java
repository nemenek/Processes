package hu.javaproject.processes.Store;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import hu.javaproject.processes.Data.Process;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcessRepository {
    private static final List<Process> processes = new ArrayList<>();

    @PostConstruct
    public void  init(){
        processes.add(
                new Process().ID(0).Description("A1").Duration(15)
        );
    }

    public List<Process> GetAll(){
        return this.processes;
    }

    public Process GetProcessByID(int Id){
        Optional<Process> tmp = getOptionalById(Id);
        if(tmp.isEmpty()){
            throw new RuntimeException("No such id is present in store: " + Id);
        }
        return tmp.get();
    }

    public Optional<Process> getOptionalById(int Id){
        Optional<Process> res =processes.stream().filter(element -> element.GetID() == Id).findFirst();
        return res;
    }

    public Process GetByDescription(String description){
        return processes.stream().filter(element -> element.GetDescription() == description).findFirst().get();
    }

    public List<Process> GetByDuration(int time){
        return processes.stream().filter(element -> element.GetDuration() == time).collect(Collectors.toList());
    }

    public void add(Process process){
        if(getOptionalById(process.GetID()).isEmpty()){
            ProcessRepository.processes.add(process);
        }
        else throw new RuntimeException("ID is already present: " + process.GetID());
    }

    public boolean delete(Process process){
        return ProcessRepository.processes.remove(process);
    }

    public boolean delete(int Id){
        Optional<Process> tmp = getOptionalById(Id);
        if(tmp.isEmpty()){
            return false;
        }
        else return ProcessRepository.processes.remove(tmp.get());
    }

    public void modify(Process process){
        Optional<Process> tmp = getOptionalById(process.GetID());
        if(!tmp.isEmpty()){
            BeanUtils.copyProperties(tmp.get(), process);
        }
    }
}
