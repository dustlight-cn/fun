package plus.fun.core.entities;

import java.time.Instant;

public interface Function {

    String getOwner();

    String getClientId();

    String getName();

    String getRuntime();

    String getHandler();

    Instant getCreatedAt();

    Instant getUpdatedAt();

}
