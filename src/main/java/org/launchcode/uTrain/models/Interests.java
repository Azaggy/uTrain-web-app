package org.launchcode.uTrain.models;

public class Interests {
    private boolean walking=false;
    private boolean running=false;
    private boolean cycling=false;
    private boolean golf=false;
    private boolean baseball=false;
    private boolean basketball=false;

    public Interests(){}

    public Interests(boolean walking, boolean running, boolean cycling, boolean golf, boolean baseball, boolean basketball){
        this.walking=walking;
        this.running=running;
        this.cycling=cycling;
        this.golf=golf;
        this.baseball=baseball;
        this.basketball=basketball;
    }

    public boolean isWalking() {
        return walking;
    }

    public void setWalking(boolean walking) {
        this.walking = walking;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isCycling() {
        return cycling;
    }

    public void setCycling(boolean cycling) {
        this.cycling = cycling;
    }

    public boolean isGolf() {
        return golf;
    }

    public void setGolf(boolean golf) {
        this.golf = golf;
    }

    public boolean isBaseball() {
        return baseball;
    }

    public void setBaseball(boolean baseball) {
        this.baseball = baseball;
    }

    public boolean isBasketball() {
        return basketball;
    }

    public void setBasketball(boolean basketball) {
        this.basketball = basketball;
    }
}
