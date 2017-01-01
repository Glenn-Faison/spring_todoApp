package ReSTAPI;

import ReSTAPI.DAO.UserDAO;
import ReSTAPI.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableSwagger2
public class Application {

    @Autowired
    private UserDAO userDAO;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void createDummyUsers() {
        User user = new User();
        user.setEmail("glennfaison@gmail.com")
                .setPassword("1111")
                .setPhoneNumber("(+237) 675-611-933")
                .setUsername("Glenn Faison");
        userDAO.save(user);
        user = new User();
        user.setEmail("achabill12@gmail.com")
                .setPassword("1111")
                .setPhoneNumber("(+237) 679-873-401")
                .setUsername("Bill Acha");
        userDAO.save(user);
        user = new User();
        user.setEmail("tarkangmcshan@gmail.com")
                .setPassword("1111")
                .setPhoneNumber("(+237) 677-661-303")
                .setUsername("Bate-Epey Ebai Tarkang M.");
        userDAO.save(user);
    }

    @Bean
    public Docket swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }
}