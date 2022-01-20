package cn.dustlight.fun.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import cn.dustlight.fun.core.entities.Function;
import cn.dustlight.fun.core.service.FunctionService;

import java.util.Collection;

@SpringBootTest
public class FunctionServiceTest {

    @Autowired
    private FunctionService functionService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getRuntimes() {
        System.out.println(functionService.getRuntimes().block());
    }

    @Test
    public void getFunction() throws JsonProcessingException {
        Function fun = (Function) functionService.get("86c3e34e2030000","hello").block();
        System.out.println(mapper.writeValueAsString(fun));

    }

    @Test
    public void getFunctions() throws JsonProcessingException {
        Collection<Function> funs = (Collection<Function>) functionService.list("86c3e34e2030000").collectList().block();
        System.out.println(mapper.writeValueAsString(funs));

    }
}
