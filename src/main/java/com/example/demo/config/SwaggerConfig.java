
// // package com.example.demo.config; 
// // import io.swagger.v3.oas.models.OpenAPI; 
// // import io.swagger.v3.oas.models.servers.Server; 
// // import org.springframework.context.annotation.Bean; 
// // import org.springframework.context.annotation.Configuration;
// //  import java.util.List;
// //   @Configuration
// //    public class SwaggerConfig {
// //          @Bean 
// //          public OpenAPI customOpenAPI() {
// //                  return new OpenAPI()
// //                   // You need to change the port as per your server 
// //                   .servers(List.of( 
// //                         new Server().url("https://9309.pro604cr.amypo.ai/") 
// //                          ));
// //                          } 
// //                 }

// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.Components;
// import io.swagger.v3.oas.models.security.SecurityRequirement;
// import io.swagger.v3.oas.models.security.SecurityScheme;
// import io.swagger.v3.oas.models.servers.Server;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import java.util.List;

// @Configuration
// public class SwaggerConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {

//         return new OpenAPI()
//                 // ✅ Server URL (already irundhadhe)
//                 .servers(List.of(
//                         new Server().url("https://9309.pro604cr.amypo.ai/")
//                 ))

//                 // ✅ GLOBAL SECURITY REQUIREMENT
//                 .addSecurityItem(
//                         new SecurityRequirement().addList("bearerAuth")
//                 )

//                 // ✅ SECURITY SCHEME DEFINITION
//                 .components(
//                         new Components().addSecuritySchemes(
//                                 "bearerAuth",
//                                 new SecurityScheme()
//                                         .name("Authorization")
//                                         .type(SecurityScheme.Type.HTTP)
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")
//                         )
//                 );
//     }
// }
package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // JWT Security Scheme
        SecurityScheme securityScheme = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .servers(List.of(
                        new Server().url("https://9309.pro604cr.amypo.ai/")
                ))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", securityScheme)
                );
    }
}