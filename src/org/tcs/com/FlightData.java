package org.tcs.com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class FlightData
 */
@WebServlet("/FlightData")
public class FlightData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String user = "U732172";
	String password = "dec123";
	Connection connection = null;
	Statement statement = null;
	Statement stat = null;
	Statement stat1 = null;
	Statement stat2 = null;
	Statement stat3 = null;
	Statement stat4 = null;
	Statement stat5 = null;
	ResultSet rs = null;
	ResultSet rc = null;
	ResultSet rd = null;
	ResultSet ra = null;
	flightbean flight = new flightbean();
	Map<Boolean, flightbean> checkPassport;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlightData() {
		super();
		// TODO Auto-generated constructor stub

		try {

			System.out.println("inside constructor");
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			connection = DriverManager.getConnection("jdbc:db2://172.16.111.107:591/STPDR11:user=U732172;password=dec123;");

			System.out.println("Connected successfully");

			statement = connection.createStatement();
			stat = connection.createStatement();
			stat1 = connection.createStatement();
			stat2 = connection.createStatement();
			stat3 = connection.createStatement();
			stat4 = connection.createStatement();
			stat5 = connection.createStatement();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		checkPassport = new HashMap<Boolean, flightbean>();
		String passport = request.getParameter("passport_num");

		try {
			flightbean obj = offerData(passport);
			if (checkPassport.containsKey(true)) {
				ArrayList<FoodJoint> al = FoodJoint();
				RequestDispatcher view = request
						.getRequestDispatcher("DisplayData.jsp");
				request.setAttribute("data", obj);
				request.setAttribute("food", al);
				view.forward(request, response);
			} else {
				RequestDispatcher view = request.getRequestDispatcher("Error.jsp");
				view.forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public flightbean offerData(String passport_number) throws Exception {

		String sql = "SELECT PASS_NO,FLIGHT_NUM,DOJ,PERSON_NAME FROM U737966.PERSON_INFO WHERE PASS_NO='"
				+ passport_number.toUpperCase() + "'";
		try {

			rs = statement.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					flight.setPassport(rs.getString("PASS_NO"));
					flight.setPerson_name(rs.getString("PERSON_NAME"));
					flight.setFlight_num(rs.getString("FLIGHT_NUM"));
					flight.setDoj(rs.getString("DOJ"));
					String sql_stat = "SELECT FLIGHT_DEST,FLIGHT_NAME,ARRIVAL_TIME,FLIGHT_SOURCE FROM U737966.AIRLINE_INFO WHERE FLIGHT_NUM='"
							+ flightbean.getFlight_num() + "'";
					System.out.println("Flight Number:"+ flightbean.getFlight_num());

					rc = stat2.executeQuery(sql_stat);
					while (rc.next()) {
						flight.setSource(rc.getString("FLIGHT_SOURCE"));
						System.out.println("\n Source:" + flightbean.getSource());

						flight.setDestination(rc.getString("FLIGHT_DEST"));
						System.out.println("\nDestination:"+ flightbean.getDestination());

						flight.setFlight_name(rc.getString("FLIGHT_NAME"));
						flight.setArrival_time(rc.getString("ARRIVAL_TIME"));

						System.out.println("\nAirways Name:"+ flightbean.getFlight_name());
						System.out.println("\nScheduled Arrival Time:"+ flight.getArrival_time());

					}

					checkPassport.put(true, flight);

				}
			} 
			else {
				System.out.println("Invalid passport number");
				checkPassport.put(false, flight);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// rb.close();
			// ra.close();
			// rd.close();
			// rc.close();
			rs.close();

			// statement.close();

			// stat2.close();

		}

		return flight;

	}

	public ArrayList<FoodJoint> FoodJoint() throws SQLException {

		ArrayList<FoodJoint> al1 = new ArrayList<FoodJoint>();

		String subquery = "SELECT FOODJNT_NAME,OFFER_ITEM,OFFER_DETAILS,OFFER_END_TIME FROM U737966.FOODJNT_INFO "
				+ "WHERE OFFER_DATE='" + flight.getDoj() + "'";

		try {

			ResultSet rb = stat1.executeQuery(subquery);
			System.out.println("\nFOOD JOINT DETAILS FOR YOUR ARRIVAL TIME OF "+ flight.getArrival_time());
			System.out.println("-----------------------------------------------");
			while (rb.next()) {
				FoodJoint fd = new FoodJoint();
				fd.setFoodjnt_name(rb.getString("FOODJNT_NAME"));
				fd.setOffer_item(rb.getString("OFFER_ITEM"));
				fd.setOffer_details(rb.getString("OFFER_DETAILS"));
				fd.setOffer_end_time(rb.getString("OFFER_END_TIME"));

				System.out.println("Food Joint:" + fd.getFoodjnt_name());
				System.out.println("Offer Item:" + fd.getOffer_item());
				System.out.println("Offer Details:" + fd.getOffer_details());
				System.out.println("Offer End Time:" + fd.getOffer_end_time());
				System.out.println("\n");
				al1.add(fd);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al1;

    }

}
