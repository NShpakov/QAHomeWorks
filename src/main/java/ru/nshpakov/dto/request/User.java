
package ru.nshpakov.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.annotation.ParametersAreNonnullByDefault;

@Data
@Builder
@AllArgsConstructor
@JsonSerialize
@ParametersAreNonnullByDefault
public class User {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private Long userStatus;
    private String username;
}