import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public static String[] readCpfFromFile(String fileName) {
        List<String> cpfList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split("\\|");
                if (userData.length >= 2) {
                    String cpf = userData[1];
                    cpfList.add(cpf);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cpfList.toArray(new String[0]);
    }

    public static String[] DataFromCpf(String fileName, String cpf) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split("\\|");
                if (userData.length >= 2 && userData[1].equals(cpf)) {
                    return userData;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    public static void saveDataToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateDataByCpf(String fileName, String cpf, String newData) {
        String tempFileName = fileName + "_temp" + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName, true))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split("\\|");
                // ---- Substitui corretamente ----
                if (userData.length >= 2 && userData[1].equals(cpf)) {
                    writer.write(newData);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            //Erro de execução
            e.printStackTrace();
        }

        // Troca os files corretamente (e cria backup)
        File oldFile = new File(fileName);
        File newFile = new File(tempFileName);
        File backup = new File("backup.txt");
        if(backup.exists())
            backup.delete();
        oldFile.renameTo(backup);
        newFile.renameTo(oldFile);
    }

    public static boolean cpfExists(String fileName, String cpf) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split("\\|");
                if (userData.length >= 2 && userData[1].equals(cpf)) {
                    return true; // CPF exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
