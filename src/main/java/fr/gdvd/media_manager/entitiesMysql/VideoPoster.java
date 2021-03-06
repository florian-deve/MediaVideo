package fr.gdvd.media_manager.entitiesMysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class VideoPoster {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPoster;

    @Nullable
    @Size(max = 32)
    private String idMd5Poster;

    @Nullable
    @Size(max = 255)
    private String fileName;

    @Nullable
    @Size(max = 255)
    private String ulrImg;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_VideoFilm")
    private VideoFilm videoFilm;

}
