package br.com.fulltime.app.generator;

public class Number {

    private long generationID = 0;
    private long value = 0;

    public Number(long generationID, long value) {
        this.generationID = generationID;
        this.value = value;
    }

    public long getValue() {
        return value;
    }

}
