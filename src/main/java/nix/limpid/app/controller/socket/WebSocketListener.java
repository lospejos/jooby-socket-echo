package nix.limpid.app.controller.socket;

import com.google.inject.Inject;
import org.jooby.Request;
import org.jooby.WebSocket;
import org.jooby.WebSocket.*;
import org.jooby.mvc.Consumes;
import org.jooby.mvc.Path;
import org.jooby.mvc.Produces;

import java.util.Map;
import java.util.logging.Logger;

@Path("/ws")
@Consumes("json")
@Produces("json")
public class WebSocketListener implements OnMessage<String>, OnOpen, OnClose, OnError {

    private WebSocket webSocket;

    @Inject
    public WebSocketListener(WebSocket webSocket){
        this.webSocket = webSocket;
    }


    @Override
    public void onMessage(String message) throws Exception {
        webSocket.broadcast(message);
    }

    @Override
    public void onOpen(Request req, WebSocket ws) throws Exception {
        ws.send(req.attributes());
        Logger.getLogger("Socket").info(ws.toString());
    }

    @Override
    public void onClose(CloseStatus status) throws Exception {
        webSocket.send("Hello");
    }

    @Override
    public void onError(Throwable err) {
        try {
            webSocket.send(err.getCause());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}