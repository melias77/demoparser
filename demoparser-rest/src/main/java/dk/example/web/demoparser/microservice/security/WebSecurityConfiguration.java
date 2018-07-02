/**
 * 
 */
package dk.example.web.demoparser.microservice.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

   
    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                return new User(username, "password", true, true, true, true,
                      AuthorityUtils.createAuthorityList("USER"));
            	
//                if (pathHandler.userExists(username)) {
//                    return new User(username, "password", true, true, true, true,
//                                    AuthorityUtils.createAuthorityList("USER"));
//                } else {
//                    log.warn("Feed directory not found for user:" + username);
//                    throw new UsernameNotFoundException("could not find user account '" + username + "'");
//                }
            }
        };
    }
}
