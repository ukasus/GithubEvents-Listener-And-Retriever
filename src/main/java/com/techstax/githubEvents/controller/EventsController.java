package com.techstax.githubEvents.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class EventsController {

    @Value("${github.access.token}")
    String token;

    @Value("${github.repo.name}")
    String repoName;

    @Value("${github.repo.owner}")
    String owner;

    @PostMapping(value = "/events", consumes = "application/json")
    String events(HttpServletRequest request)  {
       try {
            List<String> data = request.getReader().lines().collect(Collectors.toList());
            JsonNode node = new ObjectMapper().readTree(data.get(0));
            System.out.println(node.toPrettyString());
            return "Message received";
        }catch (Exception e){
           return e.getMessage();
       }

    }

    @RequestMapping("/fetchEvent")
    public String fetchEvent(){
        try{
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/vnd.github+json");
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);
            headers.set("X-GitHub-Api-Version", "2022-11-28");

            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<JsonNode> respEntity = restTemplate.exchange("https://api.github.com/repos/" + owner + "/" + repoName + "/commits", HttpMethod.GET, entity, JsonNode.class);
            JsonNode node = respEntity.getBody();
            System.out.println(node.toPrettyString());
            return node.toPrettyString();
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

}
