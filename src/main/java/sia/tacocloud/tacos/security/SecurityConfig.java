package sia.tacocloud.tacos.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.tacos.dto.User;
import sia.tacocloud.tacos.repos.UserRepository;


@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo){

//        List<UserDetails> userList = new ArrayList<>();
//        userList.add(new User("buzz",encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("Role_USER"))));
//        userList.add(new User("woody",encoder.encode("password"),Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        return new InMemoryUserDetailsManager(userList);

            return new UserDetailsService() {
                @Override
                public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                    User user = userRepo.findByUsername(username);
                    if (user != null) return user;
                    throw  new UsernameNotFoundException("User"+ username+ " not found");

                }
            };
//        return username -> {
//            User user = userRepo.findByUsername(username);
//            if (user != null) return user;
//            throw new UsernameNotFoundException("User ‘" + username + "’ not found");
//        };
    }


    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        return http.build();
    }

}
