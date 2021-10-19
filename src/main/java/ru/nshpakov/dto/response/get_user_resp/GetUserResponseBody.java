package ru.nshpakov.dto.response.get_user_resp;

import lombok.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class GetUserResponseBody {
    @Setter
    public Long id;
    @Setter
    public String username;
    @Setter
    public String firstName;
    @Setter
    public String lastName;
    @Setter
    public String email;
    @Setter
    public String password;
    @Setter
    public String phone;
    @Setter
    public int userStatus;
}
