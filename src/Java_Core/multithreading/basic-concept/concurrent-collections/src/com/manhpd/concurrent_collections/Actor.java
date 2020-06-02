package com.manhpd.concurrent_collections;

import java.util.Objects;

public class Actor {

    private String lastName;

    private String firstName;

    public Actor(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String lastName() {
        return this.lastName;
    }

    public String firstName() {
        return this.firstName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.lastName);
        hash = 67 * hash + Objects.hashCode(this.firstName);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        final Actor actor = (Actor) obj;
        if (actor == this) {
            return true;
        }

        if (!Objects.equals(this.lastName, actor.lastName)) {
            return false;
        }

        return Objects.equals(this.firstName, actor.firstName);
    }

    @Override
    public String toString() {
        return "Actor{" + "lastName=" + this.lastName + ", firstName=" + this.firstName + "}";
    }

}
