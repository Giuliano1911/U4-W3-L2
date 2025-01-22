package org.eventi;

import jakarta.persistence.*;

@Entity
@Table(name = "partecipazione")
public class Partecipazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="persona_id")
    private Persona personaId;

    @ManyToOne
    @JoinColumn(name="evento_id")
    private Evento eventoId;

    @Column(name="stato")
    private String stato;

    public Partecipazione() {
    }

    public Partecipazione(Persona personaId, Evento eventoId, String stato) {
        this.personaId = personaId;
        this.eventoId = eventoId;
        this.stato = stato;
    }

    public Long getId() {
        return id;
    }

    public Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Persona personaId) {
        this.personaId = personaId;
    }

    public Evento getEventoId() {
        return eventoId;
    }

    public void setEventoId(Evento eventoId) {
        this.eventoId = eventoId;
    }

    public Stato getStato() {
        return Stato.valueOf(this.stato.toUpperCase());
    }

    public void setStato(Stato stato) {
        this.stato = stato.toString();
    }

    @Override
    public String toString() {
        return "Partecipazione{" +
                "id=" + id +
                ", personaId=" + personaId +
                ", eventoId=" + eventoId +
                ", stato=" + stato +
                '}';
    }
}