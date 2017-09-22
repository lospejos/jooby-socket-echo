package nix.limpid.app.controller.socket;

import com.google.inject.Inject;
import org.jooby.Request;
import org.jooby.WebSocket;
import org.jooby.WebSocket.*;
import org.jooby.mvc.Consumes;
import org.jooby.mvc.Path;
import org.jooby.mvc.Produces;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Path("/ws")
@Consumes("json")
@Produces("json")
public class WebSocketListener implements OnMessage<String>, OnOpen, OnClose, OnError {

    private WebSocket webSocket;

    private ExecutorService executorService = Executors.newFixedThreadPool(8);

    AtomicInteger integer = new AtomicInteger(1);

    @Inject
    public WebSocketListener(WebSocket webSocket){
        this.webSocket = webSocket;
    }


    @Override
    public void onMessage(String message) throws Exception {
        CompletableFuture.supplyAsync(() -> {
            return integer.incrementAndGet();
        }, executorService).thenApplyAsync(i -> {
            return i + 1;
        }).thenRunAsync(() -> {
            try {
                webSocket.broadcast(integer.intValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void onOpen(Request req, WebSocket ws) throws Exception {

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