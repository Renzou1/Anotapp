package org.example;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Anotacao extends JFrame {
    //data tb
    private String titulo;
    private String anotacao;
    private int pagina;
    private int index;

    private LocalDateTime data;
    Anotacao(String titulo, String anotacao, int pagina)
    {
        this.titulo = titulo;
        this.anotacao = anotacao;
        this.pagina = pagina;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.data = LocalDateTime.now();
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }
    public void setAnotacao(String anotacao)
    {
        this.anotacao = anotacao;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public String getAnotacao()
    {
        return anotacao;
    }

    public int getPagina() { return pagina; }

    public void setPagina(int pagina) { this.pagina = pagina; }

    public LocalDateTime getData()
    {
        return data;
    }
    public int getIndex() { return index; }

    public void setIndex(int index) { this.index = index; }

}
