package com.manhpd;

public class Player implements Comparable<Player> {
    private int ranking;
    private String name;
    private int age;

    private int logicalTimer;

    public Player(int ranking, String name, int age) {
        this.ranking = ranking;
        this.name = name;
        this.age = age;
    }

    public Player(int ranking, String name, int age, int counter) {
        this(ranking, name, age);
        this.logicalTimer = counter;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLogicalTimer() {
        return this.logicalTimer;
    }

    @Override
    public int compareTo(Player o) {
//        return Integer.compare(getRanking(), o.getRanking());
        if (this.getRanking() > o.getRanking()) {
            return 1;
        } else if (this.getRanking() == o.getRanking()) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.name)
                .append("@")
                .append(this.ranking)
                .append("#")
                .append(this.age)
                .toString();
    }
}
