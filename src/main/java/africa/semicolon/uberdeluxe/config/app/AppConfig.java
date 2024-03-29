package africa.semicolon.uberdeluxe.config.app;


import africa.semicolon.uberdeluxe.config.distance.DistanceConfig;
import africa.semicolon.uberdeluxe.config.mail.MailConfig;
import africa.semicolon.uberdeluxe.config.security.util.JwtUtil;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Value("${cloudinary.api.name}")
        private String cloudName;

        @Value("${cloudinary.api.key}")
        private String apiKey;

        @Value("${cloudinary.api.secret}")
        private String apiSecret;
//
        @Value("${mail.api.key}")
        private String mailApiKey;

        @Value("${sendinblue.mail.url}")
        private String mailUrl;

        @Value("${google.api.key}")
        private String googleApiKey;

        @Value("${google.distance.url}")
        private String googleDistanceUrl;

       @Value("${jwt.secret.key}")
      private String jwtSecret;



    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name",cloudName,
                        "api_key",apiKey,
                        "api_secret",apiSecret
                )
        );
    }

    @Bean
    public MailConfig mailConfig(){
        return new MailConfig(mailApiKey, mailUrl);
    }

    @Bean
    public DistanceConfig distanceConfig(){
        return new DistanceConfig(googleDistanceUrl,googleApiKey);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil(jwtSecret);
    }

}


