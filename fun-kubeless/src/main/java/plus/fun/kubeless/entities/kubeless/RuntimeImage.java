package plus.fun.kubeless.entities.kubeless;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@Getter
@Setter
public class RuntimeImage implements Serializable {

    @JsonAlias("ID")
    @SerializedName("ID")
    private String id;

    private String depName;

    private String fileNameSuffix;

    private Collection<Version> versions;


    @Getter
    @Setter
    public static class Version implements Serializable {

        private String name;
        private String version;
        private Collection<Image> images;

    }

    @Getter
    @Setter
    public static class Image implements Serializable {

        private String command;
        private String image;
        private String phase;
        private Map<String, String> env;

    }
}
