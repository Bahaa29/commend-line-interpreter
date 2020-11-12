package os;

import javax.print.attribute.standard.Destination;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Terminal
{
    //This Variable Just Point To The Folder Where iam
    private String current_path = "/home/mohamed/";
    private String[] commands={"cd", "ls", "rm" , "cp", "mv", "mkdir", "rmdir", "clear", "more", "pwd","cat","args", "date", "help",">",">>"};

    public String pwd() {
        return current_path;
    }
    public void cd() {
        current_path = "/home/mohamed/";
    }
    public void cd(String arg) {
        arg = checkDir(arg);
        File file = new File(arg);
        boolean flag = file.isDirectory();
        if (flag) {
            current_path =arg;
        }
        else{
            System.out.println("Invalid Directory path");
        }
    }
    public String[] ls() {
        File file1 = new File(current_path);
        String[] list = file1.list();
        return list;
    }
    public String[] ls(String arg) {
        File file2 = new File(arg);
        boolean flag = file2.isDirectory();
        String[] list = null;
        if (flag) {
            list = file2.list();
        }
        else{
            System.out.println("Invalid path");
        }
        return list;
    }
    public void more(String file_name) {
        String s = "";
        try {
            File my_file = new File(file_name);
            Scanner Reader = new Scanner(my_file);
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                s+=data;
            }
            Reader.close();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        if (s.length()>1000) {
            for (int i =0; i<4 ; i++){
                switch (i){
                    case 0:
                        System.out.println(s.substring(0, s.length()/4));
                        break;
                    case 1:
                        System.out.println(s.substring(s.length()/4, s.length()/2));
                        break;
                    case 2:
                        System.out.println(s.substring(s.length()/2, 3*(s.length())/4));
                        break;
                    case 3:
                        System.out.println(s.substring((3*(s.length())/4), s.length()));
                        break;
                }
                if (i==3)
                    break;
                System.out.println("If you want to continue press 'c' or any key");
                System.out.println("If you want to quit press 'q'");
                Scanner scan = new Scanner(System.in);
                String in = scan.nextLine();
                if (in.equalsIgnoreCase("q"))
                    break;
            }
        }
        else{
            System.out.println(s);
        }
    }
    public void cp(String file1,String file2) {
        File InFile=new File(file1);
        File OutFile=new File(file2);
        if(InFile.exists())
        {
            try {
                FileReader read=new FileReader(InFile);
                BufferedReader input=new BufferedReader(read);
                if(!OutFile.exists())
                    OutFile.createNewFile();
                BufferedWriter output=new BufferedWriter(new FileWriter(OutFile));
                while(input.ready())
                {
                    output.write(input.readLine());
                    output.newLine();
                }
                output.close();
                input.close();
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
        else
            System.out.println("the file u want to copy is not exist");
    }
    public String date() {
        Date date = new Date();
        String time=date.toString();
        return time;
    }
    public String help() {
        String[] syntax={"cd or cd and put the path", "ls or ls and put the path","rm path","cp file1 file2","mv file1 file2","mikdir{path and name}","rmdir{path and name","clear","more file path","pwd","cat file1 or cat file1 file2","args command name","date","help","[command name][command parameters]>[File Path]","[command name][command parameters]>>[File Path]"};
        String [] description ={"changes the current directory to another one " , "lists Directory contents sorted alphabetically " , "removes specified file OR delete a directory and recursively delete its content " , "copies the first file onto the second one but it does not copy directories " ,
                "moves the given file into a file with the same given name " , "creates a directory with the given name " , "removes an existing empty directory " , "clears the current terminal screen " , "Display and scroll down the output in one direction only you can scroll page by page " , "Display current user directory " , "Concatenates files and prints on the standard output" ,"List all command arguments " , "Display Current date/time " , "List all user commands and the syntax of their arguments", "Redirct output to be written to the given file (create file if doesn't exist and replace if exists)","Redirct output to be written to the given file (create file if doesn't exist and Append if exists)"};
        String help_msg="";
        for(int i=0 ;i< commands.length;i++)
        {
            help_msg += "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + System.lineSeparator();
            help_msg += "commands: "+commands[i]+" | description: "+description[i]+" syntax: "+syntax[i]+ System.lineSeparator();
            help_msg += "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + System.lineSeparator();
        }
        return help_msg;
    }
    public void redirect(String[] Args,boolean type) throws FileNotFoundException {
        String[] command_side;//this array for special command like > >> |
        String[] file_side;//this array for special command like > >> |
        String result="";
        command_side=Args[0].split(" ");
        file_side=Args[1].split(" ");;
        String command=command_side[0];
        if(command.equalsIgnoreCase("cat")||command.equalsIgnoreCase("date")||command.equalsIgnoreCase("ls")||command.equalsIgnoreCase("pwd"))
        {
            if(file_side.length==1)
            {
                if(command.equalsIgnoreCase("cat"))
                {
                    String[] paths=null;
                    for (int i=1;i<command_side.length;i++)
                        paths[i] = command_side[i];
                    if(paths == null){
                        result ="";
                        System.out.println("Invalid Command Arguments");
                    }else{
                        result = cat(paths);
                    }

                }
                else if (command.equalsIgnoreCase("date"))
                {
                    result=date();
                }
                else if (command.equalsIgnoreCase("pwd"))
                {
                    result=pwd();
                }
                else if (command.equalsIgnoreCase("ls"))
                {
                    String[] content=null;
                    if(command_side.length == 1){
                        content=ls();
                    }
                    else{
                        content = ls(command_side[1]);
                    }
                    for (int i=0;i<content.length;i++)
                        result += content[i] + System.lineSeparator();
                }
            }
        }
        else
            System.out.println("the command u enter cant work ");
        try
        {
            File outFile=new File(file_side[0]);
            if(!outFile.exists())
                outFile.createNewFile();
            FileWriter write=new FileWriter(outFile,type);
            BufferedWriter buff=new BufferedWriter(write);
            while(result.length()>0)
            {
                buff.write(result);
                buff.newLine();
            }
            buff.flush();
            buff.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
    /**
     * mkdir Function
     * this function create a folder in my current directory "current_path"
     * @param dir this is then name of the folder that will be created
     * */
    public void mkdir(String dir){
        //Contain the path of the folder that will be created
        String dir_path=null;
        //On Linux
        if(dir.charAt(0) == '/'){
            //if the it start with / then it's a full path "/home/mohamed"
            dir_path = dir;
        }else{
            //if it dosn't start with / then it's a folder name exist in the current path
            dir_path = current_path + '/' + dir;
        }
        //On Windows
        /*if(dir.charAt(1) == ':'){
            //if the it start with / then it's a full path "/home/mohamed"
            dir_path = dir;
        }else{
            //if it dosn't start with / then it's a folder name exist in the current path
            dir_path = current_path + '/' + dir;
        }*/
        File my_dir =  new File(dir_path);
        //create the folder
        boolean status = my_dir.mkdir();
        if (status)
            System.out.println("Folder Is Created");
        else
            System.out.println("Something goes wrong!");
    }
    /**
     * rmdir Function
     * this function remove a directory
     * @param name this is then name of the folder that will be deleted
     * */
    public void rmdir(String name){
        //Contain the path of the folder that will be created
        String dir_path=null;
       dir_path = checkDir(name);
       if(dir_path != ""){
           File my_dir =  new File(dir_path);
           //check if this path is a directory not a file
           if(my_dir.isDirectory()){
               boolean success = my_dir.delete();
               if (success)
                   System.out.println("The Folder Deleted Successfuly.");
               else
                   System.out.println("Something goes wrong!");
           }else{
               System.out.println("This Not A Directory!");
           }
       }
    }
    /**
     * rm Function
     * this function remove a file
     * @param sourcePath this is then filename that will be deleted
     * */
    public void rm(String sourcePath){
        //Contain the path of the folder that will be created
        String dir_path=null;
        dir_path = checkDir(sourcePath);
        if(dir_path != ""){
            File my_file =  new File(dir_path);
            //check if this path is a file
            if(!my_file.isDirectory()){
                boolean success = my_file.delete();
                if (success)
                    System.out.println("The File Deleted Successfuly.");
                else
                    System.out.println("Something goes wrong!");
            }else{
                System.out.println("This Not A File!");
            }
        }
    }
    /**
     * mv Function
     * this function move file from source path to destination path
     * @param sourcePath the path of file that will be moved
     * @param destinationPath the path to move file in it
     * */
    public void mv(String sourcePath, String destinationPath){
        File InFile=new File(sourcePath);
        File OutFile=new File(destinationPath);
        if(InFile.exists())
        {
            try {
                FileReader read=new FileReader(InFile);
                BufferedReader input=new BufferedReader(read);
                if(!OutFile.exists())
                    OutFile.createNewFile();
                BufferedWriter output=new BufferedWriter(new FileWriter(OutFile));
                while(input.ready())
                {
                    output.write(input.readLine());
                    output.newLine();
                }
                output.close();
                input.close();
                File old =  new File(sourcePath);
                old.delete();
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
        }
        else
            System.out.println("the file u want to copy is not exist");
    }
    /**
     * checkDir Function
     * this function check if the directory is exist and fix reformat the path if it's not a full path
     * @param dir the path or folder name
     * */
    String checkDir(String dir){
        String dir_path=null;
        //On Linux
        if(dir.charAt(0) == '/'){
            //if the it start with / then it's a full path "/home/mohamed"
            dir_path = dir;
        }else{
            //if it dosn't start with / then it's a folder name exist in the current path
            dir_path = current_path + '/' + dir;
        }
        //On Windows
        /*if(dir.charAt(1) == ':'){
            //if the it start with / then it's a full path "/home/mohamed"
            dir_path = dir;
        }else{
            //if it dosn't start with / then it's a folder name exist in the current path
            dir_path = current_path + '/' + dir;
        }*/
        File the_dir = new File(dir_path);
        if(!the_dir.exists()){
            System.out.println("This Directory Not Exist!");
            return "";
        }
        return dir_path;
    }
    public void pipe(String[] my_args) throws FileNotFoundException { //pwd | cat
        String output= "";
        String command="";
        String[] arguments=null;
        parser par = new parser();

        for(int i=0;i<my_args.length;i++){
            par.parse(my_args[i]);
            command = par.getCmd();
            arguments = par.getArguments();
            switch (command){
                case "cd":
                    if(arguments == null){
                        cd();
                    }else{
                        cd(arguments[0]);
                    }
                    break;
                case "ls":
                    String[] content = null;
                    if(arguments == null){
                        content = ls();
                    }else{
                        content = ls(arguments[0]);
                    }
                    for (int k=0;k<content.length;k++)
                        output += content[i] + System.lineSeparator();
                    break;
                case "cp":
                    cp(arguments[0],arguments[1]);
                    break;
                case "cat":
                    if (arguments.length != 0){
                        output = cat(arguments);
                    }
                    break;
                case "more":
                    if(arguments == null){
                        more(output);
                    }else{
                        more(arguments[0]);
                    }
                    break;
                case ">":
                    redirect(arguments,false);
                    break;
                case ">>":
                    redirect(arguments,true);
                    break;
                case "mkdir":
                    mkdir(arguments[0]);
                    break;
                case "rmdir":
                    rmdir(arguments[0]);
                    break;
                case "mv":
                    mv(arguments[0],arguments[1]);
                    break;
                case "rm":
                    rm(arguments[0]);
                    break;
                case "args":
                    args(arguments[0]);
                    break;
                case "date":
                    output  = date();
                    break;
                case "help":
                    output = help();
                    break;
                case "pwd":
                    output = pwd();
                    break;
            }
            System.out.println(output);
        }
    }
    public String cat(String[] files_name ) throws FileNotFoundException {
        String line2;
        String line = "";
        try {
            for (int i = 0; i < files_name.length; i++) {
                File myfile = new File(files_name[i]);
                Scanner reader = new Scanner(myfile);

                while ((reader.hasNextLine())) {
                    line2 = reader.nextLine();

                    System.out.println(line2);
                    line +=  line2+System.lineSeparator();
                }
                System.out.println("************************************");
                reader.close();
                line +="///////////////////////////////////////////////"+System.lineSeparator() ;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return line;

    }
    public void args(String commnde1) {
        if (commnde1.equals("cd")) {
            System.out.println("args[1]:Destination path");
        }
        else if (commnde1.equals("cp")) {
            System.out.println("args[1]:orginal file");
            System.out.println("args[2]: copy file");
        }
        else  if (commnde1.equals("mv")) {
            System.out.println("args[1]:first file");
            System.out.println("args[1]: second file");
        }
        else  if (commnde1.equals("rm")) {
            System.out.println("args[1]:removed file");
        }
        else if (commnde1.equals("mkdir")) {
            System.out.println("args[1]:make folder");
        }
        else if (commnde1.equals("rmdir")) {
            System.out.println("args[1]:removed folder");
        }
        else if (commnde1.equals("more")) {
            System.out.println("args[1]:first file");
        }
        else if (commnde1.equals(">")) {
            System.out.println("args[1]:path file");
        }
        else if (commnde1.equals(">>")) {
            System.out.println("args[1]:path file");
        }else{
            System.out.println("Command Not Found!");
        }
    }
    public void clear() throws IOException {

    }

}
