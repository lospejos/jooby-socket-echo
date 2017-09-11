package nix.limpid.app.controller.rest;

import org.jooby.mvc.GET;
import org.jooby.mvc.Path;
import org.jooby.mvc.Produces;


@Path("/")
@Produces("json")
public class IndexController {

    @GET
    @Produces("json")
    public JsonGreeting index() {

        return new JsonGreeting();
    }
}


class JsonGreeting {
    private String mainMessage = "Echo-Socket Server";

    public String getMainMessage() {
        return mainMessage;
    }

    public void setMainMessage(String mainMessage) {
        this.mainMessage = mainMessage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JsonGreeting(String mainMessage, String description) {
        this.mainMessage = mainMessage;
        this.description = description;
    }

    public JsonGreeting() {
    }

    private String description = "...";
}