package com.projetMedecine.Modele;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "utilisateur")
@Data
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use= JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Patient.class,name = "PATIENT"),
        @JsonSubTypes.Type(value= Medecin.class,name = "MEDECIN")
})
public abstract class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_utilisateur")
    private Integer id;
    @NotBlank(message = "Prenom ne doit pas etre null")
    private String prenom;
    @NotBlank(message = "Nom ne doit pas etre vide(null)")
    private String nom;
    @NotBlank(message = "email ne doit pas etre vide(null)")
    private String email;
    @NotBlank(message = "le mot de pass ne doit pas etre vide(null)")
    @Column(name="mot_de_passe")
    private String password;
    @NotBlank(message = "Telephone ne doit pas etre vide(null)")
    private String telephone;
    @NotBlank(message = "L'adresse ne doit pas etre vide(null)")
    private String adresse;
    private String role;
}
