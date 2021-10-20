package plus.fun.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.fun.core.service.FunctionService;

@SpringBootTest
public class FunctionServiceTest {

    @Autowired
    private FunctionService functionService;

    @Test
    public void getRuntimes() {
        System.out.println(functionService.getRuntimes().collectList().block());
    }
}
