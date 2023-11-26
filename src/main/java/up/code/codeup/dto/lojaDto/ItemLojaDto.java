package up.code.codeup.dto.lojaDto;

import up.code.codeup.entity.ItemLoja;

import java.util.Base64;

public record ItemLojaDto(
        Integer id,
        String nomeItem,
        String fotoItem,
        String tipoItem,
        Integer precoItem,
        String descricaoItem,
        boolean adquirido
) {
    public ItemLojaDto(ItemLoja itemLoja, boolean adquirido) {
        this(
                itemLoja.getId(),
                itemLoja.getNome()  ,
                itemLoja.getImagem(),
                itemLoja.getTipo(),
                itemLoja.getPreco().intValue(),
                itemLoja.getDescricao(),
                adquirido
        );
    }
}
