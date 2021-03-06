package fr.gdvd.media_manager.entitiesMysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class VideoTrailler {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTrailler;
    @Size(max = 255)
    private String trailler;

    @JsonIgnore
    @OneToOne
    private VideoFilm videoFilm;


}
