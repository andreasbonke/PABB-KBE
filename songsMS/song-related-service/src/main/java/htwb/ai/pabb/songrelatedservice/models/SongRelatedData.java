package htwb.ai.pabb.songrelatedservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "song-related-data")
public class SongRelatedData {

    @Id
    private String id;
    private String songtext;

    public SongRelatedData(String id, String songtext) {
        this.id = id;
        this.songtext = songtext;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSongtext() {
        return songtext;
    }

    public void setSongtext(String songtext) {
        this.songtext = songtext;
    }
}
