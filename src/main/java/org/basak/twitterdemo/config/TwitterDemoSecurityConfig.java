package org.basak.twitterdemo.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Slf4j
public class TwitterDemoSecurityConfig {
    @Bean
    public JwtTokenFilter getJwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		/*
		Spring filter chain ile tüm isteklerin üzerinden geçeceği noktayı kontrol edecek metodu yazmış oluyoruz.
		Burada tüm isteklerin yetkinlendirmesi, oturum açması gibi talepler yönetilir.
		 */
        System.out.println("securityFilterChain Devrede!!!");
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers(  "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/v1/dev/user/login",
                                "/v1/dev/user/register",
                                "/v1/dev/user/get-profile",
                                "/v1/dev/post/new-post",
                                "/v1/dev/post/show-my-posts",
                                "/v1/dev/user-role",
                                "/v1/dev/post/show-all-posts",
                                "/v1/dev/user/activate-code").permitAll() //herkes girebilir.
                        .requestMatchers("/admin/**","/v1/dev/post/show-all-posts").hasAuthority("ADMIN") //admin rolüne sahip olanların girişine izin veriliyor.
                        .anyRequest().authenticated()); //üsttekiler dışında kalan tüm isteklerde authenticate olmak gerekiyor.
//		http.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
        //Bizim sistemimizde doğrulama token ile yapılacak. Form sayfası ile değil.
		/*
		csrf nedir?
		sunucu ile istemci arasında olan iletişimi doğrulamak ve güvenli şekilde iletişimde kalmak için ilk istek ile oturum açıldığında
		sunucu clienta bir csrfId gönderir. Bununla gelen istekleri karşılar. Böylece belli endpointlerden gelen isteklerin güvenli
		kalması sağlanır.
		Restapi da csrf kapalı kalması gereklidir. FormLogin kullanmayacağımız için csrf kapatırız.
		Güvenlli iletişim için JWT token gibi sistemler kullanılır.
		Restapide istekler sadece bir web formdan gelmek zorunda değildir!
		 */
        http.csrf(AbstractHttpConfigurer::disable);
        http.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
