package os;
import os.Terminal;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        parser par = new parser();
        Terminal terminal = new Terminal();
        String line ;
        Scanner scan = new Scanner(System.in);
        boolean success;
        boolean working = true;
        while (working){
            System.out.print(terminal.pwd()+ "$ ");
            line = scan.nextLine();
            if(line.equalsIgnoreCase("bye")){
                working =false;
                break;
            }
            success = par.parse(line);
            if(success){
                switch (par.getCmd()){
                    case "cd":
                        if(par.length == 0)
                            terminal.cd();
                        else
                            terminal.cd(par.getArguments()[1]);
                        break;
                    case "ls":
                        String[] content ;
                        //System.out.println(par.getArguments().length);
                        if(par.length == 0)
                            content = terminal.ls();
                        else
                            content = terminal.ls(par.getArguments()[1]);
                        for (int i=0;i<content.length;i++)
                            System.out.println(content[i]);
                        break;
                    case "rm":
                        terminal.rm(par.getArguments()[1]);
                        break;
                    case "cp":
                        terminal.cp(par.getArguments()[1],par.getArguments()[2]);
                        break;
                    case "mv":
                        terminal.mv(par.getArguments()[1],par.getArguments()[2]);
                        break;
                    case "mkdir":
                        terminal.mkdir(par.getArguments()[1]);
                        break;
                    case "rmdir":
                        terminal.rmdir(par.getArguments()[1]);
                        break;
                    case "clear":
                        //System.out.println("This Console Not Cleared");
                        terminal.clear();
                        break;
                    case "more":
                        terminal.more(par.getArguments()[1],true);
                        break;
                    case "pwd":
                        System.out.println(terminal.pwd());
                        break;
                    case "cat":
                        System.out.println(terminal.cat(par.getArguments()));
                        break;
                    case "args":
                        terminal.args(par.getArguments()[1]);
                        break;
                    case "date":
                        System.out.println(terminal.date());
                        break;
                    case "help":
                        System.out.println(terminal.help());
                        break;
                    case ">":
                        terminal.redirect(par.getArguments(),false);
                        break;
                    case ">>":
                        terminal.redirect(par.getArguments(),true);
                        break;
                    case "|":
                        terminal.pipe(par.getArguments());
                        break;

                }
            }
        }
    }
}
