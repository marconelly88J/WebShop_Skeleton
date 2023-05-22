package dao;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Item;

import java.util.ArrayList;


public class DAO {
      private DataSource ds;

// DEFINICIJA KONEKCIONIH STRINGOVA
	private static String SELECT_ALL_ITEMS = "SELECT * FROM items";
	private static String SELECT_ITEM_BY_ID = "SELECT * FROM items WHERE item_id = ?";
	private static String INSERT_CART = "INSERT INTO `cart` (`cart_items`) VALUES (?)";
	
	// DEFINICIJA KONSTRUKTORA ZA PODESAVNJE KONEKCIJE � UVEK ISTO
	public DAO(){
	try {
		InitialContext cxt = new InitialContext();
		if ( cxt == null ) { 
		} 
		ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/mysql" ); 
		if ( ds == null ) { 
		} 		
		} catch (NamingException e) {
		}
	}
	// DEFINICIJA METODE 
	public ArrayList<Item> selectAllItems(){
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ArrayList<Item> listOfItems = new ArrayList<Item>();
		Item item = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_ALL_ITEMS);
		
			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){ 
				
				item = new Item();
				
				item.setItem_id(rs.getInt("item_id")); 
				item.setItem_name(rs.getString("item_name"));
				item.setItem_price(rs.getFloat("item_price"));
				
				listOfItems.add(item);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listOfItems; 
	}
	
	/////////////////////////////////////////////////////////
	
public Item selectItemById(int id){
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		Item item = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECT_ITEM_BY_ID);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, id);
			pstm.execute();

			rs = pstm.getResultSet();

			if(rs.next()){ 
				item = new Item();
				item.setItem_id(rs.getInt("item_id")); 
				item.setItem_name(rs.getString("item_name"));
				item.setItem_price(rs.getFloat("item_price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

public void insertCartItems(Array cartItems) {
	
	Connection con = null;
	PreparedStatement pstm = null;
			
        try {
		con = ds.getConnection();
		pstm = con.prepareStatement(INSERT_CART);

		// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
		pstm.setArray(1, cartItems);
		pstm.execute();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
	
	// DEFINICIJA OSTALIH METODA ... 
}
