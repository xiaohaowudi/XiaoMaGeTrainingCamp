package org.geektimes.projects.user.management;



public class UserManagement implements UserManagementMBean {

    private final org.geektimes.projects.user.domain.User user;

    public UserManagement(org.geektimes.projects.user.domain.User user) {
        this.user = user;
    }

    @Override
    public Long getId() {
        return user.getId();
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public String getPhoneNumber() {
        return user.getPhoneNumber();
    }

    @Override
    public void setId(Long id) {
        user.setId(id);
    }

    @Override
    public void setName(String name) {
        user.setName(name);
    }

    @Override
    public void setPassword(String password) {
        user.setPassword(password);
    }

    @Override
    public void setEmail(String email) {
        user.setEmail(email);
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        user.setPhoneNumber(phoneNumber);
    }
}
