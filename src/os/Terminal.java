package os;

import java.io.File;

public class Terminal {
    String current_path = "/home/mohamed/";//This Variable Just Point To The Folder Where iam
    /**
     * mkdir Function
     * this function create a folder in my current directory "current_path"
     * @param name this is then name of the folder that will be created
     * */
    public void mkdir(String name){
        //Contain the path of the folder that will be created
        String dir_path=null;
        //check if the last index of the path is "/" or not
        if(current_path.charAt(current_path.length()-1) == '/')
            dir_path = current_path + name;
        else
            dir_path = current_path + '/' + name;

        File my_dir =  new File(dir_path);
        //check if this folder is exist already
        if(my_dir.exists()){
            System.out.println("This Folder is Created Already.");
        }else{
            //create the folder
            boolean status = my_dir.mkdir();
            if (status)
                System.out.println("Folder Is Created");
            else
                System.out.println("Something goes wrong!");
        }
    }
    /**
     * rmdir Function
     * this function remove a directory
     * @param name this is then name of the folder that will be created
     * */
    public void rmdir(String name){
        //Contain the path of the folder that will be created
        String dir_path=null;
        //check if the last index of the path is "/" or not
        if(current_path.charAt(current_path.length()-1) == '/')
            dir_path = current_path + name;
        else
            dir_path = current_path + '/' + name;

        File my_dir =  new File(dir_path);
        //check if it's a valid directory
        if(my_dir.exists()){
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
        }else{
            System.out.println("This folder dosn't exist!");
        }
    }
}
