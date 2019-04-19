import java.io.*;

public class FileWriter {
    private static FileOutputStream fileOutputStream;
    private static ObjectOutputStream objectOutputStream;

    private static FileInputStream fileInputStream;
    private static ObjectInputStream objectInputStream;

    public FileWriter() {
        try {
            //already existing file for input
            File file = new File("menu.ser");
            File auxFile = new File("helpFile.ser");
            if(!file.createNewFile() && file.length()!=0) {
                //old menu for input
                fileInputStream = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(fileInputStream);

                //aux file for output
                auxFile.createNewFile();
                FileOutputStream fileOutputStream1 = new FileOutputStream(auxFile, false);
                ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);

                //actual output
                MenuItem menuItem;
                while (true) {
                    try {
                        menuItem = (MenuItem) objectInputStream.readObject();
                        objectOutputStream1.writeObject(menuItem);
                    } catch (StreamCorruptedException | EOFException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println(e);
                    }
                }

                //clean old file
                fileInputStream.close();
                file.delete();

                //recreate
                file.createNewFile();

                //prepare aux for input
                FileInputStream fileInputStream1 = new FileInputStream(auxFile);
                ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);

                //output to new file
                fileOutputStream = new FileOutputStream(file, false);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                while (true) {
                    try {
                        menuItem = (MenuItem) objectInputStream1.readObject();
                        objectOutputStream.writeObject(menuItem);
                    } catch (StreamCorruptedException | EOFException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println(e);
                    }
                }

                //clean aux file
                fileInputStream1.close();
                fileOutputStream1.close();
                auxFile.delete();

                //prepare input
                fileInputStream = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(fileInputStream);
            } else {
                fileOutputStream = new FileOutputStream(file, true);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                fileInputStream = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(fileInputStream);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public static ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public static FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    public static FileOutputStream getFileOutputStream() {
        return fileOutputStream;
    }
}
