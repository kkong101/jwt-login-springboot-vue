package main.demo.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class Param_User {


    @Getter@Setter
    public static class Add {

        private String user_id;
        private String user_pwd;
        private String user_name;

        @Builder
        public Add(String user_id, String user_pwd,String user_name) {
            this.user_id = user_id;
            this.user_pwd = user_pwd;
            this.user_name = user_name;
        }
    }

}
