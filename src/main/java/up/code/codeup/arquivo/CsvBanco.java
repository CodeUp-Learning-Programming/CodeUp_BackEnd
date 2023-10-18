package up.code.codeup.arquivo;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import up.code.codeup.entity.Usuario;

import java.io.FileReader;
import java.io.IOException;

public class CsvBanco {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveUsuariosFromCsv(String csvFileName) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(csvFileName)).withSkipLines(1).build()) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(Integer.parseInt(line[0]));
                usuario.setNome(line[1]);
                usuario.setEmail(line[2]);
                usuario.setSenha(line[3]);
                usuario.setNivel(Integer.parseInt(line[4]));
                usuario.setXp(Integer.parseInt(line[5]));

                // Parse LocalDate from line[4] if needed

                // Save the Usuario entity to the H2 Database
                entityManager.persist(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }



}


