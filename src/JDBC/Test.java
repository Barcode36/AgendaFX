/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import com.mysql.jdbc.Connection;

/**
 *
 * @author Samuel
 */
public class Test {
    
    public static void main(String[] args) {
        
         Connection connection = ConnectionFactory.getConnection();
         ConnectionFactory.closeConnection(connection);
        
         /* 
         *se nao houver mensagem de erro, significa que conectou com sucesso.
         *if there is no error message, it means that you connected successfully.
         */
         
    }
    
}
