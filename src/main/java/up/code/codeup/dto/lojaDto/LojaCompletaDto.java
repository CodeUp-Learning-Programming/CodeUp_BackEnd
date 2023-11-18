package up.code.codeup.dto.lojaDto;

import java.util.List;

public record LojaCompletaDto(
        List<String> tipoDosItens,
        List<ItemLojaDto> itensLoja
) {

}
