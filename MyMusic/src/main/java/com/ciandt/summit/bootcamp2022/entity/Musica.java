package com.ciandt.summit.bootcamp2022.entity;
import javax.persistence.*;


    @Entity
    @Table(name = "Musicas")
    public class Musica {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "Nome")
    private String nome;

    @Column(name = "ArtistaId")
    private String artistaId;

    public Musica() {
    }

        public Musica(String id, String nome, String artistaId) {
            this.id = id;
            this.nome = nome;
            this.artistaId = artistaId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getArtistaId() {
            return artistaId;
        }

        public void setArtistaId(String artistaId) {
            this.artistaId = artistaId;
        }

        @Override
    public String toString() {

        return "Musica [id=" + id + ", nome=" + nome + ", artistaId=" + artistaId + "]";
    }
}
