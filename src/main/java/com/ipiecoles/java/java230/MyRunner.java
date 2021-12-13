package com.ipiecoles.java.java230;

import com.ipiecoles.java.java230.model.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import com.ipiecoles.java.java230.repository.EmployeRepository;

import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private EmployeRepository EmployeRepository;


    /*@Override
    public void run(String... strings) throws Exception {

        System.out.println(EmployeRepository.count());
        Employe employe = EmployeRepository.findById(5L).get();
        System.out.println(employe.toString());
    }*/
    @Override
    public void run(String... strings)throws Exception{
        /*List<Employe> employes = EmployeRepository.findByNomAndPrenom("Gaillard", "Victor");
        for(Employe employe : employes){
            System.out.println(employe.toString());
        }*/

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.ASC, "matricule");
        Page<Employe> employes = EmployeRepository.findByNomIgnoreCase("PiERre", pageRequest);
        System.out.println(employes.getTotalElements());
        System.out.println(employes.getTotalPages());
        for(Employe employe : employes){
            System.out.println(employe.toString());
            //employe.getManager()

        }

    }
    /*List<Employe> findByDateEmbaucheBefore(LocalDate dateEmbauche);
    List<Employe> findByDateEmbaucheAfter(LocalDate dateEmbauche);*/


    public static void print(Object t) {
        System.out.println(t);
    }


}
