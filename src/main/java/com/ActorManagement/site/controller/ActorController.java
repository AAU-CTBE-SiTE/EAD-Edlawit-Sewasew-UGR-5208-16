package com.ActorManagement.site.controller;
import com.ActorManagement.site.model.Actor;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/actors")
public class ActorController{
    private List<Actor> actors = new ArrayList<>();
    public ActorController(){
        actors.add(new Actor(1, "Girum Ermias", "Lamba"));
        actors.add(new Actor(2, "Netsanet Workneh", "FBI"));
        actors.add(new Actor(3, "Kassahun Fisseha", "Tilefegn"));
        actors.add(new Actor(4, "Solomon Bogale", "Absalat"));
    }
    @GetMapping
    public List<Actor> getAllActors(){
        return actors;
    }
    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable int id){
        for (Actor currentActor : actors){
            if (currentActor.getId() == id){
                return currentActor;
            }
        }
        return null;
    }
    @GetMapping(value = "/iterate", produces = "text/plain")
    public String iterateAllActors(){
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < actors.size()){
            Actor currentActor = actors.get(i);
            result.append(currentActor.output()).append("\n");
            i++;
        }
        return result.toString();
    }
    @PostMapping
    public Actor createActor(@RequestBody Actor newActor){
        actors.add(newActor);
        return newActor;
    }
    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable int id, @RequestBody Actor updatedActor){
        for (Actor currentActor : actors){
            if (currentActor.getId() == id){
                currentActor.setName(updatedActor.getName());
                currentActor.setMovie(updatedActor.getMovie());
                return currentActor;
            }
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public String deleteActor(@PathVariable int id){
        for (int i = 0; i < actors.size(); i++){
            if (actors.get(i).getId() == id){
                actors.remove(i);
                return "Actor with ID " + id + " is deleted";
            }
        }
        return "Actor with ID " + id + " is not found";
    }
}
