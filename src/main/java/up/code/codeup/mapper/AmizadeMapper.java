package up.code.codeup.mapper;

import up.code.codeup.dto.amizadeDto.AmigoDto;
import up.code.codeup.dto.amizadeDto.AmizadeResultDto;
import up.code.codeup.entity.Amizade;
import up.code.codeup.entity.Usuario;

public class AmizadeMapper {
    public static AmizadeResultDto toDto(Amizade amizade, Usuario solicitante) {
        System.out.println("Aqui está o id do soilictante na mapper" + amizade.getSolicitante().getId());

        AmizadeResultDto amizadeResultDto = new AmizadeResultDto();
        amizadeResultDto.setNome(solicitante.getNome());
        amizadeResultDto.setEmail(solicitante.getEmail());
        amizadeResultDto.setStatus(amizade.getStatus());
        return amizadeResultDto;

    }

    public static AmigoDto toAmigoDto(Usuario amigo) {
        AmigoDto amigoDto = new AmigoDto();
        amigoDto.setNome(amigo.getNome());
        amigoDto.setEmail(amigo.getEmail());
        amigoDto.setFoto(amigo.getFotoPerfil());
        amigoDto.setXp(amigo.getXp());
        return amigoDto;
    }
}
