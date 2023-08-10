package model.dao;

import model.entities.Contato;
import model.factory.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    /* CRUD */

    //metodo de salvamento no Banco de Dados - C (Create)
    public void salvando(Contato contato){

        String sqlInsert = "INSERT INTO contato(nome, idade, datacadastro) VALUE (?,?,?)";

        Connection conexao = null; // criando uma variavel cp, Classe de conexao Connection
        PreparedStatement pstm = null; // criando uma variavel com a classe para PreparedStatement para deixar seguro

        //verificando se tem conexao com o Banco de Dados
        try {

            //criando conexao com o Banco de Dados (Mysql)
            conexao = ConexaoFactory.createConnectionMysql();

            //valores esperados pela Query para o banco de dados
            pstm = conexao.prepareStatement(sqlInsert);
            pstm.setString(1, contato.getNome());
            pstm.setInt(2,contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            pstm.execute();
            System.out.println("Cadastrado com Sucesso");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                if (conexao != null){
                    conexao.close();

                } else if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                System.out.println( e.getMessage());
            }
        }

    }

//======================================================================================================================
    //metodo de Listagem dos dados do banco de dados - R (Read)
    public List<Contato> listagemContatos (){

        String sqlListagem = "SELECT * FROM contato"; // salvando o sintaxe SQL em uma variavel

        List<Contato> listaContatos = new ArrayList<>(); // instanciando a classe Contato como lista

        Connection conexao = null; // criando uma variavel cp, Classe de conexao Connection
        PreparedStatement pstm = null; // criando uma variavel com a classe para PreparedStatement para deixar seguro
        ResultSet rset = null; //ele reculpera os dados no banco de dados

        try {

            //criando conexao com o Banco de Dados (Mysql)
            conexao = ConexaoFactory.createConnectionMysql();
            pstm = conexao.prepareStatement(sqlListagem);
            rset = pstm.executeQuery();

            //percorrendo o banco de dados para pegar as informacoes
            while (rset.next()){
                Contato contatos = new Contato();

                //recuperando informacao pelo ID
                contatos.setId(rset.getLong("id"));

                //recuperando informacao pelo Nome
                contatos.setNome(rset.getString("nome"));

                //recuperando informacao pelo idade
                contatos.setIdade(rset.getInt("idade"));

                //recuperando informacao pelo datacadastro
                contatos.setDataCadastro(rset.getDate("datacadastro"));

                //salvando todas as informacoes na lista
                listaContatos.add(contatos);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {

            try {
                if (rset != null){
                    rset.close();

                } else if (pstm != null) {
                    pstm.close();
                } else if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                System.out.println("Nao foi possivel fecha: " + e.getMessage());
            }
        }
        return listaContatos;
    }
}
