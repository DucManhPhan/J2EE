package com.manhpd;

import java.util.List;
import java.util.Map;

public class Application {
    public static void main( String[] args ) {
        PlaylistYoutube youtube = new PlaylistYoutube();
//        List<String> urls = youtube.getPlaylistLinks();
//        System.out.println("Size of playlist: " + urls.size());
//        urls.stream().forEach(System.out::println);

        Map<String, String> titleWithLinks = youtube.getPlaylistWithTitle();
        youtube.downloadVideosWith(titleWithLinks);
    }
}
