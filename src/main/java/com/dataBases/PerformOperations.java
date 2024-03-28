package com.dataBases;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.*;

/*
 * @Author vishalbala_here 
 * 9079018358
 * built in Java using JavaFX for the front-end and Hibernate for the Database
 * This class is used to perform database related operations
 * 
 * 
*/
public class PerformOperations {
	private SessionFactory factory;// = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	private Session session;
	private Transaction transaction;

	public PerformOperations() {
		factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	}
	
	public String login(String username , String password) {
		session = factory.openSession(); 
		EmpDAO uniqueResult;
		Query<EmpDAO> loginQuery = session.createQuery(
	            "from EmpDAO WHERE userName = :username AND passWord = :password", EmpDAO.class);
	    loginQuery.setParameter("username", username);
	    loginQuery.setParameter("password", password);
	    try {
	    	uniqueResult = loginQuery.uniqueResult();
	    	return uniqueResult.getPost();
	    }catch (NullPointerException exp) {
			return null;
		}finally {
			 session.close();
		}
	   
		
	}

	public int saveData(InventryDAO object) {
		// TODO Getting the Session Object
		session = factory.openSession();
		// Getting the transaction object
		transaction = session.beginTransaction();
		// Saving data to the DB
		int save = (int) session.save(object);
		// Commiting changes
		transaction.commit();
		// Closing the session object
		session.close();
		return save > 0 ? 1 : -1;
	}

	public void updateInventry(InventryDAO obj) {
		session = factory.openSession();

		transaction = session.beginTransaction();

		session.update(obj);
		transaction.commit();
		session.close();
	}

	public int updateQuantity(InventryDAO object, String sold, String id) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		Query query = session.createQuery("UPDATE InventryDAO SET itemsSold = " + sold + "WHERE id = " + id);

		int rowsUpdated = query.executeUpdate();
		
		System.out.println("Rows updated: " + rowsUpdated);

		transaction.commit();
		session.close();
		return rowsUpdated;
	}

	public void removeSingleDataFromInventory(String id) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		Query<InventryDAO> query = session.createQuery("DELETE FROM InventryDAO WHERE id = " + id
				,InventryDAO.class);
		query.executeUpdate();
		transaction.commit();
		session.close();
	}

	public void removeData() {
		session = factory.openSession();
		transaction = session.beginTransaction();
		Query<InventryDAO> query = session.createQuery("DELETE FROM InventryDAO",InventryDAO.class);
		query.executeUpdate();
		transaction.commit();
		session.close();
	}

	public List<InventryDAO> getValues() {
		session = factory.openSession();
		// session = factory.openSession();
		String hqlQuery = "from InventryDAO";
		Query<InventryDAO> query = session.createQuery(hqlQuery,InventryDAO.class);
		List<InventryDAO> list = query.list();
		session.close();
		return list;
	}

// EMP ============================================//

	public int saveData(EmpDAO object) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		int save = (int) session.save(object);
		System.out.println(save);
		transaction.commit();
		session.close();
		return save > 0 ? 1 : -1;
	}

	public List<EmpDAO> getEmployeeValues() {
		session = factory.openSession();
		// session = factory.openSession();
		String hqlQuery = "from EmpDAO";
		Query<EmpDAO> query = session.createQuery(hqlQuery,EmpDAO.class);
		List<EmpDAO> list = query.list();
		session.close();
		return list;
	}

	public void updateEmployee(EmpDAO dao) {
		session = factory.openSession();

		transaction = session.beginTransaction();

		session.update(dao);
		transaction.commit();
		session.close();
	}

	public int removeEmployee(String id) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		Query<EmpDAO> query = session.createQuery("DELETE FROM EmpDAO WHERE id = " + id ,EmpDAO.class);
		int executeUpdate = query.executeUpdate();
		transaction.commit();
		session.close();
		return executeUpdate;
	}

	public void removeAllEmployee() {
		session = factory.openSession();
		transaction = session.beginTransaction();
		Query<EmpDAO> query = session.createQuery("DELETE  EmpDAO ",EmpDAO.class);
		query.executeUpdate();
		transaction.commit();
		session.close();
	}

	public int upadatePassword(EmpDAO object, String password, String id) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		Query<EmpDAO> query = session.createQuery("UPDATE EmpDAO SET passWord = " + password + " WHERE id = " + id,
				EmpDAO.class);
		int rowsUpdated = query.executeUpdate();
		transaction.commit();
		session.close();
		return rowsUpdated;
	}

	// TODD Bookings related Operations
	public List<BookinDAO> getAllBookings() {
		session = factory.openSession();
		// session = factory.openSession();
		String hqlQuery = "from BookinDAO";
		Query<BookinDAO> query = session.createQuery(hqlQuery,BookinDAO.class);
		List<BookinDAO> list = query.list();
		session.close();
		return list;
	}

	public int saveBooking(BookinDAO object) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		int save = (int) session.save(object);
		System.out.println(save);
		transaction.commit();
		session.close();
		return save > 0 ? 1 : -1;
	}

	public int deleteSingleBooking(String id) {
		session = factory.openSession();
		transaction = session.beginTransaction();
		Query<BookinDAO> query = session.createQuery("DELETE FROM BookinDAO WHERE id = " + id,BookinDAO.class);
		int executeUpdate = query.executeUpdate();
		transaction.commit();
		session.close();
		return executeUpdate;
	}

	public int removeAllBookings() {
		session = factory.openSession();
		transaction = session.beginTransaction();
		Query<BookinDAO> query = session.createQuery("DELETE FROM BookinDAO",BookinDAO.class);
		int executeUpdate = query.executeUpdate();
		transaction.commit();
		session.close();
		return executeUpdate;
	}

	/*
	 * Data Management Queries
	 * 
	 */
//	Data update query Query
	public int updateInBillDatabase(DataManagement data) {
		session = factory.openSession();
		
		String name = data.getName();
		String date = data.getDate();
		int executeUpdate=0;
		String newQuantity;
		String newIncome;

		if (ifAlreadyPurchasedToday(name, date)) {
			// Getting old quantity
//			System.out.println("hello 1");
			Query<DataManagement> oldQT = session.createQuery("from DataManagement WHERE name =:name AND date = :date",
					DataManagement.class);
			oldQT.setParameter("name", name);
			oldQT.setParameter("date", date);

			DataManagement oldDate = oldQT.uniqueResult();
			newQuantity = String
					.valueOf((Float.parseFloat(oldDate.getQuantity()) + Float.parseFloat(data.getQuantity())));
			newIncome = String.valueOf(Float.parseFloat(oldDate.getIncome()) + Float.parseFloat(data.getPrice()));
//			oldDate = data;
			transaction = session.beginTransaction();
			
			
			Query query = session.createQuery("UPDATE DataManagement SET quantity = '" + newQuantity 
					+ "' , income = '" +newIncome 
					+ "' WHERE name = '" +name+"' AND date = '" +date+"'");

			executeUpdate = query.executeUpdate();
			transaction.commit();

		} else {
			transaction = session.beginTransaction();
			session.save(data);
			transaction.commit();
		}

		session.close();

		return executeUpdate;
	}

	public boolean ifAlreadyPurchasedToday(String name, String date) {
		String SQLquery = "from DataManagement WHERE name = :name AND date = :date";
		Query<DataManagement> query = session.createQuery(SQLquery, DataManagement.class);
		query.setParameter("name", name);
		query.setParameter("date", date);
		List<DataManagement> list = query.list();
		return list.size() > 0 ? true : false;
	}
	// Getting AllData of previos date
	public List<DataManagement> previosDayList(String date){
		List<DataManagement> dataList;
		session = factory.openSession();
		transaction = session.beginTransaction();
		String SQLquery = "from DataManagement WHERE date = '" + date+"'";
		dataList = session.createQuery(SQLquery).list();
		session.close();
		return dataList;
	} 
	
	public List<DataManagement> getAllPreviousData(){
		List<DataManagement> dataList;
		session = factory.openSession();
		transaction = session.beginTransaction();
		String SQLquery = "from DataManagement";
		dataList = session.createQuery(SQLquery,DataManagement.class).list();
		session.close();
		return dataList;
	}
}
