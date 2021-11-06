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
            return new ModelAndView(model,"animal.hbs");
        }, new HandlebarsTemplateEngine());
    }
}