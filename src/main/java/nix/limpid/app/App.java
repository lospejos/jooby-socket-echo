package nix.limpid.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import nix.limpid.app.controller.rest.IndexController;
import nix.limpid.app.module.M1;
import nix.limpid.app.module.M2;
import org.jooby.Jooby;
import org.jooby.json.Jackson;

import java.util.Locale;

/**
 * @author jooby generator
 */
public class App extends Jooby {

  {
    use(new M1());
    use(new M2());

    use(new Jackson().doWith(objectMapper -> {
      objectMapper.setDateFormat(new ISO8601DateFormat());
      objectMapper.setLocale(Locale.US);
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
      objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
      objectMapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter(DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR));
      objectMapper.registerModule(new JodaModule());
      objectMapper.registerModule(new JavaTimeModule());
      objectMapper.registerModule(new Jdk8Module());
      objectMapper.registerModule(new ParameterNamesModule());
      objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
    }));


    use(IndexController.class);
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}
