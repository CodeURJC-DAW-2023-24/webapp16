package es.codeurjc.backend.security;


import es.codeurjc.backend.service.RepositoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{



    @Autowired
    public RepositoryUserDetailsService userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http
                .authorizeHttpRequests(authorize -> authorize
                        // PUBLIC PAGES
                        .requestMatchers("https://localhost:8443").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("./").permitAll()
                        .requestMatchers("/css/**","/js/**","/fonts/**","/images/**").permitAll()
                        .requestMatchers("/tournament/{cup}").permitAll()
                        .requestMatchers("/teams").permitAll()
                        .requestMatchers("/tournament/{cup}/{id}").permitAll()
                        .requestMatchers("/players/statistics").permitAll()
                        .requestMatchers("/teams/statistics").permitAll()
                        .requestMatchers("/teams/{name}").permitAll()
                        .requestMatchers("/teams/{name}/{playerName}").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/api/players").permitAll()
                        .requestMatchers("/api/teams").permitAll()
                        .requestMatchers("/players").permitAll()
                        .requestMatchers("/players/{playerName}/{lastName}").permitAll()
                        .requestMatchers("/teams/{name}/{playerName}/{lastName}").permitAll()
                        .requestMatchers("/loginSuccesfull").permitAll()
                        .requestMatchers("/signUpRequest").permitAll()
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers("/errorTemplate").permitAll()
                        .requestMatchers("/EntityNotFound").permitAll()
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui/index.html").permitAll()
                        .requestMatchers("/api/**").permitAll()


                                // API REST
                                // POSTMAN
                        .requestMatchers("/api/tournaments").hasAnyRole("POSTMAN")
                        .requestMatchers("/api/tournaments/{id}", "/api/tournaments/{id}/**").hasAnyRole("POSTMAN")
                        .requestMatchers("/api/teams", "/api/teams/**").hasAnyRole("POSTMAN")
                        .requestMatchers("/api/teams/{id}", "/api/teams/{id}/**").hasAnyRole("POSTMAN")
                        .requestMatchers("/api/players", "/api/players/**").hasAnyRole("POSTMAN")
                        .requestMatchers("/api/matches").hasAnyRole("POSTMAN")
                        .requestMatchers("/api/matches/{id}").hasAnyRole("POSTMAN")
                        .requestMatchers("/api/users").hasAnyRole("POSTMAN")
                        .requestMatchers("/api/users/{id}").hasAnyRole("POSTMAN")
                        .requestMatchers("/api/users/newUser").hasAnyRole("POSTMAN")
                        .requestMatchers("/api/users/{idUser}").hasAnyRole("POSTMAN")
                        .requestMatchers("/api/statistics/**").hasAnyRole("POSTMAN")
                        .requestMatchers("/api/reports/**").hasAnyRole("POSTMAN")
                        .requestMatchers("/api/search/**").hasAnyRole("POSTMAN")



                        // PRIVATE PAGES
                        .requestMatchers("/profile").hasAnyRole("USER")
                        .requestMatchers("/profileMod").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST,"/profile/save").hasAnyRole("USER")
                        .requestMatchers("/search").hasAnyRole("USER")
                        .requestMatchers("/tournament/{cup}/{id}/report").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.GET, "/tournament/{cup}/{id}/fillMatchReport").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/tournament/{cup}/{id}/fillMatchReport/saved").hasAnyRole("ADMIN")
                        .requestMatchers("/tournamentCreation").hasAnyRole("ADMIN")
                        .requestMatchers("/tournamentCreation/{tournamentNumber}/teamCreation/{teamNumber}").hasAnyRole("ADMIN")
                        .requestMatchers("/tournamentCreation/{tournamentNumber}/{teamNumber}").hasAnyRole("ADMIN")
                        .requestMatchers("/teamCreate").hasAnyRole("ADMIN")
                        .requestMatchers("/tournamentCreation/{created}").hasAnyRole("ADMIN")
                        .requestMatchers("addTeamToTournament/{cup}").hasAnyRole("ADMIN")
                        .requestMatchers("/cancelTournamentCreation").hasAnyRole("ADMIN")
                        .requestMatchers("/saveTournament").hasAnyRole("ADMIN")
                        //
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .failureUrl("/loginerror")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )

                

        ;

        // Disable CSRF at the moment
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

}
