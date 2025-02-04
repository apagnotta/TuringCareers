package com.turing_careers.data.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Employer")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({
    @NamedQuery(name = "findAllEmployers", query = "SELECT e FROM Employer e"),
    @NamedQuery(name = "findEmplsByMailAndPassword", query = "SELECT e FROM Employer e WHERE e.mail = :mail  AND e.password = :password"),
    @NamedQuery(name = "findEmployerByMail", query = "SELECT e FROM Employer e WHERE e.mail = :mail "),
    @NamedQuery(name = "findEmployerById", query = "SELECT e FROM Employer e WHERE e.id = :id")
})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employer implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employerId", nullable = false)
    @JsonProperty("_Employer__id")
    private Long id;

    @Column(name = "firstName", nullable = false)
    @JsonProperty("_Employer__f_name")
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @JsonProperty("_Employer__l_name")
    private String lastName;

    @Column(name = "mail", nullable = false)
    @JsonProperty("_Employer__mail")
    private String mail;

    @Column(name = "passwordAccount", nullable = false)
    @JsonProperty("_Employer__psw")
    private String password;

    @Column(name = "companyName", nullable = false)
    @JsonProperty("_Employer__company")
    private String companyName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employer")
    @ToString.Exclude
    @JsonManagedReference("employerOffers")
    private List<Offer> offers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "EmployerDeveloper",
            joinColumns = @JoinColumn(name = "employerId"),
            inverseJoinColumns = @JoinColumn(name = "developerId")
    )
    @ToString.Exclude
    private List<Developer> savedDevelopers;

    public Employer(String firstName, String lastName, String mail, String password, String companyName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.companyName = companyName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
