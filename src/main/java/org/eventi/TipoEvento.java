package org.eventi;

public enum TipoEvento {
    PUBBLICO,
    PRIVATO;

    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}
