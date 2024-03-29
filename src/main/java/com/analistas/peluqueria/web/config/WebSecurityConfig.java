package com.analistas.peluqueria.web.config;

// import javax.sql.DataSource;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
// public class WebSecurityConfig {

//     @Autowired
//     DataSource dataSource;

//     @Autowired
//     private BCryptPasswordEncoder bCryptPasswordEncoder;

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//         http
//                 .authorizeHttpRequests((requests) -> requests
//                         .requestMatchers("/css/**", "/js/**", "/img/**", "/", "/index")
//                         .permitAll()
//                         .anyRequest().authenticated())
//                 .formLogin((form) -> form
//                         .loginPage("/login")
//                         .permitAll())
//                 .logout((logout) -> logout
//                         .permitAll()
//                         .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                         .logoutSuccessUrl("/login"));

//         return http.build();
//     }

//     @Autowired
//     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

//         // Autenticar con JDBC
//         auth
//                 .jdbcAuthentication()
//                 .dataSource(dataSource)
//                 .usersByUsernameQuery(
//                         "SELECT nombre, clave, activo FROM usuario WHERE nombre = ?")
//                 .authoritiesByUsernameQuery(
//                         "SELECT u.nombre, r.nombre FROM rol r INNER JOIN usuario u ON u.id_permiso = r.id WHERE u.nombre = ?")
//                 .passwordEncoder(bCryptPasswordEncoder);

//     }

//     /*
//      * @Bean
//      * public UserDetailsService userDetailsService() {
//      * 
//      * UserDetails user = User.withDefaultPasswordEncoder()
//      * .username("user")
//      * .password("password")
//      * .roles("USER")
//      * .build();
//      * 
//      * UserDetails admin = User.withDefaultPasswordEncoder()
//      * .username("admin")
//      * .password("password")
//      * .roles("ADMIN")
//      * .build();
//      * 
//      * return new InMemoryUserDetailsManager(user, admin);
//      * }
//      */

// }
