package amoghjapps.com.gamesofthroneslast;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "char" ,indices = {@Index("name")})
public class Data implements Serializable{

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("dateOfBirth")
    @Expose
    private Integer dateOfBirth;

    @SerializedName("imageLink")
    @Expose
    private String imageLink;

    @SerializedName("male")
    @Expose
    private Boolean male;

    @SerializedName("spouse")
    @Expose
    private String spouse;

    @SerializedName("house")
    @Expose
    private String house;

    @SerializedName("slug")
    @Expose
    
    private String slug;

    @SerializedName("name")
    @Expose
    @PrimaryKey
    @NonNull
    private String name;

    @SerializedName("__v")
    @Expose
    
    private Integer v;

    @SerializedName("pageRank")
    @Expose
    
    private Integer pageRank;

    @SerializedName("hasPath")
    @Expose
    
    private Boolean hasPath;

    @SerializedName("books")
    @Expose
    
    private List<String> books = null;

    @SerializedName("updatedAt")
    @Expose
    
    private String updatedAt;

    @SerializedName("createdAt")
    @Expose
    
    private String createdAt;

    @SerializedName("titles")
    @Expose
    
    private List<String> titles = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Integer dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Integer getPageRank() {
        return pageRank;
    }

    public void setPageRank(Integer pageRank) {
        this.pageRank = pageRank;
    }

    public Boolean getHasPath() {
        return hasPath;
    }

    public void setHasPath(Boolean hasPath) {
        this.hasPath = hasPath;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

}
