/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCREDENTIALSFILE;

import PP.Admin.backupconn;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 *
 * @author SIXTYFOURBIT
 */
public class db_backup implements Job{

    
      String dbnameppt,dbnamepwp,dbnamehc, dbnamehei,dbuser, dbpassword;
    boolean executed = false;
    String dbpathppt, dbpathpwp, dbpathhc, dbpathhei,importpath;
    String found_folder, full_date, pathhc,pathppt,pathpwp,pathhei, computername, senderofmail;

    String[] myalphabet = {"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
      String filname="";
    String localhost="";
        String[] localhostsplit;
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            //=============================================================================================================
             
            backupconn conn = new backupconn();

           
    Date dat= new Date();
     String date=dat.toString().replace(" ", "_");
            dbnameppt = "programprogress";
            dbnamepwp = "pwp";
            dbnamehc = "hc";
            dbnamehei = "hei";
            dbuser = "root";
            dbpassword = "";
            String nextpage = "";
            found_folder = "";
    //MAKE A DIRECTORY TO STORE THE BACK_UP FILE.
    //        GET CURRENT DATE:
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DATE);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);

            full_date = "Created_On_" + year + "_" + month + "_" + day + "_" + hour + "_" + min + "_" + sec;
         
           
            computername = InetAddress.getLocalHost().getHostName();
            if (1 == 1) {
    //         
                String executeCmdppt = "";
                String executeCmdpwp = "";
                String executeCmdhc = "";
                String executeCmdhei = "";


                if (conn.dbsetup[3] != null) {
                    dbpassword = conn.dbsetup[3];


                }



                if (conn.dbsetup[2] != null) {

                    dbuser = conn.dbsetup[2];


                }



//                if (conn.dbsetup[1] != null) {
//
//                    dbname = conn.dbsetup[1];
//
//                }
                
                 if (conn.dbsetup[0] != null) {

                    localhost = conn.dbsetup[0];
                    localhostsplit=localhost.split(":");

                }
      
                
                filname+=date+"_";
            
                System.out.println("PASSWORD IS :" + dbpassword);
                System.out.println("USER IS :" + dbuser);
              //  System.out.println("DBNAME IS :" + dbname);


                for (int i = 0; i < myalphabet.length; i++) {
                    
                        System.out.println("at position  :  " + myalphabet[i]);
                        String current_drive = myalphabet[i];
                        File f = new File(current_drive + ":\\wamp\\mysql\\bin\\");
                        File f1 = new File(current_drive + ":\\wamp3\\bin\\mysql\\mysql5.6.12\\bin");
                        File f2 = new File(current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin");
                        File f3 = new File(current_drive + ":\\APHIAPLUS");

                        //     CREATE A DIRECTORY AND THE FILE TO HOLD DATA
                        if (f3.exists() && f3.isDirectory()) {
                            //path="C:\\PPT_UPLOADS\\BACKUP\\";
                            pathhc = current_drive + ":\\APHIAPLUS\\ALLSYSTEMSDATA\\SCHEDULEDBACKUPS\\HC_BACKUPS";
                            pathppt = current_drive + ":\\APHIAPLUS\\ALLSYSTEMSDATA\\SCHEDULEDBACKUPS\\PPT_BACKUPS";
                            pathpwp = current_drive + ":\\APHIAPLUS\\ALLSYSTEMSDATA\\SCHEDULEDBACKUPS\\PWP_BACKUPS";
                            pathhei = current_drive + ":\\APHIAPLUS\\ALLSYSTEMSDATA\\SCHEDULEDBACKUPS\\HEI_BACKUPS";
                            
                            
                              importpath = current_drive + ":\\APHIAPLUS\\ALLSYSTEMSDATA\\SCHEDULEDBACKUPS";
                            new File(pathhc).mkdirs();
                            new File(pathpwp).mkdirs();
                            new File(pathppt).mkdirs();
                            new File(pathhei).mkdirs();
                            
                            new File(importpath).mkdirs();
                            dbpathpwp =pathpwp + "\\" + full_date + "_PWP.sql";
                            dbpathhc = pathhc + "\\" + full_date + "_HC.sql";
                            dbpathppt = pathppt + "\\" + full_date + "_PPT.sql";
                            dbpathhei = pathhei + "\\" + full_date + "_HEI.sql";
                        }

                        //select the last timestamp which a backup was picked from.....



                 

                        //conn.st_6.close();


    // CHECK WHICH PROGRAM IS INSTALLED TO ENSURE THAT DATA IS BACKED UP SUCCESSFULLY.             

                        if (f.exists() && f.isDirectory()) {
                            executeCmdppt = current_drive + ":\\wamp\\mysql\\bin\\mysqldump --host="+localhostsplit[0]+"  --port="+ localhostsplit[1] +" --user=" + dbuser + " --password=" + dbpassword + " " + dbnameppt + " -r " + dbpathppt ;
                            executeCmdpwp = current_drive + ":\\wamp\\mysql\\bin\\mysqldump --host="+localhostsplit[0]+"  --port="+ localhostsplit[1] +" --user=" + dbuser + " --password=" + dbpassword + " " + dbnamepwp + " -r " + dbpathpwp ;
                            executeCmdhc = current_drive + ":\\wamp\\mysql\\bin\\mysqldump --host="+localhostsplit[0]+"  --port="+ localhostsplit[1] +" --user=" + dbuser + " --password=" + dbpassword + " " + dbnamehc + " -r " + dbpathhc ;
                            executeCmdhei = current_drive + ":\\wamp\\mysql\\bin\\mysqldump --host="+localhostsplit[0]+"  --port="+ localhostsplit[1] +" --user=" + dbuser + " --password=" + dbpassword + " " + dbnamehei + " -r " + dbpathhei ;

                            found_folder = "it is old wamp";
                        }
                        if (f1.exists() && f1.isDirectory()) {


                            executeCmdppt = current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump --host="+localhostsplit[0]+"  --port="+localhostsplit[1]+" --user=" + dbuser + " --password=" + dbpassword + " " + dbnameppt + " -r " + dbpathppt ;
                            executeCmdpwp = current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump --host="+localhostsplit[0]+"  --port="+localhostsplit[1]+" --user=" + dbuser + " --password=" + dbpassword + " " + dbnamepwp + " -r " + dbpathpwp ;
                            executeCmdhc = current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump --host="+localhostsplit[0]+"  --port="+localhostsplit[1]+" --user=" + dbuser + " --password=" + dbpassword + " " + dbnamehc + " -r " + dbpathhc ;
                            executeCmdhei = current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump --host="+localhostsplit[0]+"  --port="+localhostsplit[1]+" --user=" + dbuser + " --password=" + dbpassword + " " + dbnamehei + " -r " + dbpathhei ;
                            found_folder = "it is new wamp";
                        }
                        if (f2.exists() && f2.isDirectory()) {
                             executeCmdhc = current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump --host="+localhostsplit[0]+"  --port="+localhostsplit[1]+ " --user=" + dbuser + " --password=" + dbpassword + " " + dbnamehc + " -r " + dbpathhc ;
                         
                            executeCmdppt = current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump --host="+localhostsplit[0]+"  --port="+localhostsplit[1]+ " --user=" + dbuser + " --password=" + dbpassword + " " + dbnameppt + " -r " + dbpathppt ;
                            executeCmdpwp = current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump --host="+localhostsplit[0]+"  --port="+localhostsplit[1]+ " --user=" + dbuser + " --password=" + dbpassword + " " + dbnamepwp + " -r " + dbpathpwp ;
                            executeCmdhei = current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump --host="+localhostsplit[0]+"  --port="+localhostsplit[1]+ " --user=" + dbuser + " --password=" + dbpassword + " " + dbnamehei + " -r " + dbpathhei ;
                              found_folder = "it is workbench";
                        }
                  
                }




                System.out.println(found_folder);
                System.out.println(executeCmdhc);
                System.out.println(executeCmdppt);
                System.out.println(executeCmdpwp);
                System.out.println(executeCmdhei);
               
                Process runtimeProcess;
                Process runtimeProcess1;
                Process runtimeProcess2;
                Process runtimeProcess3;
                try {
                    System.out.println("trying to back up the data.");
                    
                
                    runtimeProcess = Runtime.getRuntime().exec(executeCmdpwp);
                    runtimeProcess2 = Runtime.getRuntime().exec(executeCmdhc);
                    runtimeProcess3 = Runtime.getRuntime().exec(executeCmdhei);
                    runtimeProcess1 = Runtime.getRuntime().exec(executeCmdppt);
                 
                    int processComplete = runtimeProcess.waitFor();
                    int processComplete2 = runtimeProcess2.waitFor();
                    int processComplete3 = runtimeProcess3.waitFor();
                    int processComplete1 = runtimeProcess1.waitFor();
                       
                       
                    System.out.println("at 1 is " + processComplete);
                    System.out.println("at 1 is " + processComplete1);
                    System.out.println("at 1 is " + processComplete2);
                    System.out.println("at 1 is " + processComplete3);
                    if (processComplete == 0) {
    //                SEND TO THE MAIL BACKED UP DATA.
                        System.out.println("PPT Autoback Created Successfully");


                        }
                     else {
                        System.out.println("Could not create the backup");
                       
                    }
                    
                     if (processComplete1 == 0) {
   
                        System.out.println("PPT Autoback Created Successfully");


                        }
                     else {
                        System.out.println("Could not create the backup");
                       
                    }
                    
                    if (processComplete2 == 0) {

                        System.out.println("HC Autoback Created Successfully");


                        }
                     else {
                        System.out.println("Could not create the backup");
                       
                    } 
                    
                    if (processComplete3 == 0) {

                        System.out.println("HEI Autoback Created Successfully");


                        }
                     else {
                        System.out.println("Could not create the backup");
                       
                    }
                    
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                   
                }


            }
            
         
            
            
            
            
            
            //==============================================================================================================
        } catch (UnknownHostException ex) {
            Logger.getLogger(db_backup.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
}
