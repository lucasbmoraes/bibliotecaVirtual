package com.example.demo.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro")

public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nome;
    private String genero;
    private String autor;
    private Date ano_publicacao;
    private boolean status = true;

    // CONSTRUTOR VAZIO
    public Livro() {

    }

    // CONSTRUTOR
    public Livro(int id, String nome, String genero, String autor, Date ano_publicacao, boolean status) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.autor = autor;
        this.ano_publicacao = ano_publicacao;
        this.status = status;
    }

    // GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(Date ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
