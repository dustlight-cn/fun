package plus.fun.kubeless.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import plus.fun.core.entities.Function;
import plus.fun.kubeless.entities.kubeless.FunctionEntity;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
public class KubelessFunction implements Function {

    private FunctionEntity function;

    @Override
    public String getOwner() {
        return function == null || function.getMetadata() == null || function.getMetadata().getLabels() == null ? null : function.getMetadata().getLabels().get("owner");
    }

    @Override
    public String getClientId() {
        if (function == null || function.getMetadata() == null)
            return null;
        String c = function.getMetadata().getLabels() == null ? null : function.getMetadata().getLabels().get("clientId");
        if (!StringUtils.hasText(c)) {
            c = getPrefix(function.getMetadata().getName(), '-');
            if (StringUtils.hasText(c))
                return c.substring(1);
        }
        return c;
    }

    @Override
    public String getName() {
        if (function == null || function.getMetadata() == null)
            return null;
        String n = function.getMetadata().getLabels() == null ? null : function.getMetadata().getLabels().get("name");
        if (StringUtils.hasText(n))
            return n;
        return getSuffix(function.getMetadata().getName(), '-');
    }

    @Override
    public String getRuntime() {
        return function == null || function.getSpec() == null ? null : function.getSpec().getRuntime();
    }

    @Override
    public String getHandler() {
        return function == null || function.getSpec() == null ? null : function.getSpec().getHandler();
    }

    @Override
    public Instant getCreatedAt() {
        return function == null || function.getMetadata() == null || function.getMetadata().getCreationTimestamp() == null ?
                null : function.getMetadata().getCreationTimestamp().toDate().toInstant();
    }

    @Override
    public Instant getUpdatedAt() {
        return null;
    }

    public static String getSuffix(String input, char split) {
        if (input == null)
            return null;
        int index = input.indexOf(split);
        if (index == -1)
            return input;
        return input.substring(index + 1);
    }

    public static String getPrefix(String input, char split) {
        if (input == null)
            return null;
        int index = input.indexOf(split);
        if (index == -1)
            return input;
        return input.substring(0, index);
    }
}
