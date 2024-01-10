package VladBudiu.proiectPj.Repositories;

import VladBudiu.proiectPj.Entities.Masini.Masina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.*;
import java.util.List;

public interface MasiniInterface extends JpaRepository<Masina, String> {
    @Query("SELECT m FROM Masina m WHERE m.brand = :brand")
    List<Masina> cautaDupaBrand(String brand);
    @Query("SELECT m FROM Masina m WHERE m.model = :model")
    List<Masina> cautaDupaModel(String model);
    @Query("SELECT m FROM Masina m WHERE m.capacitateCilindrica = :capacitateCilindrica")
    List<Masina> cautaDupaCapacitateCilindrica(int capacitateCilindrica);

    @Query("Select Distinct m.brand from Masina m")
    List<String> cautaBrandDistincte();
    @Query("Select distinct m.model from Masina m")
    List<String> cautaModelDistinct();
    @Query("Select distinct m.capacitateCilindrica from Masina m")
    List<Integer> cautaCapacitateCilindricaDistincta();

    @Query("SELECT m FROM Masina m WHERE " +
            "(:brand IS null OR m.brand = :brand) AND " +
            "(:model IS null OR m.model = :model) AND " +
            "(:capacitateCilindrica IS null OR m.capacitateCilindrica = :capacitateCilindrica)")
    List<Masina> cautaDupaToateCriteriile(@Param("brand") String brand,
                                          @Param("model") String model,
                                          @Param("capacitateCilindrica") Integer capacitateCilindrica);

}
