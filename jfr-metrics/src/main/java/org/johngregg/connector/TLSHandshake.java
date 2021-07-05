package org.johngregg.connector;

public class TLSHandshake implements TLSHandshakeMBean  {

    private String peer;    // maybe we don't even need this
    private int count;

    public TLSHandshake() {
    }

    public TLSHandshake(String peerHost) {
        this.peer = peerHost;
    }

    @Override
    public String getPeer() {
        return peer;
    }

    public void setPeer(String peer) {
        this.peer = peer;
    }

    @Override
    public int getCount() {
        return count;
    }

    public int increment() {
        return ++count;
    }
}
