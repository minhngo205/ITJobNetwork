package com.example.itnetwork.dtos.response;

public class LoginDTOResponse {
    Data data;

    // Getter Methods
    public Data getData() {
        return data;
    }

    // Setter Methods
    public void setData(Data dataObject) {
        this.data = dataObject;
    }

    public static class Data {
        private String token;
        private String id;
        private String email;
        private String role;
        private int roleId;

        // Getter Methods
        public String getToken() {
            return token;
        }

        public String getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getRole() {
            return role;
        }

        public float getRoleId() {
            return roleId;
        }

        // Setter Methods
        public void setToken(String token) {
            this.token = token;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }
    }
}