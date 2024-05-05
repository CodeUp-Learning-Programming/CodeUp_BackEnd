package up.code.codeup.mapper;

import up.code.codeup.dto.usuarioDto.AmizadeResultDto;
import up.code.codeup.entity.Amizade;
import up.code.codeup.entity.Usuario;

public class AmizadeMapper {
    public static AmizadeResultDto toDto(Amizade amizade, Usuario solicitante) {
        System.out.println("Aqui está o id do soilictante na mapper" + amizade.getSolicitante().getId());

        AmizadeResultDto amizadeResultDto = new AmizadeResultDto();
        amizadeResultDto.setNomeSolicitante(solicitante.getNome());
        amizadeResultDto.setEmailSolicitante(solicitante.getEmail());
        amizadeResultDto.setStatus(amizade.getStatus());
        return amizadeResultDto;

    }
}
