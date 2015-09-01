/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCREDENTIALSFILE;

/**
 *
 * @author Maureen
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import PP.Admin.dbConnect;
import java.io.File;
import java.io.IOException;

public class AutoBackups {
String dbname, dbuser, dbpassword;
    boolean executed = false;
    String dbpath, dbpathD, dbpathE, dbpathF, dbpathG, dbpathM;
    String found_folder, full_date, path, computername, senderofmail;
    dbConnect conn =  new dbConnect();
    String[] myalphabet = {"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
String day2="";
String mail="",county="",partner="";
 String executeCmd = "";
    public AutoBackups()  {
      
  }
 
  public boolean CreateBackUp() throws IOException{
  boolean var=true;
   dbname = "ProgramProgress";
        dbuser = "root";
        dbpassword = "";
        String nextpage = "";
        found_folder = "";
       IdGenerator IG = new IdGenerator();
      full_date=IG.timestamp();
        System.out.println("its called");
        
        String localhost="";
        String[] localhostsplit = null;
        
        
        
         if(conn.dbsetup[3]!=null){
             dbpassword=conn.dbsetup[3];
                 
             
             }
             
             
             
             if(conn.dbsetup[2]!=null){
                 
             dbuser=conn.dbsetup[2];
                 
             
             }
             
               if (conn.dbsetup[0] != null) {

                localhost = conn.dbsetup[0];
                localhostsplit=localhost.split(":");

            }
             
//             if(conn.dbsetup[1]!=null){
//                 
//             dbname=conn.dbsetup[1];                 
//             
//             }
             System.out.println("PASSWORD ISvv :"+dbpassword);
             System.out.println("USER ISvv :"+dbuser);
             System.out.println("DBNAME ISv :"+dbname);
  
        
        
         for (int i = 0; i < myalphabet.length; i++) {
             System.out.println("at position  :  " + myalphabet[i]);
      String current_drive = myalphabet[i];
      File f = new File(current_drive + ":\\wamp\\mysql\\bin\\");
      File f1 = new File(current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin");
      File f2 = new File(current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin");
      File f3 = new File(current_drive + ":\\PPT_UPLOADS");
      if (f3.exists() && f3.isDirectory()) {
          path =current_drive + ":\\PPT_UPLOADS\\DATA\\MANUAL_BACKUP";
          new File(path).mkdirs();
          dbpath = path + "\\PPMT_DATA_AS_AT_" + full_date + ".sql";
//          System.out.println("file name directory : "+dbpath);
      }
      if (f.exists() && f.isDirectory()) {
          found_folder = "it is old wamp";
          executeCmd =current_drive + ":\\wamp\\mysql\\bin\\mysqldump --host="+localhostsplit[0]+ " --port="+localhostsplit[1]+ " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " -r " + dbpath + "";
//executeCmd = "C:\\wamp3\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump --no-create-info --skip-add-drop-table --host=localhost --user="+dbuser+" --password="+dbpassword+" "+dbname+" audit enrollment childage clientmember clientoccupation clientoparea dummy medical_form riskassessmentdetails riskassessmentmain riskreductionmain riskreductiondetails user taskauditor --where=timestamp>='"+startdate+"' -r "+dbpath+"";
       found_folder = "it is old wamp";
       System.out.println(executeCmd);
      }
      if (f1.exists() && f1.isDirectory()) {
          found_folder = "it is new wamp";
          executeCmd = current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump --host="+localhostsplit[0]+ " --port="+localhostsplit[1]+ " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " -r " + dbpath + "";
          found_folder = "it is new wamp";
      }
      if (f2.exists() && f2.isDirectory()) {
          found_folder = "it is workbench";
//          executeCmd = current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump --host=localhost --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " -r " + dbpath + "";
           executeCmd = current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump --host="+localhostsplit[0]+ " --port="+localhostsplit[1]+ " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " -r " + dbpath + "";
         
          found_folder = "it is workbench";
           System.out.println(executeCmd);
      }
   System.out.println("found folder :   "+found_folder);
            }
       Process runtimeProcess;
            try {
//                System.out.println("trying to back up the data.");
                runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();
                System.out.println("at 1 is " + processComplete);
                if (processComplete == 0) {
//                SEND TO THE MAIL BACKED UP DATA.
//                    System.out.println("Backup created successfully");
                }
           } catch (IOException ex) {
            } catch (InterruptedException ex) {
    }
  
      return var;
  }
  
  
}
