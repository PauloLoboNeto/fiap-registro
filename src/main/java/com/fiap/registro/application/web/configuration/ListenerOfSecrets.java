package com.fiap.registro.application.web.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

@Slf4j
public class ListenerOfSecrets implements ApplicationListener<ApplicationPreparedEvent> {

    private final String SPRING_DATASOURCE_PASSWORD = "spring.datasource.password";
    private final String SPRING_DATASOURCE_USERNAME = "spring.datasource.username";

    private final String JWT_SECRET = "jwt.secret";

    private final String SPRING_DATASOURCE_URL = "spring.datasource.url";
    private String SECRET_NAME = "segredos";

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {

        String secretJson = getSecret();

        String dbPassword = getString(secretJson, "password");
        String dbUsername = getString(secretJson, "username");
        String datasourceurl = getString(secretJson, "datasourceurl");

        String jwtSecret = getString(secretJson, "jwtSecret");

        ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
        Properties props = new Properties();
        props.put(JWT_SECRET, jwtSecret);
        props.put(SPRING_DATASOURCE_PASSWORD, dbPassword);
        props.put(SPRING_DATASOURCE_USERNAME, dbUsername);
        props.put(SPRING_DATASOURCE_URL, datasourceurl);
        environment.getPropertySources().addFirst(new PropertiesPropertySource("aws.secret.manager", props));
    }
    public String getSecret() {
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(Region.of("us-east-1"))
                .build();

        String secret = null, decodedBinarySecret = null;

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(SECRET_NAME)
                .build();

        GetSecretValueResponse response = null;

        try {
            response = client.getSecretValue(getSecretValueRequest);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }

        if (response.secretString() != null) {
            secret = response.secretString();
        } else {
            decodedBinarySecret = new String(Base64.getDecoder().decode(response.secretBinary().asByteBuffer()).array());
        }
        return secret;
    }

    private String getString(String json, String path) {
        try {
            JsonNode root = mapper.readTree(json);
            return root.path(path).asText();
        } catch (IOException e) {
            return null;
        }
    }
}
