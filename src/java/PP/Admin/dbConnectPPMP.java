/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PP.Admin;

import static Scripts.OSValidator.isUnix;
import static Scripts.OSValidator.isWindows;
import com.mysql.jdbc.ResultSetMetaData;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SIXTYFOURBIT
 */
public final class dbConnectPPMP {
public  Connection connect = null;
    public ResultSet rs,rs_1,rs1,rs2,rs3,rs4,rs5,rs6;
    public Statement  state,state1,state_1,state2,state3,state4,state5,state6;
    PreparedStatement pst;
    String mydrive = "";
    public static int issetdbcalled_file_exists = 2;
    public static int issetdbcalled_exception = 2;
    public static int issetdbcalled_wrongpword = 2;
    public ResultSetMetaData rsmd;
    public String query,query1;
    public PreparedStatement ps;
    public  String dbsetup[] = new String[4];

    public dbConnectPPMP() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhc","root", "");
           





            //if the saved host name is less than 2 letters long, then thats not a genuine host name

            URL location = dbConnectPPMP.class.getProtectionDomain().getCodeSource().getLocation();


            mydrive = location.getFile().substring(1, 2);
            /**   
             * Ensure that the file which holds the db exists.
             * 
             * If it does not exist, call db.jsp which creates a text file with db settings, then opens index.jsp.
             * 
             * If the password saved isnt right, then call the jsp file again to ask user to save settings appropriately
             * 
             * 
             **/
            if (getdbsettings(mydrive) == true) {

                //String myfile=getServletContext().getRealPath("/dbsettings.txt");

                if (dbsetup[0] != null) {

                    if(dbsetup[3]==null){
                  connect = DriverManager.getConnection("jdbc:mysql://" + dbsetup[0] + "/" + "ppmp" , dbsetup[2],"");
}
                    else{
                    connect = DriverManager.getConnection("jdbc:mysql://" + dbsetup[0] + "/" +"ppmp", dbsetup[2],dbsetup[3]);
                    }


                } else {
                    //call the page thats sets up the database
                    //use if to avoid calling the db.jsp twice.
                    if (issetdbcalled_wrongpword %2== 0) {
                        calldbjsp();
                        issetdbcalled_wrongpword ++;
                    }
                    else{
                     issetdbcalled_wrongpword ++;
                    }

                }


                //initialize this three values
                issetdbcalled_exception = 2;
                issetdbcalled_file_exists = 2;
                issetdbcalled_wrongpword = 2;

                 state=(Statement)connect.createStatement();
            state_1=(Statement)connect.createStatement();
            state1=(Statement)connect.createStatement();
            state2=(Statement)connect.createStatement();
            state3=(Statement)connect.createStatement();
            state4=(Statement)connect.createStatement();
            state5=(Statement)connect.createStatement();
            state6=(Statement)connect.createStatement();


            }


        } catch (Exception ex) {
            Logger.getLogger(dbConnectPPMP.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR WHILE CONNECTING TO DATABASE. CHECK YOUR CONNECTION CREDENTIALS SETTINGS in dbConn.java");
            //error in dbase configuration 
            //call the jsp page that does configuration

            if (issetdbcalled_exception%2 == 0) {
                calldbjsp();
                issetdbcalled_exception ++;
            }
            else{
            issetdbcalled_exception ++;
            }

        }
    }

    public final boolean getdbsettings(String drive) {
        boolean worked = true;

        try {

  String dbconnpath =null;
            
            if (isWindows()) {
			dbconnpath = drive + ":/PPT_UPLOADS/DBCONNECTION/DO_NOT_DELETE/_/_/./dbconnection.txt";

		}
            else if (isUnix()) {
	dbconnpath = "PPT_UPLOADS/DBCONNECTION/DO_NOT_DELETE/_/_/dbconnection.txt";

		}

           
            //File file = new File("");
            // InputStream inStream = getClass().getResourceAsStream("Web-INF/classes/dbconnection.txt");  
            FileInputStream fstream = new FileInputStream(dbconnpath);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String stLine;
            //Read File Line By Line
            int count = 0;
            while ((stLine = br.readLine()) != null) {

                // Print the content on the console


                dbsetup[count] = stLine;


                if (count < 4) {
                    count++;
                }
            }
            //Close the input stream
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(dbConnectPPMP.class.getName()).log(Level.SEVERE, null, ex);


            System.out.println("MY VALUE:" + issetdbcalled_file_exists);

            if (issetdbcalled_file_exists%2 == 0) {
                calldbjsp();
                issetdbcalled_file_exists++;
            }
            else{
            issetdbcalled_file_exists++;
            }

            System.out.println("MY VALUE:" + issetdbcalled_file_exists);


            System.out.println("ERROR:      FILE NOT FOUND");
            worked = false;

        }

        return worked;

    }

    public void calldbjsp() {
        try {

            //not so good for now because the host name is static


            String url = "http://localhost:8080/ProgramProgress/db.jsp";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            //getdbsettings("M");
        } catch (IOException ex) {
            Logger.getLogger(dbConnectPPMP.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
