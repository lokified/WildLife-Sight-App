import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        staticFileLocation("/public");

        get("/",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            List<EndangeredAnimals> allEndangeredAnimals = EndangeredAnimals.all();
            model.put("enAnimals",allEndangeredAnimals);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        //show new form to add animals
        get("/animal-form",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            return new ModelAndView(model,"animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process form  to add animals
        post("/animal-form/new",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();

            String name = request.queryParams("name");
            Animals newAnimal = new Animals(name);
            newAnimal.save();
            model.put("animals",newAnimal);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        //show form to add endangered animals
        get("/en-animal-form",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            return new ModelAndView(model,"en-animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process form  to add endangered animals
        post("/en-animal-form/new",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();

            String name = request.queryParams("name");
            String health = request.queryParams("health");
            String age = request.queryParams("age");

            EndangeredAnimals newEndangeredAnimal = new EndangeredAnimals(name,health,age);
            newEndangeredAnimal.save();
            model.put("enAnimals",newEndangeredAnimal);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        //show animals
        get("/animals",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            List<Animals> allAnimals = Animals.all();
            model.put("animals",allAnimals);
            List<EndangeredAnimals> allEnAnimals = EndangeredAnimals.all();
            model.put("enAnimals",allEnAnimals);
            return new ModelAndView(model,"animal.hbs");
        }, new HandlebarsTemplateEngine());


        //show form to add sightings
        get("/sighting-form",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            List<Animals> all = Animals.allAnimals();
            model.put("animals",all);
            return new ModelAndView(model,"sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process sighting form
        post("/sighting-form/new",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            int animalId = Integer.parseInt(request.queryParams("animal"));
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");

            Sighting sighting = new Sighting(animalId, location, rangerName);
            sighting.save();
            model.put("sightings",sighting);
            return new ModelAndView(model,"success.hbs");

        }, new HandlebarsTemplateEngine());

        //show sightings
        get("/sightings", (request, response) ->{
            Map<String,Object> model = new HashMap<String, Object>();
            List<Sighting> allSightings = Sighting.all();
            model.put("sightings",allSightings);
            return new ModelAndView(model,"sightings.hbs");
        }, new HandlebarsTemplateEngine() );

        //show form to update animals
        get("/animals/:id/update",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            int idOfAnimal = Integer.parseInt(request.params("id"));

            Animals editAnimal = Animals.find(idOfAnimal);
            model.put("editAnimal",editAnimal);
            return new ModelAndView(model,"animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process form to update
        post("/animals/:id/update",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            String newName = request.queryParams("name");

            int idOfAnimal = Integer.parseInt(request.params(":id"));
            Animals editAnimal = Animals.find(idOfAnimal);
            editAnimal.update(idOfAnimal,newName);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        //deletes an animal
        post("/animals/:id/delete",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            int idOfAnimal = Integer.parseInt(request.params("id"));
            Animals animal = Animals.find(idOfAnimal);
            animal.delete();
            EndangeredAnimals enAnimal = EndangeredAnimals.find(idOfAnimal);
            enAnimal.delete();
            return new ModelAndView(model,"success-del.hbs");
        }, new HandlebarsTemplateEngine());

        //show form to update endangered animals
        get("/enAnimals/:id/update",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            int idOfAnimal = Integer.parseInt(request.params("id"));

            EndangeredAnimals editEnAnimal = EndangeredAnimals.find(idOfAnimal);
            model.put("editEnAnimal",editEnAnimal);
            return new ModelAndView(model,"en-animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process form to update endangered animals
        post("/enAnimals/:id/update",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            String newName = request.queryParams("name");
            String newHealth = request.queryParams("health");
            String newAge = request.queryParams("age");

            int idOfAnimal = Integer.parseInt(request.params(":id"));
            EndangeredAnimals editAnimal = EndangeredAnimals.find(idOfAnimal);
            editAnimal.update(idOfAnimal,newName,newHealth,newAge);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}