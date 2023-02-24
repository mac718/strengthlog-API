package com.mac718.strengthlogAPI;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mac718.strengthlogAPI.jwt.CustomUserDetailsService;
import com.mac718.strengthlogAPI.jwt.JWTAuthFilter;
import com.mac718.strengthlogAPI.jwt.JwtServiceImpl;
import com.mac718.strengthlogAPI.user.Role;
import com.mac718.strengthlogAPI.user.UserEntity;

@SpringBootTest
@AutoConfigureMockMvc
class JwtServiceImplTest {
	@Autowired
	private JwtServiceImpl jwtServiceImpl;
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CustomUserDetailsService userDetails;

	@Test
	void shouldNotAllowUnauthenticatedUsersToAccessSecuredResourcetest() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/users")).andDo(print()).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser("user@user.com")
	void shouldAllowAccessToSecureResourceWithValidToken() throws Exception {
		UserEntity user = UserEntity.builder().email("user@user.com").password("password").role(Role.USER).build();
		
		Authentication auth
        = new UsernamePasswordAuthenticationToken(
            user.getEmail(),
            user.getPassword(),
            user.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(auth);
    
		when(userDetails.loadUserByUsername(Mockito.anyString())).thenReturn(new User(user.getEmail(), user.getPassword(), user.getAuthorities()));
    	String token = jwtServiceImpl.createToken(auth);
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/users").header("Authorization", "Bearer " + token) ).andDo(print()).andExpect(status().isOk());
	}

}
