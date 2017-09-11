package nix.limpid.app.controller.util.data.consumes;

import nix.limpid.app.controller.util.data.MessageAbstraction;

import java.util.Map;

public class InputMessage<T> extends MessageAbstraction<T> {

    private String content;

    private String checksum;

    private Map<String, String> messageKeySet;
}
