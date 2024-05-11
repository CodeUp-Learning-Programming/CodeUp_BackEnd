package up.code.codeup.mapper;

import up.code.codeup.dto.amizadeDto.AmigoDto;
import up.code.codeup.dto.amizadeDto.AmizadeResultDto;
import up.code.codeup.dto.amizadeDto.BuscarPorNomeResultDto;
import up.code.codeup.entity.Amizade;
import up.code.codeup.entity.Usuario;

public class AmizadeMapper {
    public static AmizadeResultDto toDto(Amizade amizade, Usuario solicitante) {
        System.out.println("Aqui está o id do soilictante na mapper" + amizade.getSolicitante().getId());

        AmizadeResultDto amizadeResultDto = new AmizadeResultDto();
        amizadeResultDto.setNome(solicitante.getNome());
        amizadeResultDto.setEmail(solicitante.getEmail());
        amizadeResultDto.setFoto(solicitante.getFotoPerfil());
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

    public static BuscarPorNomeResultDto toBuscarPorNomeResultDto(Amizade amizade, Integer idUsuario) {
        BuscarPorNomeResultDto buscarPorNomeResultDto = new BuscarPorNomeResultDto();
        if (amizade.getSolicitante().getId() == idUsuario) {
            buscarPorNomeResultDto.setNome(amizade.getReceptor().getNome());
            buscarPorNomeResultDto.setEmail(amizade.getReceptor().getEmail());
            buscarPorNomeResultDto.setFoto(amizade.getReceptor().getFotoPerfil());
            buscarPorNomeResultDto.setStatusAmizade(amizade.getStatus());
            buscarPorNomeResultDto.setStatusUsuario("Receptor");
        } else {
            buscarPorNomeResultDto.setNome(amizade.getSolicitante().getNome());
            buscarPorNomeResultDto.setEmail(amizade.getSolicitante().getEmail());
            buscarPorNomeResultDto.setFoto(amizade.getSolicitante().getFotoPerfil());
            buscarPorNomeResultDto.setStatusAmizade(amizade.getStatus());
            buscarPorNomeResultDto.setStatusUsuario("Solicitante");
        }
        return buscarPorNomeResultDto;
    }
    public static BuscarPorNomeResultDto toBuscarPorNomeResultDto(Usuario usuario) {
        BuscarPorNomeResultDto buscarPorNomeResultDto = new BuscarPorNomeResultDto();
        buscarPorNomeResultDto.setNome(usuario.getNome());
        buscarPorNomeResultDto.setEmail(usuario.getEmail());
        buscarPorNomeResultDto.setFoto(usuario.getFotoPerfil());
        buscarPorNomeResultDto.setStatusAmizade(null);
        buscarPorNomeResultDto.setStatusUsuario("Não há relacionamento");
        return buscarPorNomeResultDto;
    }


}
