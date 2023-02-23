package com.formacion.BS63.controller;

import com.formacion.BS63.model.Greeting;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello   %s!";

    private static final String template2 = "name:   %s!";

    private final AtomicLong counter = new AtomicLong();

    private List<Greeting> greetingList = new ArrayList<Greeting>();

    @GetMapping("/greeting")

    public Greeting greeting(@RequestParam(value ="name", defaultValue = "World")String name){

        return  new Greeting(counter.incrementAndGet(),String.format(template,name));
    }
    @PutMapping("/post")
    public Greeting objectUser(@RequestParam(value = "id") Integer id,@RequestParam(value="name")String name)
    {
        return new Greeting(id,String.format(template2,name));
    }


    @PostMapping("/post")
    public Greeting objectPost(@RequestBody Greeting objectJson ){

        return objectJson;

    }

    @PostMapping("/postList")
    public List<Greeting>  greetingList(@RequestBody Greeting objectJson ){
        greetingList.add(objectJson);
        return greetingList;
    }


    @GetMapping("/user/{id}")
    public Greeting showById(@PathVariable Integer id){
        for(Greeting ListGreeting: greetingList){
            if(ListGreeting.getId()==(id)){
                return ListGreeting;
            }
        }
        return null;
    }

}
