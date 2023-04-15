package fr.nelson.apibasetemplate.enumerations;

public enum Roles {
    ;

    String role;

    Roles(String role){
        this.role = role;
    }

    public String getRoleName() {
        return role;
    }
}
