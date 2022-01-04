//package com.shop.web.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//   @Autowired
//   private DataSource dataSource;
//   @Override
//   protected void configure(HttpSecurity http) throws Exception {
//       http
//               .authorizeRequests()
//               .antMatchers("/").permitAll()
//               .anyRequest().authenticated()
//       .and()
//               .formLogin()
//               .loginPage("/login")
//               .permitAll()
//       .and()
//               .logout()
//               .permitAll();
//   }
//
//   @Override
//   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//       auth.jdbcAuthentication().dataSource(dataSource)
//               .passwordEncoder(NoOpPasswordEncoder.getInstance())
//               .usersByUsernameQuery("select name, description, email from products where name=?")
//               .authoritiesByUsernameQuery("select f.name, fr.roles from foods f inner join user_role fr on f.id = fr.food_id where f.name=?");
//   }
//
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.requiresChannel()
//      .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
//      .requiresSecure();
//
//}
//}