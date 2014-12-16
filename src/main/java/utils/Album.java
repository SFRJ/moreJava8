package utils;

import java.util.List;

public class Album {

    private Artist mainArtist;
    private List<Song> songs;
    private String name;

    public java.util.stream.Stream<Song> getSongs() {
        return songs.stream();
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Artist getMainArtist() {
        return mainArtist;
    }

    public String getName() {
        return name;
    }
}
