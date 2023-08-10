package org.main;

import model.dao.ContatoDAO;
import model.entities.Contato;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProgramaPrincipal {
    public static void main(String[] args) {
        //instanciando as classe
        ContatoDAO contatoDAO = new ContatoDAO();
        Contato contato = new Contato();

        //salavando as informacoes no Banco de Dados
        contato.setNome("Roberto Rodrigues");
        contato.setIdade(51);
        contato.setDataCadastro(new Date());

       // contatoDAO.salvando(contato); // salvando os dados no banco de dados

//======================================================================================================================
        //visualizando e mostrando as informacoes no banco de dados
        for (Contato c : contatoDAO.listagemContatos()){
            System.out.println("ID: " + c.getId());
            System.out.println("Nome : " + c.getNome());
            System.out.println("Idade: " + c.getIdade());
            System.out.println("Data de cadasto: " + c.getDataCadastro());
            System.out.println();
        }
    }
}
