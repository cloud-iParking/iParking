package ro.ubb.cloud.iParking.model.jwt;
import ro.ubb.cloud.iParking.model.dto.LoginDTO;

import java.io.Serializable;
public class JwtResponse implements Serializable{
    private static final long serialVersionUID = -8091879091924046844L;

    //private final String jwttoken;
    private final LoginDTO loginDTO;

//    public JwtResponse(String jwttoken) {
//        this.jwttoken = jwttoken;
//    }

    public JwtResponse(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }

    public String getToken() {
        return this.loginDTO.getToken();
    }
}
