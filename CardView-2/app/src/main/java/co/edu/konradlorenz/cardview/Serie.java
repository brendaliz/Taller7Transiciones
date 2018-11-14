package co.edu.konradlorenz.cardview;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Serie {
    private String name;
    private int Temporada;
    private int thumbnail;
    private String description;

    public Serie() {
    }

    public Serie(String name, int Temporada, int thumbnail, String description) {
        this.name = name;
        this.Temporada = Temporada;
        this.thumbnail = thumbnail;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTemporadas() {
        return Temporada;
    }

    public void setTemporadas(int temporadas) {
        Temporada = temporadas;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
