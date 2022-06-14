package com.example.e_invoicingpaymentsystem.config;


import com.example.e_invoicingpaymentsystem.config.security.JwtAuthenticationEntryPoint;
import com.example.e_invoicingpaymentsystem.config.security.JwtAuthenticationTokenFilter;
import com.example.e_invoicingpaymentsystem.config.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;


    private final JwtAuthenticationEntryPoint unauthorizedHandler;



    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and().authorizeRequests()
//               .antMatchers("/api/auth/signIn" ).permitAll()
                .antMatchers("/api/company/signUp" ).permitAll()
                .antMatchers("/api/company/signIn" ).permitAll()
                .antMatchers("/api/company/findByTin" ).hasAuthority("ROLE_USER")
                .antMatchers("/api/company/deletebytin").hasAuthority("ROLE_USER")
                .antMatchers("/api/company/updatebytin").hasAuthority("ROLE_USER")
                .antMatchers("/api/invoice/all" ).hasAuthority("ROLE_USER");



//          .antMatchers("/api/v1/auth/signUp" ).hasRole("ADMIN");
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("0")
//                .password("0")
//                .authorities("q");
//    }

//        @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable() .formLogin().and() .logout().logoutSuccessUrl("/")
//                    .and().authorizeRequests()
//                .and().authorizeRequests()
//                .antMatchers("/").hasAuthority("ADMIN")
//                .antMatchers("/api/auth/signIn" ).permitAll()
//               .antMatchers("/api/auth/signUp" ).hasAnyAuthority("ADMIN")
//.anyRequest().authenticated();
//
//        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//
//    }



//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("q").password(passwordEncoder().encode("q")).roles("user");
//
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("0").password("0").roles("user");

    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {

        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
