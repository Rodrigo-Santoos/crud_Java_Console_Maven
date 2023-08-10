package model.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoFactory {

    //nome usuario no mysql
    private static final String USERNAME = "root";

    //senha usuario no mysql
    private static final String PASSWORD = "root";

    //caminho do banco, porta e o nome do banco | (opcional ["jdbc:mysql://localhost/banco"])
    private static final  String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda?autoReconnect=true&useSSL=false";

    //==================================================================================================================
    //metodo de conexao com Banco de Dados Mysql
    public static Connection createConnectionMysql(){
        Connection conexao = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //caminho até a classe de Driver do banco | (opcional[com.mysql.jdbc.Driver])
            conexao = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); //conectando com o banco

        } catch (ClassNotFoundException e) {
            System.out.println("Driver do Banco de Dados nao localizado.");

        } catch (SQLException sq){
            System.out.println("Ocorreu um erro ao acessar o banco: " + sq.getMessage());
        }
        return conexao;
    }

    //==================================================================================================================
    public static void main(String[] args) {

        //recuparando conexao com o Banco de Dados mysql
        Connection conMysql = createConnectionMysql();

        //verificando se a conexao é null
        if (conMysql != null){
            try {
                conMysql.close();

            } catch (SQLException e) {
                System.out.println("Falha ao fecha a conexao com o banco");
            }
        }


    }
}
