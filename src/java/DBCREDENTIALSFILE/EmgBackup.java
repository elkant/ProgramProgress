/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCREDENTIALSFILE;

import PP.Admin.dbConnect;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SIXTYFOURBIT
 */
public final class EmgBackup {
    
    
    public EmgBackup(){
    
  dobackup();
    
    }
    
    
    
    public boolean  dobackup(){
    
          try {
            URL location = db_backup.class.getProtectionDomain().getCodeSource().getLocation();
             String  mydrive = location.getFile().substring(1, 2);
             
             String password="";
             String user="";
             String dbname="";   
             String storagepath=mydrive+":\\PPT_UPLOADS\\DBCONNECTION\\DO_NOT_DELETE\\";
             
             dbConnect conn= new dbConnect();
             
             
             if(conn.dbsetup[3]!=null){
             password=conn.dbsetup[3];
                 
             
             }
             
             
             
             if(conn.dbsetup[2]!=null){
                 
             user=conn.dbsetup[2];
                 
             
             }
             
             
             
             if(conn.dbsetup[1]!=null){
                 
             dbname=conn.dbsetup[1];                 
             
             }
             
             System.out.println("PASSWORD IS :"+password);
             System.out.println("USER IS :"+user);
             System.out.println("DBNAME IS :"+dbname);
             
             Calendar cal = Calendar.getInstance();

     int   year = cal.get(Calendar.YEAR);
      int  month = cal.get(Calendar.MONTH) + 1;
      int  date = cal.get(Calendar.DAY_OF_MONTH);
      int  hour = cal.get(Calendar.HOUR_OF_DAY);
      int  min = cal.get(Calendar.MINUTE);
      int  sec = cal.get(Calendar.SECOND);
             
      String dat=year+"_"+month+"_"+date+"_"+hour+"_"+min+"_"+sec;
             
      //String prepa="set path=M:\\wamp\\bin\\mysql\\mysql5.1.36\\bin";       
      
              
               //all comps
               String command2=mydrive+":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump -u"+user+" -p"+password+" "+dbname+" -r "+storagepath+dbname+"_"+dat+".sql";
               
               //manus comps
               String backup=mydrive+":\\wamp\\bin\\mysql\\mysql5.1.36\\bin\\mysqldump -u "+user+" -p"+password+" "+dbname+" -r "+storagepath+dbname+"_"+dat+".sql";
              //dont run this for now
               String command5=mydrive+":\\Program Files\\MySQL\\MySQL Server 5.1\\bin\\mysqldump -u "+user+" -p"+password+" "+dbname+"  -r "+storagepath+dbname+"_"+dat+".sql";
               //godfreys comp
               String command3="D:\\wamp\\bin\\mysql\\bin\\mysqldump -u "+user+" -p"+password+" "+dbname+" -r "+storagepath+dbname+"_"+dat+".sql";
            // Process prs=  Runtime.getRuntime().exec(prepa);
              
               Runtime runtime = Runtime.getRuntime();
               runtime.exec(backup);
               runtime.exec(command2);
               runtime.exec(command3);
               runtime.exec(command5);
              
               System.out.println("Backing Up The database");
               
               
               
               // int processComplete = prs.waitFor();
 
            
            
        } catch (IOException ex) {
            System.out.println("ERROR WHILE PERFORMING DATA BACKUP   : "+ex);
            Logger.getLogger(db_backup.class.getName()).log(Level.SEVERE, null, ex);
        }
    return true;
    
    }
    
}
