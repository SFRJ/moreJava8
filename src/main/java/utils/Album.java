package utils;

import java.util.List;

public class Album {

    private List<Song> songs;


    public java.util.stream.Stream<Song> getSongs() {
        return songs.stream();
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
