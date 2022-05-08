package com.manhpd;

import java.util.*;

public class Application {

    private static int counter = 0;

    public static void main(String[] args) {
//        sortList();
        sortPriorityQueueByIncreasedRanking();
//        sortPriorityQueueByDecreasedAge();
    }

    private static void sortList() {
        Player player1 = new Player(59, "John", 20);
        Player player2 = new Player(67, "Roger", 22);
        Player player3 = new Player(67, "Roger", 22);
        Player player4 = new Player(45, "Steven", 24);

        List<Player> footballTeam = new ArrayList<>();
        footballTeam.add(player1);
        footballTeam.add(player2);
        footballTeam.add(player3);
        footballTeam.add(player4);

        System.out.println("Before Sorting : " + footballTeam);
        Collections.sort(footballTeam);
        System.out.println("After Sorting : " + footballTeam);
    }

    /**
     * The highest ranking at the 0.
     * If the players has the same ranking, based on the insertion order.
     */
    private static void sortPriorityQueueByIncreasedRanking() {
        Player player1 = new Player(59, "John", 20, ++counter);
        Player player2 = new Player(67, "Roger1", 22, ++counter);
        Player player3 = new Player(67, "Roger2", 22, ++counter);
        Player player4 = new Player(45, "Steven", 24, ++counter);

        Comparator<Player> byIncreasedRanking = (p1, p2) -> {
            if (p1.getRanking() != p2.getRanking()) {
                return Integer.compare(p1.getRanking(), p2.getRanking());
            } else {
                return Integer.compare(p1.getLogicalTimer(), p2.getLogicalTimer());
            }
        };

        PriorityQueue<Player> playersByRanking = new PriorityQueue<>(byIncreasedRanking);
        playersByRanking.offer(player1);
        playersByRanking.offer(player2);
        playersByRanking.offer(player3);
        playersByRanking.offer(player4);

        System.out.println(playersByRanking.poll().toString());
        System.out.println(playersByRanking.poll().toString());
        System.out.println(playersByRanking.poll().toString());
        System.out.println(playersByRanking.poll().toString());
    }

    private static void sortPriorityQueueByDecreasedAge() {

    }
}
