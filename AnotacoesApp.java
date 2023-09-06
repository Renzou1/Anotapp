package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Dimension2D;
import java.util.*;
import java.util.List;

import static java.lang.String.valueOf;

public class AnotacoesApp extends JFrame implements ActionListener{

    private int max_notes = 10;
    private int pagina_atual = 1;
    private int note_index = 0;

    private int elementos_na_pagina = 0;
    private JButton[] open_note_buttons;
    private JButton[] remove_note_buttons;
    private JButton newAnotation_b;
    private JButton data_b;
    private JButton titulo_b;
    private JButton theme_b;
    private JButton next_page_b;
    private JButton previous_page_b;
    private JLabel label;
    private JLabel page_label;
    private List<Anotacao> listaDeAnotacoes;
    private JTextArea[] display;
    AnotacoesApp() {
        listaDeAnotacoes = new ArrayList<>();
        display = new JTextArea[max_notes];
        open_note_buttons = new JButton[max_notes];
        remove_note_buttons = new JButton[max_notes];

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(400, 600);
        this.setTitle("Anotapp");
        this.setResizable(false);
    }

    public void criarBotoes() {
        newAnotation_b = new JButton();
        newAnotation_b.setBounds(275, 10, 100, 50);
        newAnotation_b.setVisible(true);
        newAnotation_b.setFont(new Font("Comic Sans", Font.PLAIN, 30));
        newAnotation_b.setText("+");
        newAnotation_b.setFocusable(false);
        newAnotation_b.addActionListener(this);

        data_b = new JButton();
        data_b.setBounds(275, 200, 100, 50);
        data_b.setVisible(true);
        data_b.setText("Data");
        data_b.setFocusable(false);
        data_b.addActionListener(this);

        titulo_b = new JButton();
        titulo_b.setBounds(275, 275, 100, 50);
        titulo_b.setVisible(true);
        titulo_b.setText("Titulo");
        titulo_b.setFocusable(false);
        titulo_b.addActionListener(this);

        theme_b = new JButton();
        theme_b.setBounds(275, 500, 100, 50);
        theme_b.setVisible(true);
        theme_b.setText("Tema");
        theme_b.setFocusable(false);
        theme_b.addActionListener(this);

        previous_page_b = new JButton();
        previous_page_b.setBounds(25, 500, 100, 50);
        previous_page_b.setVisible(true);
        previous_page_b.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        previous_page_b.setText("<<");
        previous_page_b.setFocusable(false);
        previous_page_b.addActionListener(this);

        next_page_b = new JButton();
        next_page_b.setBounds(125, 500, 100, 50);
        next_page_b.setVisible(true);
        next_page_b.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        next_page_b.setText(">>");
        next_page_b.setFocusable(false);
        next_page_b.addActionListener(this);

        label = new JLabel();
        label.setText("Ordenar:");
        label.setBounds(300, 150, 100, 50);
        label.setVisible(true);

        page_label = new JLabel();
        page_label.setText(valueOf(pagina_atual));
        page_label.setBounds(250, 475, 100, 100);
        page_label.setVisible(true);

        for (int i = 0; i < max_notes; i++) {
            open_note_buttons[i] = new JButton();
            open_note_buttons[i].setBounds(125, 10 + 50 * i, 50, 20);
            open_note_buttons[i].setVisible(false);
            open_note_buttons[i].setText("<");
            open_note_buttons[i].addActionListener(this);
            open_note_buttons[i].setFocusable(false);
            remove_note_buttons[i] = new JButton();
            remove_note_buttons[i].setBounds(175, 10 + 50 * i, 50, 20);
            remove_note_buttons[i].setVisible(false);
            remove_note_buttons[i].setText("x");
            remove_note_buttons[i].addActionListener(this);
            remove_note_buttons[i].setFocusable(false);
            display[i] = new JTextArea();
            display[i].setBounds(25, 10 + 50 * i, 100, 20);
            display[i].setEditable(false);
            display[i].setVisible(false);
            this.add(display[i]);
            this.add(open_note_buttons[i]);
            this.add(remove_note_buttons[i]);
        }

        this.add(newAnotation_b);
        this.add(data_b);
        this.add(titulo_b);
        this.add(theme_b);
        this.add(previous_page_b);
        this.add(next_page_b);
        this.add(label);
        this.add(page_label);

        this.trocarTema();
        this.trocarTema();
    }
    public void updatePageGraphics(int pagina_atual) {
        for (int i = 0; i < max_notes; i++) {
            open_note_buttons[i].setVisible(false);
            open_note_buttons[i].setEnabled(false);
            remove_note_buttons[i].setVisible(false);
            remove_note_buttons[i].setEnabled(false);
            display[i].setVisible(false);
            display[i].setEnabled(false);
        }

        note_index = 0;
        for (Anotacao i : listaDeAnotacoes) {
            if (i.getPagina() == pagina_atual) {
                display[note_index].setText(i.getTitulo());
                display[note_index].setVisible(true);
                display[note_index].setEnabled(true);
                open_note_buttons[note_index].setVisible(true);
                open_note_buttons[note_index].setEnabled(true);
                remove_note_buttons[note_index].setVisible(true);
                remove_note_buttons[note_index].setEnabled(true);
                note_index++;
            }
        }

        page_label.setText(valueOf(pagina_atual));
    }
    public void adicionarAnotacao() {
        this.setEnabled(false);
        AnotacoesApp anotacoesApp = this;
        JFrame anotacaoWindow = new JFrame("Nova Anotacao");
        anotacaoWindow.setResizable(false);
        anotacaoWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        anotacaoWindow.setVisible(true);
        anotacaoWindow.setSize(600, 600);
        anotacaoWindow.setLayout(null);
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setVisible(true);
        textArea.setBounds(15, 100, 550, 350);
        textArea.setText("Anotacao");

        JTextField titulo = new JTextField();
        titulo.setVisible(true);
        titulo.setBounds(15, 25, 550, 25);
        titulo.setText("Titulo");

        JButton salvar = new JButton();
        salvar.setVisible(true);
        salvar.setBounds(150, 480, 100, 50);
        salvar.setText("Salvar");
        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Anotacao temp = new Anotacao(titulo.getText(), textArea.getText(), anotacoesApp.pagina_atual);
                listaDeAnotacoes.add(temp);
                temp.setIndex(note_index);
                display[note_index].setText(titulo.getText());
                note_index++;
                anotacoesApp.updatePageGraphics(pagina_atual);
                anotacaoWindow.dispose();
                anotacoesApp.setEnabled(true);
                anotacoesApp.toFront();
            }
        });

        JButton cancelar = new JButton();
        cancelar.setVisible(true);
        cancelar.setBounds(350, 480, 100, 50);
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anotacaoWindow.dispose();
                anotacoesApp.setEnabled(true);
                anotacoesApp.toFront();
            }
        });

        anotacaoWindow.add(textArea);
        anotacaoWindow.add(titulo);
        anotacaoWindow.add(salvar);
        anotacaoWindow.add(cancelar);

        anotacaoWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                anotacoesApp.setEnabled(true);
                anotacoesApp.toFront();
            }
        });

    }
    public void removerAnotacao(int i)
    {
        for(int j = 0; j < listaDeAnotacoes.size(); j++)
        {
            if(listaDeAnotacoes.get(j).getPagina() == pagina_atual && listaDeAnotacoes.get(j).getIndex() == i)
            {
                listaDeAnotacoes.remove(j);
                this.remakeIndexes();
                this.updatePageGraphics(pagina_atual);
            }
        }
    }

    public void editarAnotacao(Anotacao temp, JTextField titulo, JTextArea textArea)
    {
        temp.setTitulo(titulo.getText());
        temp.setAnotacao(textArea.getText());
    }
    public void ordenarData()
    {
        List<Anotacao> temp = new ArrayList<>();
        int page = 0;
        if(listaDeAnotacoes.size() == 0) return;
        int lowest_int = 0;

        while(listaDeAnotacoes.size() != 0) {
            String lowest = String.valueOf(listaDeAnotacoes.get(0).getData());
            lowest_int = 0;

            for (int i = 0; i < listaDeAnotacoes.size(); i++)
            {
                if (String.valueOf(listaDeAnotacoes.get(i).getData()).compareTo(lowest) < 0)
                {
                    lowest = String.valueOf(listaDeAnotacoes.get(i).getData());
                    lowest_int = i;
                }
            }
            temp.add(listaDeAnotacoes.get(lowest_int));
            listaDeAnotacoes.remove(lowest_int);
        }
        listaDeAnotacoes = temp;
        this.remakeIndexes();
        pagina_atual = 1;
        this.updatePageGraphics(pagina_atual);
    }
    public void remakeIndexes()
    {
        for(int i = 0; i < listaDeAnotacoes.size(); i++)
        {
            listaDeAnotacoes.get(i).setIndex((int)((i % 10)));
            listaDeAnotacoes.get(i).setPagina((int)(i/10 + 1));
        }
    }
    public void ordenarTitulo() {
        List<Anotacao> temp = new ArrayList<>();
        int page = 0;
        if(listaDeAnotacoes.size() == 0) return;
        int lowest_int = 0;

        while(listaDeAnotacoes.size() != 0) {
            String lowest = listaDeAnotacoes.get(0).getTitulo();
            lowest_int = 0;

            for (int i = 0; i < listaDeAnotacoes.size(); i++)
            {
                if (listaDeAnotacoes.get(i).getTitulo().compareTo(lowest) < 0)
                {
                    lowest = listaDeAnotacoes.get(i).getTitulo();
                    lowest_int = i;
                }
            }
            temp.add(listaDeAnotacoes.get(lowest_int));
            listaDeAnotacoes.remove(lowest_int);
        }
        listaDeAnotacoes = temp;
        this.remakeIndexes();
        pagina_atual = 1;
        this.updatePageGraphics(pagina_atual);
    }

    public void trocarTema() {
        if (this.getContentPane().getBackground() == Color.BLACK) {
            this.getContentPane().setBackground(null);
            newAnotation_b.setBackground(null);
            data_b.setBackground(null);
            theme_b.setBackground(null);
            titulo_b.setBackground(null);
            next_page_b.setBackground(null);
            previous_page_b.setBackground(null);
            label.setForeground(null);
            page_label.setForeground(null);
            for(int i = 0; i < max_notes; i++)
            {
                open_note_buttons[i].setBackground(null);
                remove_note_buttons[i].setBackground(null);
            }
        } else {
            this.getContentPane().setBackground(Color.BLACK);
            newAnotation_b.setBackground(Color.CYAN);
            data_b.setBackground(Color.CYAN);
            theme_b.setBackground(Color.CYAN);
            titulo_b.setBackground(Color.CYAN);
            next_page_b.setBackground(Color.CYAN);
            previous_page_b.setBackground(Color.CYAN);
            label.setForeground(Color.WHITE);
            page_label.setForeground(Color.WHITE);
            for(int i = 0; i < max_notes; i++)
            {
                open_note_buttons[i].setBackground(Color.CYAN);
                remove_note_buttons[i].setBackground(Color.CYAN);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newAnotation_b) {
            if(note_index < max_notes) {
                this.adicionarAnotacao();
            }  else{
                JOptionPane.showMessageDialog(this, "Limite da pagina atingido");
            }
        }
        if (e.getSource() == data_b) {
            this.ordenarData();
        }
        if (e.getSource() == titulo_b) {
            this.ordenarTitulo();
        }
        if (e.getSource() == theme_b) {
            this.trocarTema();
        }
        if (e.getSource() == next_page_b) {
            pagina_atual++;
            updatePageGraphics(pagina_atual);
        }
        if (e.getSource() == previous_page_b) {
            if (pagina_atual > 1) {
                pagina_atual--;
            }
            updatePageGraphics(pagina_atual);
        }
        for (int i = 0; i < max_notes; i++)
        {
            if (e.getSource() == open_note_buttons[i])
            {
                for (Anotacao j : listaDeAnotacoes)
                {
                    if (j.getPagina() == pagina_atual && j.getIndex() == i)
                    {
                        this.setEnabled(false);
                        AnotacoesApp anotacoesApp = this;
                        JFrame anotacaoWindow = new JFrame(String.valueOf(j.getData()));
                        anotacaoWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        anotacaoWindow.setVisible(true);
                        anotacaoWindow.setSize(600, 600);
                        anotacaoWindow.setLayout(null);
                        anotacaoWindow.setResizable(false);
                        JTextArea textArea = new JTextArea();
                        textArea.setLineWrap(true);
                        textArea.setWrapStyleWord(true);
                        textArea.setVisible(true);
                        textArea.setBounds(15, 100, 550, 350);
                        textArea.setText(j.getAnotacao());

                        JTextField titulo = new JTextField();
                        titulo.setVisible(true);
                        titulo.setBounds(15, 25, 550, 25);
                        titulo.setText(j.getTitulo());

                        JButton salvar = new JButton();
                        salvar.setVisible(true);
                        salvar.setBounds(150, 480, 100, 50);
                        salvar.setText("Salvar");
                        salvar.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                anotacoesApp.editarAnotacao(j, titulo, textArea);
                                anotacaoWindow.dispose();
                                anotacoesApp.setEnabled(true);
                                anotacoesApp.toFront();
                                anotacoesApp.updatePageGraphics(pagina_atual);
                            }
                        });

                        JButton cancelar = new JButton();
                        cancelar.setVisible(true);
                        cancelar.setBounds(350, 480, 100, 50);
                        cancelar.setText("Cancelar");
                        cancelar.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                anotacaoWindow.dispose();
                                anotacoesApp.setEnabled(true);
                                anotacoesApp.toFront();
                            }
                        });

                        anotacaoWindow.add(textArea);
                        anotacaoWindow.add(titulo);
                        anotacaoWindow.add(salvar);
                        anotacaoWindow.add(cancelar);

                        anotacaoWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                anotacoesApp.setEnabled(true);
                                anotacoesApp.toFront();
                            }
                        });
                    }
                }
            }
        }
        for (int i = 0; i < max_notes; i++)
        {
            if (e.getSource() == remove_note_buttons[i])
            {
                int result = JOptionPane.showConfirmDialog(this, "Remover anotacao?");
                if(result == JOptionPane.YES_OPTION)
                {
                    this.removerAnotacao(i);
                }
            }
        }
    }

}