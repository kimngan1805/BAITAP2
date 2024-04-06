package Model;

import java.util.List;
public class Model {
    private String fileName;
    private List<String> content;

    public Model(String fileName, List<String> content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return this.fileName;
    }

    public List<String> getContent() {
        return this.content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

}
