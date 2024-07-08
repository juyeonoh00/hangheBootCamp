package myselectshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myselectshop.domain.User;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponseDto {
    private String password;
    private String nickname;


    public static SignUpResponseDto toDto(User user) {
        return new SignUpResponseDto(user.getPassword(), user.getUsername());
    }
}