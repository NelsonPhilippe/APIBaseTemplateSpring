package fr.nelson.apibasetemplate.enumerations;

public enum SecurizedRoutes {

    DEFAULT("/", null, false),
    TEST("/test", null, false),;

    String route;
    Roles roles;
    boolean isSecurized;

    SecurizedRoutes(String route, Roles role, boolean isSecurized){
        this.route = route;
        this.roles = role;
    }

    public String getRoute() {
        return route;
    }

    public Roles getRoles() {
        return roles;
    }

    public boolean isSecurized() {
        return isSecurized;
    }

    public boolean hasRole(){
        return roles != null;
    }
}
