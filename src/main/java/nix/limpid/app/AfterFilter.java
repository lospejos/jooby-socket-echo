package nix.limpid.app;

import org.jooby.Request;
import org.jooby.Response;
import org.jooby.Result;
import org.jooby.Route;

public class AfterFilter implements Route.After {
    @Override
    public Result handle(Request req, Response rsp, Result result) throws Exception {
        return null;
    }
}
