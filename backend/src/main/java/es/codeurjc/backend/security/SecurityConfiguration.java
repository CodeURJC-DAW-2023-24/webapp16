package es.codeurjc.backend.security;


import es.codeurjc.backend.security.jwt.JwtRequestFilter;
import es.codeurjc.backend.security.jwt.UnauthorizedHandlerJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    RepositoryUserDetailsService userDetailsService;

    @Autowired
    private UnauthorizedHandlerJwt unauthorizedHandlerJwt;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http
                .securityMatcher("/api/**")
                .exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandlerJwt));

        http
                .authorizeHttpRequests(authorize -> authorize
                        // PRIVATE ENDPOINTS
                          // API REST
                        .requestMatchers("/api/users").hasAnyRole("ADMIN")
                        .requestMatchers("/api/users/**").hasAnyRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/users/me").hasAuthority("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/users/me").hasAuthority("USER")

                        .requestMatchers(HttpMethod.GET,"/api/tournaments").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/tournaments/**").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST,"/api/tournaments/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/tournaments/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/tournaments/**").hasAnyRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"/api/teams").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/teams/**").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST,"/api/teams/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/teams/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/teams/**").hasAnyRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"/api/players").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/players/**").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST,"/api/players/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/players/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/players/**").hasAnyRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"/api/reports").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.GET,"/api/reports/**").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST,"/api/reports/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/reports/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/reports/**").hasAnyRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"/api/matches").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.GET,"/api/matches/**").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST,"/api/matches/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/matches/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/matches/**").hasAnyRole("ADMIN")

                       // .requestMatchers(HttpMethod.GET,"/api/search/**").hasAnyRole("USER")
                        .requestMatchers("/api/statistics/**").permitAll()

                        // PUBLIC ENDPOINTS (anything that's not filtered by the above rules, is public. It is not necessary to add anything here)
                        .anyRequest().permitAll()
                );

        // Disable Form login Authentication
        http.formLogin(formLogin -> formLogin.disable());

        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf(csrf -> csrf.disable());

        // Disable Basic Authentication
        http.httpBasic(httpBasic -> httpBasic.disable());

        // Stateless session
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add JWT Token filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http
                .authorizeHttpRequests(authorize -> authorize
                        // PUBLIC PAGES
                       // .requestMatchers("https://localhost:8443").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/new/**").permitAll()
                        .requestMatchers("/new").permitAll()
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
                        .requestMatchers("/players").permitAll()
                        .requestMatchers("/players/{playerName}/{lastName}").permitAll()
                        .requestMatchers("/teams/{name}/{playerName}/{lastName}").permitAll()
                        .requestMatchers("/loginSuccesfull").permitAll()
                        .requestMatchers("/signUpRequest").permitAll()
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers("/errorTemplate").permitAll()
                        .requestMatchers("/EntityNotFound").permitAll()
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/**", "/swagger-ui/index.html").permitAll()
                        // PRIVATE PAGES
                        // USER PAGES
                        .requestMatchers("/profile").hasAnyRole("USER")
                        .requestMatchers("/profileMod").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST,"/profile/save").hasAnyRole("USER")
                        .requestMatchers("/search").hasAnyRole("USER")
                        .requestMatchers("/tournament/{cup}/{id}/report").hasAnyRole("USER")
                        //ADMIN PAGES
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

                        //ANGULAR PAGES
                       //F .requestMatchers("/new/**").permitAll()


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
                );

        return http.build();
    }
}































//
//@Configuration
//@EnableWebSecurity
//@DependsOn("repositoryUserDetailsService")
//public class SecurityConfiguration{
//
//
//
//    @Autowired
//    public RepositoryUserDetailsService userDetailService;
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(userDetailService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.authenticationProvider(authenticationProvider());
//
//        http
//                .authorizeHttpRequests(authorize -> authorize
//
//                        // PUBLIC PAGES
//                        .requestMatchers("https://localhost:8443").permitAll()
//                        .requestMatchers("/").permitAll()
//                        .requestMatchers("/new/**").permitAll()
//
//                        .requestMatchers("./").permitAll()
//                        .requestMatchers("/css/**","/js/**","/fonts/**","/images/**").permitAll()
//                        .requestMatchers("/tournament/{cup}").permitAll()
//                        .requestMatchers("/teams").permitAll()
//                        .requestMatchers("/tournament/{cup}/{id}").permitAll()
//                        .requestMatchers("/players/statistics").permitAll()
//                        .requestMatchers("/teams/statistics").permitAll()
//                        .requestMatchers("/teams/{name}").permitAll()
//                        .requestMatchers("/teams/{name}/{playerName}").permitAll()
//                        .requestMatchers("/about").permitAll()
//                        .requestMatchers("/login").permitAll()
//                        .requestMatchers("/players").permitAll()
//                        .requestMatchers("/players/{playerName}/{lastName}").permitAll()
//                        .requestMatchers("/teams/{name}/{playerName}/{lastName}").permitAll()
//                        .requestMatchers("/loginSuccesfull").permitAll()
//                        .requestMatchers("/signUpRequest").permitAll()
//                        .requestMatchers("/error/**").permitAll()
//                        .requestMatchers("/errorTemplate").permitAll()
//                        .requestMatchers("/EntityNotFound").permitAll()
//                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/**", "/swagger-ui/index.html").permitAll()
//
//
//                        // API REST
//                        // POSTMAN
//                        .requestMatchers("/api/users").hasAnyRole("ADMIN")
//                        .requestMatchers("/api/users/**").hasAnyRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.GET, "/api/users/me").hasAuthority("USER")
//                        .requestMatchers(HttpMethod.PUT, "/api/users/me").hasAuthority("USER")
//
//                        .requestMatchers(HttpMethod.GET,"/api/tournaments").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/api/tournaments/**").hasAnyRole("USER")
//                        .requestMatchers(HttpMethod.POST,"/api/tournaments/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT,"/api/tournaments/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE,"/api/tournaments/**").hasAnyRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.GET,"/api/teams").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/api/teams/**").hasAnyRole("USER")
//                        .requestMatchers(HttpMethod.POST,"/api/teams/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT,"/api/teams/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE,"/api/teams/**").hasAnyRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.GET,"/api/players").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/api/players/**").hasAnyRole("USER")
//                        .requestMatchers(HttpMethod.POST,"/api/players/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT,"/api/players/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE,"/api/players/**").hasAnyRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.GET,"/api/reports").hasAnyRole("USER")
//                        .requestMatchers(HttpMethod.GET,"/api/reports/**").hasAnyRole("USER")
//                        .requestMatchers(HttpMethod.POST,"/api/reports/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT,"/api/reports/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE,"/api/reports/**").hasAnyRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.GET,"/api/matches").hasAnyRole("USER")
//                        .requestMatchers(HttpMethod.GET,"/api/matches/**").hasAnyRole("USER")
//                        .requestMatchers(HttpMethod.POST,"/api/matches/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT,"/api/matches/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE,"/api/matches/**").hasAnyRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.GET,"/api/search/**").hasAnyRole("USER")
//                        .requestMatchers("/api/statistics/**").permitAll()
//
//                        /*
//                        .requestMatchers("/api/**").hasAnyRole("POSTMAN")
//                        .requestMatchers("/api/tournaments").hasAnyRole("POSTMAN")
//                        .requestMatchers("/api/tournaments/{id}", "/api/tournaments/{id}/**").hasAnyRole("POSTMAN")
//                        .requestMatchers("/api/teams", "/api/teams/**").hasAnyRole("POSTMAN")
//                        .requestMatchers("/api/teams/{id}", "/api/teams/{id}/**").hasAnyRole("POSTMAN")
//                        .requestMatchers("/api/players", "/api/players/**").hasAnyRole("POSTMAN")
//                        .requestMatchers("/api/matches").hasAnyRole("POSTMAN")
//                        .requestMatchers("/api/matches/{id}").hasAnyRole("POSTMAN")
//                        .requestMatchers("/api/users").hasAnyRole("POSTMAN")
//                        .requestMatchers("/api/users/{id}").hasAnyRole("POSTMAN")
//                        .requestMatchers("/api/users/newUser").hasAnyRole("POSTMAN")
//                        .requestMatchers("/api/users/{idUser}").hasAnyRole("POSTMAN")
//                        .requestMatchers("/api/statistics/**").hasAnyRole("POSTMAN")
//                        .requestMatchers("/api/reports/**").hasAnyRole("POSTMAN")
//                        .requestMatchers("/api/search/**").hasAnyRole("POSTMAN")
//                        */
//
//
//                        // PRIVATE PAGES
//                        .requestMatchers("/profile").hasAnyRole("USER")
//                        .requestMatchers("/profileMod").hasAnyRole("USER")
//                        .requestMatchers(HttpMethod.POST,"/profile/save").hasAnyRole("USER")
//                        .requestMatchers("/search").hasAnyRole("USER")
//                        .requestMatchers("/tournament/{cup}/{id}/report").hasAnyRole("USER")
//                        .requestMatchers(HttpMethod.GET, "/tournament/{cup}/{id}/fillMatchReport").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/tournament/{cup}/{id}/fillMatchReport/saved").hasAnyRole("ADMIN")
//                        .requestMatchers("/tournamentCreation").hasAnyRole("ADMIN")
//                        .requestMatchers("/tournamentCreation/{tournamentNumber}/teamCreation/{teamNumber}").hasAnyRole("ADMIN")
//                        .requestMatchers("/tournamentCreation/{tournamentNumber}/{teamNumber}").hasAnyRole("ADMIN")
//                        .requestMatchers("/teamCreate").hasAnyRole("ADMIN")
//                        .requestMatchers("/tournamentCreation/{created}").hasAnyRole("ADMIN")
//                        .requestMatchers("addTeamToTournament/{cup}").hasAnyRole("ADMIN")
//                        .requestMatchers("/cancelTournamentCreation").hasAnyRole("ADMIN")
//                        .requestMatchers("/saveTournament").hasAnyRole("ADMIN")
//                        //
//
//                        //ANGULAR PAGES
//                        .requestMatchers("/new/**").permitAll()
//                )
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login")
//                        .failureUrl("/loginerror")
//                        .defaultSuccessUrl("/")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/")
//                        .permitAll()
//                )
//
//
//
//        ;
//
//        // Disable CSRF at the moment
//        http.csrf(AbstractHttpConfigurer::disable);
//
//        return http.build();
//    }
//
//}
