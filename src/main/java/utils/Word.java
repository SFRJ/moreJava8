package utils;

public class Word {

    private String content;
    private int length;

    public Word(String content, int length) {
        this.content = content;
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public int getLength() {
        return length;
    }
}
