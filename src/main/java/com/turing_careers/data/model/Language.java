package com.turing_careers.data.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Language")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({
    @NamedQuery(name = "findAllLanguages", query = "SELECT l FROM Language l"),
    @NamedQuery(name = "findLanguageByLanguageCode", query = "SELECT l FROM Language l WHERE l.languageCode = :languageCode")
})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "languageId", nullable = false)
    @JsonProperty("_Language__id")
    private Long id;

    @Column(name = "languageCode", nullable = false)
    @JsonProperty("_Language__code")
    private String languageCode;

    @ManyToMany(mappedBy = "languages")
    @ToString.Exclude
    // @JsonManagedReference("languageOffer")
    @JsonIgnore
    private List<Offer> offerList;

    @ManyToMany(mappedBy = "languages")
    @ToString.Exclude
    // @JsonManagedReference("languageDeveloper")
    @JsonIgnore
    private List<Developer> developerList;

    public Language(String languageCode) {
        this.languageCode = languageCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
