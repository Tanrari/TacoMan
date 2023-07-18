package sia.tacocloud.tacos.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.tacos.dto.User;
import sia.tacocloud.tacos.repos.UserRepository;


@Configuration
@EnableWebSecurity

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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
//        http.headers().frameOptions().disable();
        return http
                .authorizeRequests()
                .antMatchers("/design", "/orders/**").access("hasRole('USER')")
                .antMatchers("/", "/**").access("permitAll()")
                .antMatchers("/api/**").access("denyAll()")
//                .antMatchers("/h2-console/**").access("permitAll()")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
//                .csrf().disable()
                .logout()
                .logoutSuccessUrl("/").and()
//                .headers().frameOptions().disable()
//                .and()
                .build();
    }



}
