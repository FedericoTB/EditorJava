package models;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Document {
    private String title;
    private String type;
    private Path path;
    private File file;
    private String content;

    public Document(String name,String pathFile, String extensionType) throws IOException {
        this.title = name;
        this.type = extensionType;
        if(!Files.exists(Path.of(pathFile+name+extensionType))){
            this.path = Files.createFile(Path.of(pathFile+name+extensionType));
        }else this.path = Path.of(pathFile+name+extensionType);
        this.file = new File(pathFile+name+extensionType);
        this.content = fileReader(this.file);
    }
    public Document(File eFile) throws IOException {
        this.file = eFile;
        this.title = eFile.getName();
        this.path = Path.of(eFile.getPath());
        this.type = eFile.getName().substring(eFile.getName().lastIndexOf("."));
        this.content = fileReader(this.file);
    }

    public String fileReader(File file) throws IOException {
        return new String(Files.readAllBytes(Path.of(file.getPath())),"UTF-8");
    }
    public void fileWriter(File file,Document document) throws IOException {
        Files.write(Path.of(file.getPath()),document.content.getBytes(StandardCharsets.UTF_8));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { this.title = title;}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) { this.path = path;}

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getContent() {return content;}

    public void setContent(String content) {
        this.content = content;
    }
}
