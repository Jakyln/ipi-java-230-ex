package com.ipiecoles.java.java230.repository;

import com.ipiecoles.java.java230.model.Employe;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EmployeRepository extends JpaRepository<Employe, Long> {
    //    Recherche d'employés par matricule
    Employe findByMatricule(String matricule);

    /*@Override
    public void run(String... strings) throws Exception*/

    //    Recherche d'employés par nom et prénom
    List<Employe> findByNomAndPrenom(String nom, String Prenom);

    List<Employe> findByNomIgnoreCase(String nom);
    //    Recherche d'employés embauchés avant une certaine date
    List<Employe> findByDateEmbaucheBefore(LocalDate dateEmbauche);
    //    Recherche d'employés embauchés après une certaine date
    List<Employe> findByDateEmbaucheAfter(LocalDate dateEmbauche);
    //    Recherche d'employés gagnant plus de X euros et ordonnés selon leur salaire (ceux qui gagnent le plus d'abord).
    List<Employe> findBySalaireGreaterThanOrderBySalaireDesc(Double salaire);


    Page<Employe> findByNomIgnoreCase(String nom, Pageable pageable);
    @Query("select e from Employe e " +
            "where lower(e.prenom) = lower(:nomOuPrenom) or " +
            "lower(e.nom) = lower(:nomOuPrenom)")
    List<Employe> findByNomOrPrenomAllIgnoreCase(@Param("nomOuPrenom") String nomOuPrenom);
    @Query(value = "SELECT * FROM Employe WHERE salaire > (SELECT avg(e2.salaire) FROM Employe e2);", nativeQuery = true)
    List<Employe> findEmployePlusRiches();
}
